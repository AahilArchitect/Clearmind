package com.clearmind;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class Page extends StackPane {

    public static final double WIDTH = 700;
    public static final double HEIGHT = 1000;
    public static final double PADDING = 40;

    public TextArea editor;

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
