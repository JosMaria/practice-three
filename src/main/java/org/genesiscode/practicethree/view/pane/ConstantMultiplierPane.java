package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class ConstantMultiplierPane extends MyPane {

    private static ConstantMultiplierPane constantMultiplierPane;
    private VBox mainPane;

    private ConstantMultiplierPane() {
        super("Multiplicador Constante");
        buildPane();
    }

    public synchronized static ConstantMultiplierPane getInstance() {
        return constantMultiplierPane == null ? new ConstantMultiplierPane() : constantMultiplierPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
