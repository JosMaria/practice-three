package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class AdditivePane extends MyPane {

    private static AdditivePane additivePane;
    private VBox mainPane;

    private AdditivePane() {
        super("Congruencial Aditivo");
        buildPane();
    }

    public synchronized static AdditivePane getInstance() {
        return additivePane == null ? new AdditivePane() : additivePane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
