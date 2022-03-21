package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class BlumBlumShubPane extends MyPane {

    private static BlumBlumShubPane blumBlumShubPane;
    private VBox mainPane;

    private BlumBlumShubPane() {
        super("Blum Blum Shub");
        buildPane();
    }

    public synchronized static BlumBlumShubPane getInstance() {
        return blumBlumShubPane == null ? new BlumBlumShubPane() : blumBlumShubPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
