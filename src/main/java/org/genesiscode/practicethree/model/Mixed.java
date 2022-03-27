package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.Row;

import static org.genesiscode.practicethree.view.utils.Utility.*;

public class Mixed implements CongruentialGenerator {

    private int seed, multiplicativeConstant, additiveConstant, module;
    private String messageToNotification;
    private final ObservableList<Row> rows = FXCollections.observableArrayList();

    public void loadData(int seed, int multiplicativeConstant, int additiveConstant, int module) {
        this.seed = seed;
        this.multiplicativeConstant = multiplicativeConstant;
        this.additiveConstant = additiveConstant;
        this.module = module;
        if (! areRestrictionsValid(seed, multiplicativeConstant, additiveConstant, module)) {
            throw new IllegalArgumentException(messageToNotification);
        }
    }

    private boolean areRestrictionsValid(int seed, int multiplicativeConstant, int additiveConstant, int module) {
        messageToNotification = "";
        if (seed <= 0 || multiplicativeConstant <= 0 || additiveConstant <= 0 || module <= 0) {
            messageToNotification = "- No cumple la restricción a, c, m, xo > 0\n";
        }
        if (module <= multiplicativeConstant || module <= additiveConstant || module <= seed) {
            messageToNotification += "- No cumple la restricción m > c, Xo, a\n";
        }
        if (! isModuleValid(module)) {
            messageToNotification += "- No cumple la restricción m = 2^g\n";
        }
        if (! isRelativelyPrime(additiveConstant, module)) {
            messageToNotification += "- C no es relativamente primo M";
        }
        return messageToNotification.isEmpty();
    }

    public ObservableList<Row> getAllRows() {
        rows.clear();
        int index = 1;
        rows.add(getRow(index));
        Row firstRow = rows.get(FIRST_ELEMENT);
        Row actual;
        do {
            index++;
            actual = getRow(index);
            rows.add(actual);
        } while (index <= module && ! areRowsEquals(firstRow, actual));

        return rows;
    }

    private Row getRow(int index) {
        int columnTwo = multiplicativeConstant * seed;
        int columnThree = columnTwo + additiveConstant;
        int columnFour = columnThree % module;
        Row row = new Row(index, String.valueOf(seed), String.valueOf(columnTwo), String.valueOf(columnThree),
                String.valueOf(columnFour), String.format("%s/%s", columnFour, module));
        seed = columnFour;
        return row;
    }
}
