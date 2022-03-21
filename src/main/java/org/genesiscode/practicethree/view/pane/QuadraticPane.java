package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class QuadraticPane extends MyPane {

    private static QuadraticPane quadraticPane;
    private VBox mainPane;

    private QuadraticPane() {
        super("Congruencial Cuadratico");
        buildPane();
    }

    public synchronized static QuadraticPane getInstance() {
        return quadraticPane == null ? new QuadraticPane() : quadraticPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
