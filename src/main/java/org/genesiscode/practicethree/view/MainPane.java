package org.genesiscode.practicethree.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPane extends Application implements EventHandler<ActionEvent> {

    // NO CONGRUENTIAL
    private static final String MIDDLE_SQUARES = "_Cuadrados Medios";
    private static final String MIDDLE_PRODUCTS = "_Productos Medios";
    private static final String CONSTANT_MULTIPLIER = "_Multiplicador Constante";

    // CONGRUENTIAL
    private static final String MIXED = "_Mixto";
    private static final String MULTIPLICATIVE = "_Multiplicativo";
    private static final String ADDITIVE = "_Aditivo";
    private static final String QUADRATIC = "_Cuadratico";
    private static final String BLUM_BLUM_SHUB = "_Blum Blum Shub";

    private final VBox mainPane;
    private final MenuItem middleSquaresItem, middleProductsItem, constantMultiplierItem,
                            mixedItem, multiplicativeItem, additiveItem, quadraticItem, blumBlumShubItem;

    public MainPane() {
        middleSquaresItem = new MenuItem(MIDDLE_SQUARES);
        middleSquaresItem.setOnAction(this);
        middleProductsItem = new MenuItem(MIDDLE_PRODUCTS);
        middleProductsItem.setOnAction(this);
        constantMultiplierItem = new MenuItem(CONSTANT_MULTIPLIER);
        constantMultiplierItem.setOnAction(this);

        mixedItem = new MenuItem(MIXED);
        mixedItem.setOnAction(this);
        multiplicativeItem = new MenuItem(MULTIPLICATIVE);
        multiplicativeItem.setOnAction(this);
        additiveItem = new MenuItem(ADDITIVE);
        additiveItem.setOnAction(this);
        quadraticItem = new MenuItem(QUADRATIC);
        quadraticItem.setOnAction(this);
        blumBlumShubItem = new MenuItem(BLUM_BLUM_SHUB);
        blumBlumShubItem.setOnAction(this);

        mainPane = new VBox(10, getMenuBar(), new VBox());
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Practica 3");
        stage.setScene(new Scene(mainPane, 800, 500));
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        MenuItem source = (MenuItem) actionEvent.getSource();
        String text = switch (source.getText()) {
            case MIDDLE_SQUARES -> MIDDLE_SQUARES;
            case MIDDLE_PRODUCTS -> MIDDLE_PRODUCTS;
            case CONSTANT_MULTIPLIER -> CONSTANT_MULTIPLIER;
            case MIXED -> MIXED;
            case MULTIPLICATIVE -> MULTIPLICATIVE;
            case ADDITIVE -> ADDITIVE;
            case QUADRATIC -> QUADRATIC;
            case BLUM_BLUM_SHUB -> BLUM_BLUM_SHUB;
            default -> "Input not founded";
        };

        System.out.println(text);
    }

    private MenuBar getMenuBar() {
        Menu noCongruentialMenu = new Menu("_No Congruencial");
        noCongruentialMenu.getItems().addAll(middleSquaresItem, middleProductsItem, constantMultiplierItem);

        Menu congruentialMenu = new Menu("_Congruencial");
        congruentialMenu.getItems().addAll(mixedItem, multiplicativeItem, additiveItem, quadraticItem, blumBlumShubItem);
        return new MenuBar(noCongruentialMenu, congruentialMenu);
    }
//    public void setBottomPane(Pane bottomPane) {
//        mainPane.getChildren().remove(1);
//        mainPane.getChildren().add(bottomPane);
//    }
}
