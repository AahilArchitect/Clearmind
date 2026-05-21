/**
 *****************************************************************************
 * Author: Aahil Bashar
 * Description: Utility class that calculates text capacity metrics for a Page
 *              based on font measurements. Determines the maximum number of
 *              lines, characters per line, and total characters that can fit
 *              within the usable writing area of a Page. Used by the Notes
 *              system to support pagination logic and overflow handling.
 *****************************************************************************
 */

package com.clearmind;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageMetrics {
	
    /*
  * Method Name: getMaxLines
  * Author: Aahil Bashar
  * Creation Date: May 17, 2026
  * Modified Date: May 21, 2026
  * Description: Calculates the maximum number of text lines that can fit on a
  *              Page by measuring the rendered height of a single character
  *              using the specified font.
  * Parameters: Font font (the font used to compute line height)
  * Return Value: int (the maximum number of lines that fit vertically)
  * Data Type: int
  * Dependencies: javafx.scene.text.Text, com.clearmind.Page
  * Throws/Exceptions: N/A
  */
    public static int getMaxLines(Font font) {
        Text temp = new Text("A");
        temp.setFont(font);
        double lineHeight = temp.getLayoutBounds().getHeight();

        double usableHeight = Page.HEIGHT - 2 * Page.PADDING;
        return (int) (usableHeight / lineHeight);
    }
    
    /*
     * Method Name: getCharsPerLine
     * Author: Aahil Bashar
     * Creation Date: May 17, 2026
     * Modified Date: May 21, 2026
     * Description: Estimates the maximum number of characters that can fit on a
     *              single line of a Page by measuring the width of a single
     *              character in the given font.
     * Parameters: Font font (the font used to compute character width)
     * Return Value: int (the number of characters that fit horizontally)
     * Data Type: int
     * Dependencies: javafx.scene.text.Text, com.clearmind.Page
     * Throws/Exceptions: N/A
     */
    public static int getCharsPerLine(Font font) {
        Text temp = new Text("A");
        temp.setFont(font);
        double charWidth = temp.getLayoutBounds().getWidth();

        double usableWidth = Page.WIDTH - 2 * Page.PADDING;
        return (int) (usableWidth / charWidth);
    }

    /*
     * Method Name: getTotalChars
     * Author: Aahil Bashar
     * Creation Date: May 17, 2026
     * Modified Date: May 21, 2026
     * Description: Computes the total number of characters that can fit on a
     * 				Page by multiplying the maximum lines by the characters per
     * 				line. Provides a rough capacity estimate for pagination.
     * Parameters: Font font (the font used for all calculations)
     * Return Value: int (total estimated character capacity of a Page)
     * Data Type: int
     * Dependencies: PageMetrics.getMaxLines, PageMetrics.getCharsPerLine
     * Throws/Exceptions: N/A
	*/
    public static int getTotalChars(Font font) {
        return getMaxLines(font) * getCharsPerLine(font);
    }
}
