package org.genesiscode.practicethree.view.pane;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class MyPane {

    protected Label lblHeader;

    protected MyPane(String titleHeader) {
        lblHeader = new Label(titleHeader);
        lblHeader.setFont(new Font("Gargi", 20));
    }
}
