package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class MiddleProductsPane extends MyPane {

    private static MiddleProductsPane middleProductsPane;
    private VBox mainPane;

    private MiddleProductsPane() {
        super("Productos Medios");
        buildPane();
    }

    public synchronized static MiddleProductsPane getInstance() {
        return middleProductsPane == null ? new MiddleProductsPane() : middleProductsPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
