package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.Row;

import static org.genesiscode.practicethree.view.utils.Utility.*;

public class ConstantMultiplier implements NoCongruentialGenerator {

    private long seed, constant;
    private int limit;
    private String messageToNotification;
    private final ObservableList<Row> rows = FXCollections.observableArrayList();

    public void loadData(long seed, long constant, int limit) {
        if (areRestrictionsValid(seed, constant, limit)) {
            this.seed = seed;
            this.constant = constant;
            this.limit = limit;
        } else {
            throw new IllegalArgumentException(messageToNotification);
        }
    }

    public ObservableList<Row> getAllRows() {
        rows.clear();
        String columnTwo  = String.valueOf(seed);
        for (int i = 0; i < limit; i++) {
            String columnThree = zerosToLeft(String.valueOf(constant * Long.parseLong(columnTwo)), columnTwo.length());
            String columnFour = compressToD(columnThree, columnTwo.length());
            String columnFive = addZeroAndPoint(columnFour);
            rows.add(new Row(i + 1, String.valueOf(constant), columnTwo,
                    columnThree, columnFour, columnFive));
            columnTwo = columnFour;
        }
        return rows;
    }

    public boolean areRestrictionsValid(long seed, long constant, int limit) {
        if (String.valueOf(seed).length() <= NUMBER_MIN_DIGITS_TO_SEED) {
            messageToNotification = "Cantidad de digitos de la semilla (Xo) tiene que ser mayor a 3; D > 3";
            return false;
        } else if (String.valueOf(constant).length() <= NUMBER_MIN_DIGITS_TO_SEED) {
            messageToNotification = "Cantidad de digitos de la constante (a) tiene que ser mayor a 3; D > 3";
            return false;
        } else if (limit <= 0) {
            messageToNotification = "El limite tiene que ser mayor a 0";
            return false;
        }
        return true;
    }
}
