package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class MultiplicativePane extends MyPane {

    private static MultiplicativePane multiplicativePane;
    private VBox mainPane;

    private MultiplicativePane() {
        super("Congruencial Multiplicativo");
        buildPane();
    }

    public synchronized static MultiplicativePane getInstance() {
        return multiplicativePane == null ? new MultiplicativePane() : multiplicativePane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
