/**
 *******************************************************************************
 * Author: Aahil Bashar
 * Creation Date: May 16, 2026
 * Modified Date: May 21, 2026
 * Description: Manages the ClearMind paginated notes interface. Handles dynamic
 * 		page creation, overflow detection, and automatic text flow across
 * 		multiple pages using JavaFX layout measurements.
 *******************************************************************************
 */
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

    /*
     * Method Name: open
     * Author: Aahil Bashar
     * Creation Date: May 16, 2026
     * Modified Date: May 21, 2026
     * Description: Initializes the Notes interface by creating a scrollable,
     * 				vertically stacked page container. Loads the first page,
     * 				attaches overflow listeners, and displays the scene.
     * Parameters: Stage stage (the window in which the Notes screen is displayed)
     * Return Value: None
     * Data Type: void
     * Dependencies: javafx.stage.Stage, javafx.scene.Scene, javafx.scene.control.ScrollPane,
     * 				 javafx.scene.layout.VBox, com.clearmind.PaginatedDocument
     * Throws/Exceptions: N/A
     */
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

    /*
     * Method Name: isVisuallyFull
     * Author: Aahil Bashar
     * Creation Date: May 16, 2026
     * Modified Date: May 21, 2026
     * Description: Determines whether a page's text content exceeds its usable
     * 				vertical space by forcing CSS/layout updates and measuring
     * 				the rendered content height.
     * Parameters: Page page (the page whose content height is evaluated)
     * Return Value: boolean, true if the page is full, false otherwise.
     * Data Type: boolean
     * Dependencies: javafx.scene.Node, com.clearmind.Page
     * Throws/Exceptions: N/A
     */
    private boolean isVisuallyFull(Page page) {
        page.editor.applyCss();
        page.editor.layout();

        var content = page.editor.lookup(".content");
        if (content == null) return false;

        double contentHeight = content.getBoundsInLocal().getHeight();
        double usableHeight = Page.HEIGHT - 2 * Page.PADDING;

        return contentHeight > usableHeight;
    }
    
    /*
     * Method Name: overflowToNextPage
     * Author: Aahil Bashar
     * Creation Date: May 17, 2026
     * Modified Date: May 21, 2026
     * Description: Moves overflowing text from the current page to the next page
     *              by iteratively removing characters until the page fits within
     *              its visual bounds.
     * Parameters: Page current  (the page that overflowed)
     * 			   Page next (the page receiving the overflow text)
     * Return Value: None
     * Data Type: void
     * Dependencies: com.clearmind.Page
     * Throws/Exceptions: N/A
     */
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
    
    /*
     * Method Name: attachListener
     * Author: Aahil Bashar
     * Creation Date: May 17, 2026
     * Modified Date: May 21, 2026
     * Description: Attaches a text listener to a page so that when the user types
     * 		    and the page becomes visually full, a new page is created and
     * 		    overflow text is automatically transferred.
     * Parameters: Page page (the page whose editor receives the listener)
     * Return Value: None
     * Data Type: void
     * Dependencies: javafx.application.Platform, com.clearmind.Page,
     * 		         com.clearmind.PaginatedDocument
     * Throws/Exceptions: N/A
     */
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
