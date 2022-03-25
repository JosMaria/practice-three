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
    private TextField limitField, pField, qField, mField, seedField;
    private VBox radioButtonPane;
    private Button startButton;
    private RadioButton pByQRadio, mRadio;
    private TableView<RowMultiplicativeAndBlum> table;
    private final BlumBlumShub blumBlumShub;

    private static BlumBlumShubPane blumBlumShubPane;
    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private BlumBlumShubPane() {
        super("Blum Blum Shub");
        blumBlumShub = new BlumBlumShub();
        loadControls();
        buildRadioButtonPane();
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
                new HBox(15, seedField, limitField, radioButtonPane),
                new HBox(startButton),
                getTable());
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
    }

    private VBox leftRadioPane, rightRadioPane;

    public void buildRadioButtonPane() {

        ToggleGroup toggleGroup = new ToggleGroup();
        pByQRadio = new RadioButton("p * q\t");
        pByQRadio.setToggleGroup(toggleGroup);
        pByQRadio.setSelected(true);
        pByQRadio.setOnAction(actionEvent -> {
            leftRadioPane.setVisible(true);
            rightRadioPane.setVisible(false);
        });

        mRadio = new RadioButton(" m ");
        mRadio.setToggleGroup(toggleGroup);
        mRadio.setOnAction(actionEvent -> {
            rightRadioPane.setVisible(true);
            leftRadioPane.setVisible(false);
        });

        leftRadioPane = new VBox(5, pField, qField);
        rightRadioPane = new VBox(mField);
        rightRadioPane.setVisible(false);

        radioButtonPane = new VBox(10, new HBox(10, pByQRadio, mRadio),
                                            new HBox(leftRadioPane, rightRadioPane));
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

        mField = new TextField();
        mField.setPromptText("M");
        mField.setTooltip(new Tooltip("M"));
        mField.setPrefColumnCount(SPACES);

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
        String inputSeed = seedField.getText().trim();

        try {
            verifyFieldEmpty(inputSeed, inputLimit);
            blumBlumShub.loadData(Integer.parseInt(inputSeed), getValueM(), Integer.parseInt(inputLimit));
            table.setItems(blumBlumShub.getAllRows());
        } catch (IllegalArgumentException e) {
            MessageBox.show(e.getMessage(), MESSAGE_FAILED);
        }
    }

    private int getValueM() {
        int mInputCalculated = 0;

        if (pByQRadio.isSelected()) {
            String inputP = pField.getText().trim();
            String inputQ = qField.getText().trim();
            try {
                mInputCalculated = Integer.parseInt(inputP) * Integer.parseInt(inputQ);
            } catch (NumberFormatException e) {
                MessageBox.show("P y Q deben ser enteros", MESSAGE_FAILED);
            }
        } else if (mRadio.isSelected()) {
            try {
                mInputCalculated = Integer.parseInt(mField.getText().trim());
            } catch (NumberFormatException e) {
                MessageBox.show("M debe ser entero", MESSAGE_FAILED);
            }
        }
        return mInputCalculated;
    }

    private void verifyFieldEmpty(String seed, String limit) throws IllegalArgumentException {
        String message = "Introducir el valor %s";
        if (seed.length() <= 0) {
            throw new IllegalArgumentException(String.format(message, "de la semilla"));
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
