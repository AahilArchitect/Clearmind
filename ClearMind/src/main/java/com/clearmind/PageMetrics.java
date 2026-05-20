package com.clearmind;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageMetrics {

    public static int getMaxLines(Font font) {
        Text temp = new Text("A");
        temp.setFont(font);
        double lineHeight = temp.getLayoutBounds().getHeight();

        double usableHeight = Page.HEIGHT - 2 * Page.PADDING;
        return (int) (usableHeight / lineHeight);
    }

    public static int getCharsPerLine(Font font) {
        Text temp = new Text("A");
        temp.setFont(font);
        double charWidth = temp.getLayoutBounds().getWidth();

        double usableWidth = Page.WIDTH - 2 * Page.PADDING;
        return (int) (usableWidth / charWidth);
    }

    public static int getTotalChars(Font font) {
        return getMaxLines(font) * getCharsPerLine(font);
    }
}
