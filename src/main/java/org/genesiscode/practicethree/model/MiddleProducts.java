package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.Row;

import static org.genesiscode.practicethree.view.utils.Utility.*;

public class MiddleProducts implements NoCongruentialGenerator {

    private long seedOne;
    private long seedTwo;
    private int limit;
    private String messageToNotification;
    private ObservableList<Row> rows = FXCollections.observableArrayList();

    public void loadData(long seedOne, long seedTwo, int limit) throws IllegalArgumentException {
        if (areRestrictionsValid(seedOne, seedTwo, limit)) {
            this.seedOne = seedOne;
            this.seedTwo = seedTwo;
            this.limit = limit;
        } else {
            throw new IllegalArgumentException(messageToNotification);
        }
    }

    private boolean areRestrictionsValid(long seedOne, long seedTwo, int limit) {
        if (String.valueOf(seedOne).length() <= NUMBER_MIN_DIGITS_TO_SEED) {
            messageToNotification = "Cantidad de digitos de la semilla 1 (D) tiene que ser mayor a 3; D > 3";
            return false;
        } else if (String.valueOf(seedTwo).length() <= NUMBER_MIN_DIGITS_TO_SEED) {
            messageToNotification = "Cantidad de digitos de la semilla 2 (D) tiene que ser mayor a 3; D > 3";
            return false;
        } else if (limit <= 0) {
            messageToNotification = "El limite tiene que ser mayor a 0";
            return false;
        }
        return true;
    }

    public ObservableList<Row> getAllRows() {
        rows.clear();
        String columnOne = String.valueOf(seedOne);
        String columnTwo = String.valueOf(seedTwo);

        for (int i = 0; i < limit; i++) {
            String columnThree = zerosToLeft(String.valueOf(Long.parseLong(columnOne) * Long.parseLong(columnTwo)), columnTwo.length());
            String columnFour = compressToD(columnThree, columnTwo.length());
            String columnFive = addZeroAndPoint(columnFour);
            rows.add(new Row(i + 1, columnOne, columnTwo, columnThree, columnFour, columnFive));
            columnOne = columnTwo;
            columnTwo = columnFour;
        }
        return rows;
    }
}
