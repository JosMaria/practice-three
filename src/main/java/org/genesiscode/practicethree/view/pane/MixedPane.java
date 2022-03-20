package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class MixedPane extends MyPane {

    private static MixedPane mixedPane;
    private VBox mainPane;

    private MixedPane() {
        super("Congruencial Mixto");
        buildPane();
    }

    public synchronized static MixedPane getInstance() {
        return mixedPane == null ? new MixedPane() : mixedPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
