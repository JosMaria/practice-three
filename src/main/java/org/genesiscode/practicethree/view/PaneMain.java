package org.genesiscode.practicethree.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaneMain extends Application implements EventHandler<ActionEvent> {

    @Override
    public void start(Stage stage) {
        Button button = new Button("Hello World!");
        button.setOnAction(this);
        VBox pane = new VBox(button);
        Scene scene = new Scene(pane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        MessageBox.show("Hello Jose Maria", "title");
    }
}
