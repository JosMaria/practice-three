package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;

import static org.genesiscode.practicethree.model.CongruentialGenerator.FIRST_ELEMENT;
import static org.genesiscode.practicethree.view.utils.Utility.areRowsMultiplicativeEquals;

public class Multiplicative {

    private int seed;
    private int multiplicativeConstant;
    private int module;
    private String messageToNotification;
    private final ObservableList<RowMultiplicativeAndBlum> rows = FXCollections.observableArrayList();

    public void loadData(int seed, int multiplicativeConstant, int module) {
        if (areRestrictionsValid(seed, multiplicativeConstant, module)) {
            this.seed = seed;
            this.multiplicativeConstant = multiplicativeConstant;
            this.module = module;
        } else {
            throw new IllegalArgumentException((messageToNotification));
        }
    }

    public ObservableList<RowMultiplicativeAndBlum> getAllRows() {
        rows.clear();
        int index = 1;
        rows.add(getRow(index));
        RowMultiplicativeAndBlum firstRow = rows.get(FIRST_ELEMENT);
        RowMultiplicativeAndBlum actual;
        do {
            index++;
            actual = getRow(index);
            rows.add(actual);
        } while (index <= module && ! areRowsMultiplicativeEquals(firstRow, actual));
        return rows;
    }

    private RowMultiplicativeAndBlum getRow(int index) {
        int columnTwo = multiplicativeConstant * seed;
        int columnThree = columnTwo % module;
        RowMultiplicativeAndBlum rowMultiplicativeAndBlum = new RowMultiplicativeAndBlum(index, String.valueOf(seed),
                String.valueOf(columnTwo), String.valueOf(columnThree), String.format("%s/%s", columnThree, module));
        seed = columnThree;
        return rowMultiplicativeAndBlum;
    }

    private boolean areRestrictionsValid(int seed, int multiplicativeConstant, int module) {
        if (seed <= 0 || multiplicativeConstant <= 0 || module <= 0) {
            messageToNotification = "Los valores ingrsados deben ser mayores a 0.";
            return false;
        } else if(seed % 2 == 0 || seed % 5 == 0) {
            messageToNotification = "La semilla no tiene que ser divisible entre 2 o 5.";
            return false;
        }else if (! isModuleCorrect(module)) {
            messageToNotification = "El module (m) debe ser 10^d";
            return false;
        }
        return true;
    }

    private boolean isModuleCorrect(int module) {
        int exponent = 1;
        double value;
        boolean isModuleValid = false;
        do {
            value = Math.pow(10, exponent);
            if (module == value) {
                isModuleValid = true;
            } else {
                exponent++;
            }
        }
        while (! isModuleValid && value < module);
        return isModuleValid;
    }
}
