package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.Multiplicative;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;

import java.util.List;

public class MultiplicativePane extends MyPane {

    private static MultiplicativePane multiplicativePane;
    private VBox mainPane;
    private TextField seedField, multiplicativeConstantField, moduleField;
    private Button startButton;
    private TableView<RowMultiplicativeAndBlum> table;
    private final Multiplicative multiplicative;

    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private MultiplicativePane() {
        super("Congruencial Multiplicativo");
        multiplicative = new Multiplicative();
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
                new HBox(20, seedField, multiplicativeConstantField, moduleField),
                new HBox(startButton),
                getTable());
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
    }

    private void loadControls() {
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

        table = new TableView<>();
    }

    private void startProcess() {
        String inputSeed = seedField.getText().trim();
        String inputModule = moduleField.getText().trim();
        String inputMultiplicative = multiplicativeConstantField.getText().trim();

        if (inputSeed.length() > 0 && inputModule.length() > 0 && inputMultiplicative.length() > 0) {
            try {
                multiplicative.loadData(Integer.parseInt(inputSeed), Integer.parseInt(inputMultiplicative),
                        Integer.parseInt(inputModule));
                table.setItems(multiplicative.getAllRows());
            } catch (NumberFormatException e) {
                MessageBox.show("Formato de las entradas no valida", MESSAGE_FAILED);

            } catch(IllegalArgumentException e) {
                MessageBox.show(e.getMessage(), MESSAGE_FAILED);
            }
        } else {
            MessageBox.show("Introducir las variables de entrada.", MESSAGE_FAILED);
        }
    }

    private TableView<RowMultiplicativeAndBlum> getTable() {

        TableColumn<RowMultiplicativeAndBlum, Integer> numberRow = new TableColumn<>("Number");
        numberRow.setMinWidth(100);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<RowMultiplicativeAndBlum, String> columnOne = new TableColumn<>("Xo");
        columnOne.setMinWidth(100);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<RowMultiplicativeAndBlum, String> columnTwo = new TableColumn<>("a * Xn");
        columnTwo.setMinWidth(100);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<RowMultiplicativeAndBlum, String> columnThree = new TableColumn<>("X n+1");
        columnThree.setMinWidth(100);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<RowMultiplicativeAndBlum, String> columnFour = new TableColumn<>("Un = X n+1/m");
        columnFour.setMinWidth(150);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour));
        return table;
    }
}
