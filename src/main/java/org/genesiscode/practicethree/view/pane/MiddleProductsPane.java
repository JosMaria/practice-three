package org.genesiscode.practicethree.view.pane;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.genesiscode.practicethree.model.MiddleProducts;
import org.genesiscode.practicethree.view.MessageBox;
import org.genesiscode.practicethree.view.Row;

import java.util.List;

public class MiddleProductsPane extends MyPane {

    private static MiddleProductsPane middleProductsPane;
    private VBox mainPane;

    private TextField limitField, seedOneField, seedTwoField;
    private Button startButton;
    private TableView<Row> table;
    private final MiddleProducts middleProducts;

    private static final String MESSAGE_FAILED = "Error al empezar el proceso";

    private MiddleProductsPane() {
        super("Productos Medios");
        middleProducts = new MiddleProducts();
        loadControls();
        buildPane();
    }

    public synchronized static MiddleProductsPane getInstance() {
        return middleProductsPane == null ? new MiddleProductsPane() : middleProductsPane;
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private void buildPane() {
        mainPane = new VBox(10, new VBox(lblHeader),
                new HBox(20, seedOneField, seedTwoField, limitField),
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

        seedOneField = new TextField();
        seedOneField.setPromptText("Semilla 1");
        seedOneField.setTooltip(new Tooltip("Semilla 1"));
        seedOneField.setPrefColumnCount(SPACES);

        seedTwoField = new TextField();
        seedTwoField.setPromptText("Semilla 2");
        seedTwoField.setTooltip(new Tooltip("Semilla 2"));
        seedTwoField.setPrefColumnCount(SPACES);

        startButton = new Button("Empezar");
        startButton.setOnAction(actionEvent -> startProcess());

        table = new TableView<>();
    }

    private void startProcess() {
        String inputSeedOne = seedOneField.getText().trim();
        String inputSeedTwo = seedTwoField.getText().trim();
        String inputLimit = limitField.getText().trim();

        if (inputSeedOne.isEmpty() || inputSeedTwo.isEmpty() || inputLimit.isEmpty()) {
            MessageBox.show("Introducir las variables de entrada.", MESSAGE_FAILED);
        } else {
            try {
                middleProducts.loadData(Long.parseLong(inputSeedOne), Long.parseLong(inputSeedTwo), Integer.parseInt(inputLimit));
                table.setItems(middleProducts.getAllRows());
            } catch (NumberFormatException e) {
                MessageBox.show("Formato de las entradas no validos", MESSAGE_FAILED);
            } catch (IllegalArgumentException e) {
                MessageBox.show(e.getMessage(), MESSAGE_FAILED);
            }
        }
    }

    private TableView<Row> getTable() {
        TableColumn<Row, Integer> numberRow = new TableColumn<>("Number");
        numberRow.setMinWidth(20);
        numberRow.setCellValueFactory(new PropertyValueFactory<>("numberRow"));

        TableColumn<Row, String> columnOne = new TableColumn<>("Xo");
        columnOne.setMinWidth(50);
        columnOne.setCellValueFactory(new PropertyValueFactory<>("ColumnOne"));

        TableColumn<Row, String> columnTwo = new TableColumn<>("X1");
        columnTwo.setMinWidth(150);
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("ColumnTwo"));

        TableColumn<Row, String> columnThree = new TableColumn<>("Xo * X1");
        columnThree.setMinWidth(150);
        columnThree.setCellValueFactory(new PropertyValueFactory<>("ColumnThree"));

        TableColumn<Row, String> columnFour = new TableColumn<>("X n+1");
        columnFour.setMinWidth(50);
        columnFour.setCellValueFactory(new PropertyValueFactory<>("ColumnFour"));

        TableColumn<Row, String> columnFive = new TableColumn<>("Ri");
        columnFive.setMinWidth(50);
        columnFive.setCellValueFactory(new PropertyValueFactory<>("ColumnFive"));

        table.getColumns().addAll(List.of(numberRow, columnOne, columnTwo, columnThree, columnFour, columnFive));
        return table;
    }
}
