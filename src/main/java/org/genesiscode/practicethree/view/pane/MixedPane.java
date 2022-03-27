package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.Mixed;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.Row;

import java.util.List;

public class MixedPane extends MyPane {

    private static MixedPane mixedPane;
    private VBox mainPane, radioButtonPane;
    private TextField seedField, multiplicativeConstantField, additiveConstantField, moduleField, kField;
    private Button startButton;
    private TableView<Row> table = new TableView<>();
    private RadioButton aRadio, kRadio;
    private final Mixed mixed;

    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private MixedPane() {
        super("Congruencial Mixto");
        mixed = new Mixed();
        loadControls();
        buildRadioButtonPane();
        buildPane();
    }

    public synchronized static MixedPane getInstance() {
        return mixedPane == null ? new MixedPane() : mixedPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(10, new VBox(lblHeader),
                                    new HBox(20, seedField, additiveConstantField, moduleField, radioButtonPane),
                                    new HBox(startButton),
                                    getTable());
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
    }

    public void buildRadioButtonPane() {
        kField = new TextField();
        kField.setPromptText("K");
        kField.setTooltip(new Tooltip("K"));
        kField.setPrefColumnCount(SPACES - 2);
        kField.setVisible(false);

        ToggleGroup toggleGroup = new ToggleGroup();
        aRadio = new RadioButton("a");
        aRadio.setToggleGroup(toggleGroup);
        aRadio.setSelected(true);
        aRadio.setOnAction(actionEvent -> {
            multiplicativeConstantField.setVisible(true);
            kField.setVisible(false);
        });

        kRadio = new RadioButton("k");
        kRadio.setToggleGroup(toggleGroup);
        kRadio.setOnAction(actionEvent -> {
            multiplicativeConstantField.setVisible(false);
            kField.setVisible(true);
        });

        radioButtonPane = new VBox(10, new HBox(30, aRadio, kRadio),
                                        new HBox(10, multiplicativeConstantField, kField));
    }

    private void loadControls() {
        seedField = new TextField();
        seedField.setPromptText("Semilla");
        seedField.setTooltip(new Tooltip("Semilla"));
        seedField.setPrefColumnCount(SPACES);

        multiplicativeConstantField = new TextField();
        multiplicativeConstantField.setPromptText("Multiplicativa");
        multiplicativeConstantField.setTooltip(new Tooltip("Multiplicativa"));
        multiplicativeConstantField.setPrefColumnCount(SPACES - 2);

        additiveConstantField = new TextField();
        additiveConstantField.setPromptText("Aditiva");
        additiveConstantField.setTooltip(new Tooltip("Aditiva"));
        additiveConstantField.setPrefColumnCount(SPACES);

        moduleField = new TextField();
        moduleField.setPromptText("Modulo");
        moduleField.setTooltip(new Tooltip("Modulo"));
        moduleField.setPrefColumnCount(SPACES);

        startButton = new Button("Comenzar");
        startButton.setOnAction(actionEvent -> startProcess());

        table = new TableView<>();
    }

    private void startProcess() {
        String inputSeed = seedField.getText().trim();
        String inputAdditive = additiveConstantField.getText().trim();
        String inputModule = moduleField.getText().trim();

        if (inputSeed.isEmpty() || inputAdditive.isEmpty() || inputModule.isEmpty()) {
            MessageBox.show("Introducir las variables de entrada.", MESSAGE_FAILED);
        } else {
            int module = 0;
            try {
                module = Integer.parseInt(inputModule);
                mixed.loadData(Integer.parseInt(inputSeed), getMultiplicativeConstant(), Integer.parseInt(inputAdditive), module);
                table.setItems(mixed.getAllRows());
                if (module != table.getItems().size() - 1) {
                    MessageBox.show("Periodo Incompleto", "INCOMPLETO");
                }
            } catch (NumberFormatException e) {
                MessageBox.show("Formato de las entradas no valida", MESSAGE_FAILED);
            } catch(IllegalArgumentException e) {
                MessageBox.show(e.getMessage(), MESSAGE_FAILED);
            } finally {
                table.setItems(mixed.getAllRows());
                if (module != table.getItems().size() - 1) {
                    MessageBox.show("Periodo Incompleto", "INCOMPLETO");
                }
            }
        }
    }
    
    private int getMultiplicativeConstant() {
        int multiplicativeConstant;
        if (aRadio.isSelected()) {
            String inputMultiplicativeConstant = multiplicativeConstantField.getText().trim();
            multiplicativeConstant = Integer.parseInt(inputMultiplicativeConstant);
        } else if (kRadio.isSelected()) {
            String inputK = kField.getText().trim();
            int valueK = Integer.parseInt(inputK);
            multiplicativeConstant = 1 + (4 * valueK);
        } else {
            throw new IllegalArgumentException("Introducir el valor para el modulo");
        }
        return multiplicativeConstant;
    }

    private TableView<Row> getTable() {
        TableColumn<Row, Integer> numberRow = new TableColumn<>("Number");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<Row, String> columnOne = new TableColumn<>("Xo");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<Row, String> columnTwo = new TableColumn<>("a * Xn");
        columnTwo.setMinWidth(100);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<Row, String> columnThree = new TableColumn<>("a * Xn + c");
        columnThree.setMinWidth(100);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<Row, String> columnFour = new TableColumn<>("X n+1");
        columnFour.setMinWidth(100);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        TableColumn<Row, String> columnFive = new TableColumn<>("Un = X n+1/m");
        columnFive.setMinWidth(200);
        columnFive.setCellValueFactory(new PropertyValueFactory<>("ColumnFive"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour, columnFive));
        return table;
    }
}
