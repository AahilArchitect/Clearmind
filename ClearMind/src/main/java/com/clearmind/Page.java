/*********************************************************************************
 * Author: Aahil Bashar
 * Description: Represents a single editable page in the ClearMind Notes system.
 * 				Provides a fixed-size writing surface with custom styling,
 * 				padding, and a transparent TextArea that visually mimics a
 * 				physical sheet of paper. Used by PaginatedDocument to support
 * 				multi-page note-taking.
********************************************************************************
 */
package com.clearmind;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class Page extends StackPane {

    public static final double WIDTH = 700;
    public static final double HEIGHT = 1000;
    public static final double PADDING = 40;

    public TextArea editor;
    
    /*
     * Method Name: Page (constructor)
     * Author: Aahil Bashar
     * Creation Date: May 16, 2026
     * Modified Date: May 21, 2026
     * Description: Initializes a new Page with fixed dimensions, a white
     * 				background, a light border, and a fully transparent TextArea
     * 				that fills the page. The TextArea is styled to resemble
     * 				traditional writing paper and is configured to disable
     * 				internal scrolling so that overflow is handled externally.
     * Parameters: None
     * Return Value: A new Page instance
     * Data Type: Page
     * Dependencies: javafx.scene.control.TextArea, javafx.geometry.Insets,
     * 		         javafx.scene.layout.StackPane
     * Throws/Exceptions: N/A
     */
    public Page() {
        setPrefSize(WIDTH, HEIGHT);
        setMaxSize(WIDTH, HEIGHT);

        setStyle("""
            -fx-background-color: white;
            -fx-border-color: #d0d0d0;
            -fx-border-width: 1;
        """);

        editor = new TextArea();
        editor.setWrapText(true);
        editor.setPadding(new Insets(PADDING));
        editor.setStyle("""
        	    -fx-background-color: transparent;
        	    -fx-control-inner-background: transparent;
        	    -fx-border-color: transparent;
        	    -fx-focus-color: transparent;
        	    -fx-faint-focus-color: transparent;
        	    -fx-highlight-fill: #c8c8c8;   /* light grey selection */
        	    -fx-highlight-text-fill: black;
        	    -fx-text-fill: black;
        	    -fx-font-family: 'Times New Roman';
        	    -fx-font-size: 16px;
        	""");

        // Disable internal scroll
        editor.setPrefRowCount(Integer.MAX_VALUE);
        editor.setPrefColumnCount(Integer.MAX_VALUE);
        editor.setScrollTop(Double.MIN_VALUE);
        editor.setScrollLeft(Double.MIN_VALUE);

        editor.setPrefSize(WIDTH, HEIGHT);
        editor.setMaxSize(WIDTH, HEIGHT);

        getChildren().add(editor);
    }
}
