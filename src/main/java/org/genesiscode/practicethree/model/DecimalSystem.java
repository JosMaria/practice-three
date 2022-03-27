package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.RowDecimalMultiplicative;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;
import org.genesiscode.practicethree.view.utils.Utility;

import static org.genesiscode.practicethree.model.CongruentialGenerator.FIRST_ELEMENT;
import static org.genesiscode.practicethree.view.utils.Utility.*;

public class DecimalSystem {

    private int seed;
    private int multiplicativeConstant;
    private int module;
    private String messageToNotification;
    private final ObservableList<RowDecimalMultiplicative> rows = FXCollections.observableArrayList();

    public void loadData(int seed, int multiplicativeConstant, int module) {
        this.seed = seed;
        this.multiplicativeConstant = multiplicativeConstant;
        this.module = module;
        if (! areRestrictionsValid()) {
            throw new IllegalArgumentException((messageToNotification));
        }
    }

    public ObservableList<RowDecimalMultiplicative> getAllRows() {
        rows.clear();
        int index = 1;
        rows.add(getRow(index));
        RowDecimalMultiplicative firstRow = rows.get(FIRST_ELEMENT);
        RowDecimalMultiplicative actual;
        do {
            index++;
            actual = getRow(index);
            rows.add(actual);
        } while (index <= module && ! areDecimalMultiplicativeRowEquals(firstRow, actual));
        return rows;
    }


    private RowDecimalMultiplicative getRow(int index) {
        int columnTwo = multiplicativeConstant * seed;
        int columnThree = columnTwo % module;

        RowDecimalMultiplicative rowDecimalMultiplicative =
                new RowDecimalMultiplicative(index, seed, columnTwo, columnThree, String.format("%s/%s", columnThree, module));
        seed = columnThree;
        return rowDecimalMultiplicative;
    }

    private boolean areRestrictionsValid() {
        messageToNotification = "";
        if (seed <= 0 || multiplicativeConstant <= 0 || module <= 0) {
            messageToNotification = "- Los valores ingrsados deben ser mayores a 0\n";
        }
        if(seed % 2 == 0 || seed % 5 == 0 || ! isRelativelyPrime(seed, module)) {
            messageToNotification += "- Semilla no debe ser divisible entre 2 o 5\ny ser relativamente a M\n";
        }
        if (! isModuleCorrect(module)) {
            messageToNotification += "El module (m) debe ser 10^d";
        }
        return messageToNotification.isEmpty();
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
