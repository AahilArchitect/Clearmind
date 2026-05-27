package com.clearmind;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import org.fxmisc.richtext.InlineCssTextArea;

public class Page extends StackPane {

    public static final double WIDTH   = 700;
    public static final double HEIGHT  = 1000;
    public static final double PADDING = 40;

    public InlineCssTextArea editor;

    public Page() {
        setPrefSize(WIDTH, HEIGHT);
        setMaxSize(WIDTH, HEIGHT);
        setMinSize(WIDTH, HEIGHT);

        setStyle("""
            -fx-background-color: white;
            -fx-border-color: #d0d0d0;
            -fx-border-width: 1;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 6, 0, 0, 2);
        """);

        editor = new InlineCssTextArea();
        editor.setWrapText(true);
        editor.setPadding(new Insets(PADDING));
        editor.setStyle("""
            -fx-background-color: transparent;
            -fx-font-family: 'Times New Roman';
            -fx-font-size: 16px;
            -fx-fill: black;
        """);

        // Fixed width; NO fixed height so totalHeightEstimateProperty
        // reports real content height instead of the pref height.
        editor.setPrefWidth(WIDTH - 2 * PADDING);
        editor.setMaxWidth(WIDTH - 2 * PADDING);

        // Disable the editor's own scrollbars — overflow goes to a new page instead
        editor.setStyle(editor.getStyle() + "-fx-vbar-policy: never; -fx-hbar-policy: never;");

        getChildren().add(editor);
        StackPane.setAlignment(editor, Pos.TOP_LEFT);
        StackPane.setMargin(editor, new Insets(PADDING));
    }
}