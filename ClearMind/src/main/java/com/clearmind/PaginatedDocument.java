package com.clearmind;

import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

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

    public void addPage() {
        Page page = new Page();
        pages.add(page);
        container.getChildren().add(page);
    }
}
