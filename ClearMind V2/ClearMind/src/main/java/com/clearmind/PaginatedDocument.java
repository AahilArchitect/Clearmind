package com.clearmind;

import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of pages and manages adding/removing them from the UI container.
 */
public class PaginatedDocument {
    private final VBox container;
    private final List<Page> pages = new ArrayList<>();

    public PaginatedDocument(VBox container) {
        this.container = container;
        addPage();
    }

    public Page getLastPage() {
        return pages.get(pages.size() - 1);
    }

    public Page getPage(int index) {
        return pages.get(index);
    }

    public int getPageIndex(Page page) {
        return pages.indexOf(page);
    }

    public int getPageCount() {
        return pages.size();
    }

    public void addPage() {
        Page page = new Page();
        pages.add(page);
        container.getChildren().add(page);
    }

    /** Remove a page from both the data list and the UI. */
    public void removePage(Page page) {
        pages.remove(page);
        container.getChildren().remove(page);
    }
}