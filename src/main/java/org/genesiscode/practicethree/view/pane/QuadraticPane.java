package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.Quadratic;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.Row;

import java.util.List;

public class QuadraticPane extends MyPane {

    private static QuadraticPane quadraticPane;
    private VBox mainPane;
    private TextField aField, bField, cField, mField, seedField;
    private Button startButton;
    private TableView<Row> table = new TableView<>();

    private final Quadratic quadratic;
    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private QuadraticPane() {
        super("Congruencial Cuadratico");
        quadratic = new Quadratic();
        loadControls();
        buildPane();
    }

    public synchronized static QuadraticPane getInstance() {
        return quadraticPane == null ? new QuadraticPane() : quadraticPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(10, new VBox(lblHeader),
                new HBox(20, aField, bField, cField, mField, seedField),
                new HBox(startButton),
                getTable());
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
    }

    private void loadControls() {
        aField = new TextField();
        aField.setPromptText("A");
        aField.setTooltip(new Tooltip("A"));
        aField.setPrefColumnCount(SPACES);

        bField = new TextField();
        bField.setPromptText("B");
        bField.setTooltip(new Tooltip("B"));
        bField.setPrefColumnCount(SPACES);

        cField = new TextField();
        cField.setPromptText("C");
        cField.setTooltip(new Tooltip("C"));
        cField.setPrefColumnCount(SPACES);

        mField = new TextField();
        mField.setPromptText("Modulo");
        mField.setTooltip(new Tooltip("Modulo"));
        mField.setPrefColumnCount(SPACES);

        seedField = new TextField();
        seedField.setPromptText("Semilla");
        seedField.setTooltip(new Tooltip("Semilla"));
        seedField.setPrefColumnCount(SPACES);

        startButton = new Button("Empezar");
        startButton.setOnAction(actionEvent -> startProcess());

        table = new TableView<>();
    }

    private void startProcess() {
        if (existInput(aField) && existInput(bField) && existInput(cField) && existInput(mField) && existInput(seedField)) {
            try {
                quadratic.loadData(Integer.parseInt(aField.getText()), Integer.parseInt(bField.getText()),
                        Integer.parseInt(cField.getText()), Long.parseLong(mField.getText()), Long.parseLong(seedField.getText()));
            } catch (IllegalArgumentException e) {
                MessageBox.show(e.getMessage(), MESSAGE_FAILED);
            } finally {
                table.setItems(quadratic.getAllRows());
            }
        } else {
            MessageBox.show("Introducir las variables de entrada.", MESSAGE_FAILED);
        }
    }

    private boolean existInput(TextField input) {
        return input.getText().trim().length() > 0;
    }

    private TableView<Row> getTable() {
        TableColumn<Row, Integer> numberRow = new TableColumn<>("Xi");
        numberRow.setMinWidth(50);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<Row, String> columnOne = new TableColumn<>("ax² + bx + c mod m");
        columnOne.setMinWidth(350);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<Row, String> columnTwo = new TableColumn<>("Ri");
        columnTwo.setMinWidth(100);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo));
        return table;
    }
}
