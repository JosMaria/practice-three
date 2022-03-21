package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.Row;

import static org.genesiscode.practicethree.view.utils.Utility.*;

public class MiddleSquares implements NoCongruentialGenerator {

    private long seed;
    private int limit;
    private String messageToNotification;
    private ObservableList<Row> rows = FXCollections.observableArrayList();

    public void loadData(long seed, int limit) throws IllegalArgumentException {
        if (areRestrictionsValid(seed, limit)) {
            this.seed = seed;
            this.limit = limit;
        } else {
            throw new IllegalArgumentException(messageToNotification);
        }
    }

    private boolean areRestrictionsValid(long seed, int limit) {
        if (String.valueOf(seed).length() <= NUMBER_MIN_DIGITS_TO_SEED) {
            messageToNotification = "Cantidad de digitos de la semilla (D) tiene que ser mayor a 3; D > 3";
            return false;
        } else if (limit <= 0) {
            messageToNotification = "El limite tiene que ser mayor a 0";
            return false;
        }
        return true;
    }

    public ObservableList<Row> getAllRows() {
        rows.clear();
        String columnOne = String.valueOf(seed);
        for (int i = 0; i < limit; i++) {
            long columnTwo = Long.parseLong(columnOne) * Long.parseLong(columnOne);
            String columnThree = zerosToLeft(String.valueOf(columnTwo), columnOne.length());
            String columnFour = compressToD(columnThree, columnOne.length());
            String columnFive = addZeroAndPoint(columnFour);
            rows.add(new Row(i + 1, columnOne, String.valueOf(columnTwo), columnThree, columnFour, columnFive));
            columnOne = columnFour;
        }
        return rows;
    }
}
