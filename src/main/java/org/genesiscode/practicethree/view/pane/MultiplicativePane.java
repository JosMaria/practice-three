package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.BinarySystem;
import org.genesiscode.practicethree.model.DecimalSystem;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.RowBinaryMultiplicative;
import org.genesiscode.practicethree.view.RowDecimalMultiplicative;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;

import java.util.List;

public class MultiplicativePane extends MyPane {

    private static MultiplicativePane multiplicativePane;
    private VBox mainPane, bottomPane;
    private TextField seedField, multiplicativeConstantField, moduleField;
    private Button startButton;
    private RadioButton binaryRadio, decimalRadio;
    private TableView<RowDecimalMultiplicative> decimalTable;
    private TableView<RowBinaryMultiplicative> binaryTable;
    private final DecimalSystem decimalSystem;
    private final BinarySystem binarySystem;

    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private MultiplicativePane() {
        super("Congruencial Multiplicativo");
        decimalSystem = new DecimalSystem();
        binarySystem = new BinarySystem();
        loadControls();
        buildPane();
    }

    public synchronized static MultiplicativePane getInstance() {
        return multiplicativePane == null ? new MultiplicativePane() : multiplicativePane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(10, new VBox(lblHeader),
                new HBox(20, new HBox(10, decimalRadio, binaryRadio), seedField, multiplicativeConstantField, moduleField),
                new HBox(startButton),
                bottomPane);
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
    }

    private void loadControls() {
        bottomPane = new VBox(10);
        bottomPane.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();
        decimalRadio = new RadioButton("decimal");
        decimalRadio.setSelected(true);
        decimalRadio.setToggleGroup(toggleGroup);
        binaryRadio = new RadioButton("binario");
        binaryRadio.setToggleGroup(toggleGroup);

        seedField = new TextField();
        seedField.setPromptText("Semilla");
        seedField.setTooltip(new Tooltip("Semilla"));
        seedField.setPrefColumnCount(SPACES);

        multiplicativeConstantField = new TextField();
        multiplicativeConstantField.setPromptText("Multiplicativa");
        multiplicativeConstantField.setTooltip(new Tooltip("Multiplicativa"));
        multiplicativeConstantField.setPrefColumnCount(SPACES);

        moduleField = new TextField();
        moduleField.setPromptText("Modulo");
        moduleField.setTooltip(new Tooltip("Modulo"));
        moduleField.setPrefColumnCount(SPACES);
        
        startButton = new Button("Comenzar");
        startButton.setOnAction(actionEvent -> startProcess());

        decimalTable = new TableView<>();
        binaryTable = new TableView<>();
    }

    private void startProcess() {
        try {
            int seed = Integer.parseInt(seedField.getText());
            int module = Integer.parseInt(moduleField.getText());
            int multiplicativeConstant = Integer.parseInt(multiplicativeConstantField.getText());

            if (binaryRadio.isSelected()) {
                updateBottomPane(getBinaryTable());
                binarySystem.loadData(multiplicativeConstant, seed, module);

            } else if (decimalRadio.isSelected()) {
                updateBottomPane(getDecimalTable());
                decimalSystem.loadData(seed, multiplicativeConstant, module);
            } else {
                MessageBox.show("Seleccionar una opcion", MESSAGE_FAILED);
            }
        } catch (NumberFormatException e) {
            MessageBox.show("Formato de las entradas no valida", MESSAGE_FAILED);
        } catch (IllegalArgumentException e) {
            MessageBox.show(e.getMessage(), MESSAGE_FAILED);
        } finally {
            if (binaryRadio.isSelected()) {
                binaryTable.setItems(binarySystem.getAllRows());
            } else if (decimalRadio.isSelected()) {
                decimalTable.setItems(decimalSystem.getAllRows());
            } else {
                MessageBox.show("Seleccionar una opcion", MESSAGE_FAILED);
            }
        }
    }

    private void updateBottomPane(Node node) {
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(node);
    }

    private TableView<RowDecimalMultiplicative> getDecimalTable() {
        TableColumn<RowDecimalMultiplicative, Integer> numberRow = new TableColumn<>("Number");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<RowDecimalMultiplicative, String> columnOne = new TableColumn<>("Xo");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<RowDecimalMultiplicative, String> columnTwo = new TableColumn<>("a * Xn");
        columnTwo.setMinWidth(100);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<RowDecimalMultiplicative, String> columnThree = new TableColumn<>("X n+1");
        columnThree.setMinWidth(100);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<RowDecimalMultiplicative, String> columnFour = new TableColumn<>("Un = X n+1/m");
        columnFour.setMinWidth(150);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        decimalTable.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour));
        return decimalTable;
    }

    private TableView<RowBinaryMultiplicative> getBinaryTable() {
        TableColumn<RowBinaryMultiplicative, Integer> numberRow = new TableColumn<>("n");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<RowBinaryMultiplicative, Integer> columnOne = new TableColumn<>("Xn");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));

        binaryTable.getColumns().addAll(List.of(numberRow, columnOne));
        return binaryTable;
    }
}
