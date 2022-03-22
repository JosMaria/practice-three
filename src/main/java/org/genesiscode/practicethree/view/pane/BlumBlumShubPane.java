package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.BlumBlumShub;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;

import java.util.List;

public class BlumBlumShubPane extends MyPane {

    private VBox mainPane;
    private TextField limitField, pField, qField, seedField;
    private Button startButton;
    private TableView<RowMultiplicativeAndBlum> table;
    private final BlumBlumShub blumBlumShub;

    private static BlumBlumShubPane blumBlumShubPane;
    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private BlumBlumShubPane() {
        super("Blum Blum Shub");
        blumBlumShub = new BlumBlumShub();
        loadControls();
        buildPane();
    }

    public synchronized static BlumBlumShubPane getInstance() {
        return blumBlumShubPane == null ? new BlumBlumShubPane() : blumBlumShubPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(20, new VBox(lblHeader),
                new HBox(15, seedField, pField, qField, limitField),
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

        pField = new TextField();
        pField.setPromptText("P");
        pField.setTooltip(new Tooltip("P"));
        pField.setPrefColumnCount(SPACES);
        
        qField = new TextField();
        qField.setPromptText("Q");
        qField.setTooltip(new Tooltip("Q"));
        qField.setPrefColumnCount(SPACES);

        seedField = new TextField();
        seedField.setPromptText("Semilla");
        seedField.setTooltip(new Tooltip("Semilla"));
        seedField.setPrefColumnCount(SPACES);

        startButton = new Button("Comenzar");
        startButton.setOnAction(actionEvent -> startProcess());
        
        table = new TableView<>();
    }

    private void startProcess() {
        String inputLimit = limitField.getText().trim();
        String inputP = pField.getText().trim();
        String inputQ = qField.getText().trim();
        String inputSeed = seedField.getText().trim();

        try {
            verifyFieldEmpty(inputSeed, inputP, inputQ, inputLimit);
            blumBlumShub.loadData(Integer.parseInt(inputSeed), Integer.parseInt(inputP), Integer.parseInt(inputQ),
                    Integer.parseInt(inputLimit));
            table.setItems(blumBlumShub.getAllRows());
        } catch (IllegalArgumentException e) {
            MessageBox.show(e.getMessage(), MESSAGE_FAILED);
        }
    }

    private void verifyFieldEmpty(String seed, String p, String q, String limit) throws IllegalArgumentException {
        String message = "Introducir el valor %s";
        if (seed.length() <= 0) {
            throw new IllegalArgumentException(String.format(message, "de la semilla"));
        } else if (p.length() <= 0) {
            throw new IllegalArgumentException(String.format(message, "de p"));
        } else if (q.length() <= 0) {
            throw new IllegalArgumentException(String.format(message, "de q"));
        } else if(limit.length() <= 0) {
            throw new IllegalArgumentException(String.format(message, "del limite"));
        }
    }

    private TableView<RowMultiplicativeAndBlum> getTable() {
        TableColumn<RowMultiplicativeAndBlum, Integer> numberRow = new TableColumn<>("n");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<RowMultiplicativeAndBlum, String> columnOne = new TableColumn<>("Xi");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<RowMultiplicativeAndBlum, String> columnTwo = new TableColumn<>("(Xi)²");
        columnTwo.setMinWidth(200);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<RowMultiplicativeAndBlum, String> columnThree = new TableColumn<>("m = p * q");
        columnThree.setMinWidth(200);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<RowMultiplicativeAndBlum, String> columnFour = new TableColumn<>("(Xi)² mod m");
        columnFour.setMinWidth(100);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour));
        return table;
    }
}
