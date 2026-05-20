package com.clearmind;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Notes {

    private PaginatedDocument document;
    private VBox pagesContainer;

    private boolean handlingOverflow = false;

    public void open(Stage stage) {

        pagesContainer = new VBox(40);
        pagesContainer.setAlignment(Pos.TOP_CENTER);

        document = new PaginatedDocument(pagesContainer);

        Page firstPage = document.getLastPage();
        attachListener(firstPage);

        ScrollPane scroll = new ScrollPane(pagesContainer);
        scroll.setFitToWidth(true);

        stage.setScene(new Scene(scroll, 1000, 900));
        stage.setTitle("ClearMind – Notes");
        stage.show();
    }

    private boolean isVisuallyFull(Page page) {
        page.editor.applyCss();
        page.editor.layout();

        var content = page.editor.lookup(".content");
        if (content == null) return false;

        double contentHeight = content.getBoundsInLocal().getHeight();
        double usableHeight = Page.HEIGHT - 2 * Page.PADDING;

        return contentHeight > usableHeight;
    }

    private void overflowToNextPage(Page current, Page next) {
        String text = current.editor.getText();
        StringBuilder overflow = new StringBuilder();

        // Move characters until the page fits
        while (!text.isEmpty()) {
            if (!isVisuallyFull(current)) break;

            char c = text.charAt(text.length() - 1);
            overflow.insert(0, c);
            text = text.substring(0, text.length() - 1);
            current.editor.setText(text);
        }

        next.editor.setText(overflow + next.editor.getText());
        next.editor.requestFocus();
        next.editor.positionCaret(overflow.length());
    }

    private void attachListener(Page page) {
        page.editor.textProperty().addListener((obs, oldVal, newVal) -> {

            if (handlingOverflow) return;

            Platform.runLater(() -> {
                if (isVisuallyFull(page)) {

                    handlingOverflow = true;

                    document.addPage();
                    Page next = document.getLastPage();
                    attachListener(next);

                    overflowToNextPage(page, next);

                    handlingOverflow = false;
                }
            });
        });
    }
}
