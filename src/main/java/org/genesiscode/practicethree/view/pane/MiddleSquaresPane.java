package org.genesiscode.practicethree.view.pane;

import javafx.scene.layout.VBox;

public class MiddleSquaresPane extends MyPane {

    private static MiddleSquaresPane middleSquaresPane;
    private VBox mainPane;
    
    private MiddleSquaresPane() {
        super("Cuadrados Medios");
        buildPane();
    }

    public synchronized static MiddleSquaresPane getInstance() {
        return middleSquaresPane == null ? new MiddleSquaresPane() : middleSquaresPane;
    }
    
    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(lblHeader);
    }
}
