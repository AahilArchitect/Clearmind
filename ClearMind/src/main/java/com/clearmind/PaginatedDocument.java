/**
*********************************************************************************
 * Author: Aahil Bashar
 * Description: Manages a dynamic collection of Page objects for the ClearMind
 *              Notes system. Handles page creation, storage, and insertion into
 *              the UI container. Acts as the document-level controller that
 *              supports multi-page note-taking and overflow-driven pagination.
*********************************************************************************
 */
package com.clearmind;

import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class PaginatedDocument {

    private final VBox container;
    private final List<Page> pages = new ArrayList<>();
    
    /*
     * Method Name: PaginatedDocument (constructor)
     * Author: Aahil Bashar
     * Creation Date: May 17, 2026
     * Modified Date: May 21, 2026
     * Description: Initializes a new paginated document bound to a VBox
     *              container. Automatically creates the first Page and adds it
     *              to both the internal list and the UI layout.
     * Parameters: VBox container (the UI container that visually holds pages)
     * Return Value: A new PaginatedDocument instance
     * Data Type: PaginatedDocument
     * Dependencies: javafx.scene.layout.VBox, com.clearmind.Page
     * Throws/Exceptions: N/A
     */
    public PaginatedDocument(VBox container) {
        this.container = container;
        addPage();
    }

    /*
     * Method Name: getLastPage
     * Author: Aahil Bashar
     * Creation Date: May 17, 2026
     * Modified Date: May 21, 2026
     * Description: Retrieves the most recently added Page in the document.
     *              Used by the Notes system to attach listeners and handle
     *              overflow routing.
     * Parameters: None
     * Return Value: Page (the last page in the document)
     * Data Type: Page
     * Dependencies: java.util.List, com.clearmind.Page
     * Throws/Exceptions: N/A
     */
    public Page getLastPage() {
        return pages.get(pages.size() - 1);
    }

    /*
     * Method Name: addPage
     * Author: Aahil Bashar
     * Creation Date: May 17, 2026
     * Modified Date: May 21, 2026
     * Description: Creates a new Page, appends it to the internal list, and
     *              inserts it into the UI container. Supports dynamic pagination
     *              when text overflows existing pages.
     * Parameters: None
     * Return Value: None
     * Data Type: void
     * Dependencies: com.clearmind.Page, javafx.scene.layout.VBox
     * Throws/Exceptions: N/A
     */
    public void addPage() {
        Page page = new Page();
        pages.add(page);
        container.getChildren().add(page);
    }
}
