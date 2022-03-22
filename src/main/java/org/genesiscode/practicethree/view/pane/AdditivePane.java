package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.Additive;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.Row;

import java.util.List;

public class AdditivePane extends MyPane {

    private static AdditivePane additivePane;
    private VBox mainPane;
    private TextField limitField, moduleField, numberField, sequenceField;
    private Button addButton, clearButton, startButton;
    private TableView<Row> table;

    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private final Additive additive;

    private AdditivePane() {
        super("Congruencial Aditivo");
        additive = new Additive();
        loadControls();
        buildPane();
    }

    public synchronized static AdditivePane getInstance() {
        return additivePane == null ? new AdditivePane() : additivePane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(10, new VBox(lblHeader),
                new HBox(20, limitField, moduleField, numberField, addButton),
                new HBox(20, new Label("Secuencia"), sequenceField, clearButton, startButton),
                getTable());
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
    }

    private void loadControls() {
        limitField = new TextField();
        limitField.setPromptText("Limite");
        limitField.setTooltip(new Tooltip("Limite"));
        limitField.setPrefColumnCount(SPACES);

        moduleField = new TextField();
        moduleField.setPromptText("Modulo");
        moduleField.setTooltip(new Tooltip("Modulo"));
        moduleField.setPrefColumnCount(SPACES);

        numberField = new TextField();
        numberField.setPromptText("Numero");
        numberField.setTooltip(new Tooltip("Numero"));
        numberField.setPrefColumnCount(SPACES);

        sequenceField = new TextField();
        sequenceField.setPromptText("secuencia");
        sequenceField.setTooltip(new Tooltip("secuencia"));
        sequenceField.setPrefColumnCount(20);
        sequenceField.setEditable(false);

        addButton = new Button("Agregar");
        addButton.setOnAction(actionEvent -> click_btn_add());
        clearButton = new Button("Limpiar");
        clearButton.setOnAction(actionEvent -> click_btn_clear());
        startButton = new Button("Comenzar");
        startButton.setOnAction(actionEvent -> click_btn_start());

        table = new TableView<>();
    }

    private void click_btn_start() {
        String inputLimit = limitField.getText().trim();
        String inputModule = moduleField.getText().trim();
        String inputSequence = sequenceField.getText();

        try {
            verifyFieldEmpty(inputLimit, inputModule, inputSequence);
            additive.setModule(Integer.parseInt(inputModule));
            additive.setCountOfNumberToGenerate(Integer.parseInt(inputLimit));
            table.setItems(additive.getAllRows());
        } catch (IllegalArgumentException e) {
            MessageBox.show(e.getMessage(), MESSAGE_FAILED);
        }
        sequenceField.setText("");
    }

    private void verifyFieldEmpty(String inputLimit, String inputModule, String inputSequence) throws IllegalArgumentException {
        if (inputLimit.length() <= 0) {
            throw new IllegalArgumentException("Introducir un valor en el limite");
        } else if (inputModule.length() <= 0) {
            throw new IllegalArgumentException("Introducir un valor al modulo");
        } else if (inputSequence.length() <= 0) {
            throw new IllegalArgumentException("Introducir un valor para la secuencia");
        }
    }

    private void click_btn_clear() {
        sequenceField.setText("");
        additive.clear();
    }

    private void click_btn_add() {
        String inputNumber = numberField.getText().trim();
        if (inputNumber.length() <= 0) {
            MessageBox.show("Ingrese datos en el campo de texto del numero", MESSAGE_FAILED);
        } else {
            try {
                additive.addNumber(Integer.parseInt(inputNumber));
            } catch (IllegalArgumentException e) {
                MessageBox.show("Formato del numero que se va a agregar no valido", MESSAGE_FAILED);
            }
        }

        if (sequenceField.getText().length() > 0)
            sequenceField.setText(sequenceField.getText() + " " + inputNumber);
        else {
            sequenceField.setText(inputNumber);
        }
        numberField.setText("");
    }

    private TableView<Row> getTable() {
        TableColumn<Row, Integer> numberRow = new TableColumn<>("i");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<Row, String> columnOne = new TableColumn<>("X (i-1)");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<Row, String> columnTwo = new TableColumn<>("X (i-n)");
        columnTwo.setMinWidth(200);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<Row, String> columnThree = new TableColumn<>("X(i-1) + X(i-n)");
        columnThree.setMinWidth(200);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<Row, String> columnFour = new TableColumn<>("mod m");
        columnFour.setMinWidth(100);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        TableColumn<Row, String> columnFive = new TableColumn<>("Ri");
        columnFive.setMinWidth(100);
        columnFive.setCellValueFactory(new PropertyValueFactory<>("ColumnFive"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour, columnFive));
        return table;
    }
}
