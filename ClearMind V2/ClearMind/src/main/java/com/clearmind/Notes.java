package com.clearmind;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxmisc.richtext.InlineCssTextArea;

/**
 * Manages the Notes interface: toolbar + paginated document.
 */
public class Notes {

    private PaginatedDocument document;
    private VBox pagesContainer;
    private ScrollPane scroll;
    private boolean handlingOverflow = false;

    private InlineCssTextArea activeEditor;

    public void open(Stage stage) {
        pagesContainer = new VBox(20);
        pagesContainer.setAlignment(Pos.TOP_CENTER);
        pagesContainer.setPadding(new Insets(20));

        document = new PaginatedDocument(pagesContainer);
        Page firstPage = document.getLastPage();
        activeEditor = firstPage.editor;
        attachOverflowListener(firstPage);
        attachFocusListener(firstPage);

        ToolBar toolBar = createToolBar();

        scroll = new ScrollPane(pagesContainer);
        scroll.setFitToWidth(true);

        BorderPane root = new BorderPane(scroll);
        root.setTop(toolBar);

        stage.setScene(new Scene(root, 850, 950));
        stage.setTitle("ClearMind - Notes");
        stage.show();

        firstPage.editor.requestFocus();
    }

    private ToolBar createToolBar() {
        ToggleButton boldBtn = new ToggleButton("B");
        boldBtn.setStyle("-fx-font-weight: bold;");
        boldBtn.setOnAction(e -> applyStyle(
                boldBtn.isSelected() ? "-fx-font-weight: bold;" : "-fx-font-weight: normal;"));

        ToggleButton italicBtn = new ToggleButton("I");
        italicBtn.setStyle("-fx-font-style: italic;");
        italicBtn.setOnAction(e -> applyStyle(
                italicBtn.isSelected() ? "-fx-font-style: italic;" : "-fx-font-style: normal;"));

        ToggleButton underlineBtn = new ToggleButton("U");
        underlineBtn.setStyle("-fx-underline: true;");
        underlineBtn.setOnAction(e -> applyStyle(
                underlineBtn.isSelected() ? "-fx-underline: true;" : "-fx-underline: false;"));

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            javafx.scene.paint.Color c = colorPicker.getValue();
            applyStyle(String.format("-fx-fill: rgb(%d,%d,%d);",
                    (int)(c.getRed() * 255),
                    (int)(c.getGreen() * 255),
                    (int)(c.getBlue() * 255)));
        });

        ToolBar bar = new ToolBar(boldBtn, italicBtn, underlineBtn, colorPicker);
        bar.setPadding(new Insets(5));
        return bar;
    }

    private void applyStyle(String style) {
        if (activeEditor == null) return;
        int start = activeEditor.getSelection().getStart();
        int end   = activeEditor.getSelection().getEnd();
        if (start == end) return;
        activeEditor.setStyle(start, end, style);
    }

    private void attachFocusListener(Page page) {
        page.editor.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) activeEditor = page.editor;
        });
    }

    private void attachOverflowListener(Page page) {
        page.editor.textProperty().addListener((obs, oldText, newText) -> {
            if (handlingOverflow) return;
            Platform.runLater(() -> {
                if (handlingOverflow) return;
                double usable = Page.HEIGHT - 2 * Page.PADDING;
                double height = page.editor.totalHeightEstimateProperty().orElse(0.0).getValue();
                if (height > usable) {
                    handlingOverflow = true;

                    // Only add a new page if this is actually the last page,
                    // to avoid creating duplicate pages on repeated overflow events.
                    if (document.getPageIndex(page) == document.getPageCount() - 1) {
                        document.addPage();
                        Page next = document.getLastPage();
                        attachOverflowListener(next);
                        attachFocusListener(next);
                    }

                    Page next = document.getPage(document.getPageIndex(page) + 1);
                    overflowToNextPage(page, next);

                    // Scroll so the new page's top is visible, not just vvalue=1.0
                    // which can overshoot on a long document.
                    Platform.runLater(() -> {
                        double contentH = pagesContainer.getBoundsInLocal().getHeight();
                        double viewH    = scroll.getViewportBounds().getHeight();
                        if (contentH > viewH) {
                            // Compute the Y offset of the new page inside pagesContainer
                            double pageTop = next.getBoundsInParent().getMinY();
                            double newVvalue = pageTop / (contentH - viewH);
                            scroll.setVvalue(Math.min(newVvalue, 1.0));
                        }
                    });

                    handlingOverflow = false;
                }
            });
        });
    }

    private void overflowToNextPage(Page current, Page next) {
        double usable = Page.HEIGHT - 2 * Page.PADDING;
        String fullText = current.editor.getText();

        // ---------- find the split point purely by character counting ----------
        // We binary-search for the largest prefix that fits within `usable` height.
        // This avoids relying on the editor re-laying out after each replaceText call,
        // which was the original bug (height never updated inside the while-loop).

        // First check: if the whole text already fits, nothing to do.
        double currentHeight = current.editor.totalHeightEstimateProperty().orElse(0.0).getValue();
        if (currentHeight <= usable) return;

        // Split at the last newline boundary that keeps the current page within bounds.
        // Walk backwards through paragraph breaks until the page fits.
        String textOnCurrentPage = fullText;
        String textForNextPage   = "";

        // Collect paragraph split candidates (indices of all '\n' chars, plus index 0)
        java.util.List<Integer> splitPoints = new java.util.ArrayList<>();
        splitPoints.add(0);
        for (int i = 0; i < fullText.length(); i++) {
            if (fullText.charAt(i) == '\n') splitPoints.add(i + 1); // start of next paragraph
        }

        // Walk from the last split point backwards until the kept prefix fits.
        for (int i = splitPoints.size() - 1; i >= 0; i--) {
            int splitAt = splitPoints.get(i);
            textOnCurrentPage = fullText.substring(0, splitAt).stripTrailing();
            textForNextPage   = fullText.substring(splitAt);

            // Temporarily set the editor text to measure the resulting height.
            // handlingOverflow is true here so the listener won't re-enter.
            current.editor.replaceText(textOnCurrentPage);

            double h = current.editor.totalHeightEstimateProperty().orElse(0.0).getValue();
            if (h <= usable || splitAt == 0) {
                // This split fits (or we've reduced to nothing — accept it).
                break;
            }
        }

        if (textForNextPage.isEmpty()) return;

        // Strip a single leading newline that was the paragraph separator
        if (textForNextPage.startsWith("\n")) {
            textForNextPage = textForNextPage.substring(1);
        }

        // Prepend the overflow text in front of whatever was already on the next page
        String existing    = next.editor.getText();
        String newNextText = existing.isEmpty()
                ? textForNextPage
                : textForNextPage + "\n" + existing;

        next.editor.replaceText(newNextText);
        next.editor.requestFocus();
        // Place caret at the end of the newly-inserted overflow text
        int caretPos = textForNextPage.length();
        next.editor.moveTo(Math.min(caretPos, newNextText.length()));
    }
}