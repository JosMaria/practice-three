package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.ConstantMultiplier;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.Row;

import java.util.List;

public class ConstantMultiplierPane extends MyPane {

    private static ConstantMultiplierPane constantMultiplierPane;
    private VBox mainPane;
    private TextField limitField, seedField, constantField;
    private Button startButton;
    private TableView<Row> table;

    private final ConstantMultiplier constantMultiplier;
    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private ConstantMultiplierPane() {
        super("Multiplicador Constante");
        constantMultiplier = new ConstantMultiplier();
        loadControls();
        buildPane();
    }

    public synchronized static ConstantMultiplierPane getInstance() {
        return constantMultiplierPane == null ? new ConstantMultiplierPane() : constantMultiplierPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(10, new VBox(lblHeader),
                new HBox(20, seedField, constantField, limitField),
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

        constantField = new TextField();
        constantField.setPromptText("Constante");
        constantField.setTooltip(new Tooltip("Constante"));
        constantField.setPrefColumnCount(SPACES);

        startButton = new Button("Empezar");
        startButton.setOnAction(actionEvent -> startProcess());

        table = new TableView<>();
    }

    private void startProcess() {
        String inputLimit = limitField.getText().trim();
        String inputSeed = seedField.getText().trim();
        String inputConstant = constantField.getText().trim();

        if (inputConstant.isEmpty() || inputLimit.isEmpty() || inputSeed.isEmpty()) {
            MessageBox.show("Introducir las variables de entrada.", MESSAGE_FAILED);
        } else {
            try {
                constantMultiplier.loadData(Long.parseLong(inputSeed), Long.parseLong(inputConstant), Integer.parseInt(inputLimit));
                table.setItems(constantMultiplier.getAllRows());
            } catch (NumberFormatException e) {
                MessageBox.show("Formato de las entradas no valida", MESSAGE_FAILED);
            } catch(IllegalArgumentException e) {
                MessageBox.show(e.getMessage(), MESSAGE_FAILED);
            }
        }
    }

    private TableView<Row> getTable() {
        TableColumn<Row, Integer> numberRow = new TableColumn<>("Number");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<Row, String> columnOne = new TableColumn<>("a");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<Row, String> columnTwo = new TableColumn<>("X");
        columnTwo.setMinWidth(200);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<Row, String> columnThree = new TableColumn<>("a * X");
        columnThree.setMinWidth(200);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<Row, String> columnFour = new TableColumn<>("X (n+1)");
        columnFour.setMinWidth(100);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        TableColumn<Row, String> columnFive = new TableColumn<>("Ri");
        columnFive.setMinWidth(100);
        columnFive.setCellValueFactory(new PropertyValueFactory<>("ColumnFive"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour, columnFive));
        return table;
    }
}
