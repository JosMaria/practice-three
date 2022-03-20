package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.view.utils.Row;

import java.util.List;

public class MiddleSquaresPane extends MyPane {

    private static MiddleSquaresPane middleSquaresPane;
    private VBox mainPane;

    private TextField limitField, seedField;
    private Button startButton;
    private TableView<Row> table;

    private MiddleSquaresPane() {
        super("Cuadrados Medios");
        loadControls();
        buildPane();
    }

    public synchronized static MiddleSquaresPane getInstance() {
        return middleSquaresPane == null ? new MiddleSquaresPane() : middleSquaresPane;
    }
    
    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(10, new VBox(lblHeader),
                                    new HBox(20, seedField, limitField),
                                    new HBox(startButton),
                                    getTable());
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));

    }

    private void loadControls() {
        limitField = new TextField();
        limitField.setPromptText("Limite");
        limitField.setTooltip(new Tooltip("Limite"));
        limitField.setPrefColumnCount(SPACES);

        seedField = new TextField();
        seedField.setPromptText("Semilla");
        seedField.setTooltip(new Tooltip("Semilla"));
        seedField.setPrefColumnCount(SPACES);

        startButton = new Button("Empezar");
        startButton.setOnAction(actionEvent -> startProcess());

        table = new TableView<>();
    }

    private TableView<Row> getTable() {
        TableColumn<Row, Integer> numberRow = new TableColumn<>("Number");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<Row, String> columnOne = new TableColumn<>("X");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<Row, String> columnTwo = new TableColumn<>("X*X = X²");
        columnTwo.setMinWidth(200);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<Row, String> columnThree = new TableColumn<>("Zeros to left");
        columnThree.setMinWidth(200);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<Row, String> columnFour = new TableColumn<>("X n+1");
        columnFour.setMinWidth(100);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        TableColumn<Row, String> columnFive = new TableColumn<>("Ri");
        columnFive.setMinWidth(100);
        columnFive.setCellValueFactory(new PropertyValueFactory<>("ColumnFive"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour, columnFive));
        return table;
    }

    private void startProcess() {

    }
}
