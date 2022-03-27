package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.RowBinaryMultiplicative;

import static org.genesiscode.practicethree.view.utils.Utility.isModuleValid;
import static org.genesiscode.practicethree.view.utils.Utility.isRelativelyPrime;

public class BinarySystem {

    private int seed;
    private int multiplicativeConstant;
    private int module;
    private String messageNotification;
    private final ObservableList<RowBinaryMultiplicative> rows = FXCollections.observableArrayList();

    public void loadData(int multiplicativeConstant, int seed, int module) {
        this.multiplicativeConstant = multiplicativeConstant;
        this.seed = seed;
        this.module = module;
        if (! areRestrictionsValid()) {
            throw new IllegalArgumentException(messageNotification);
        }
    }

    public ObservableList<RowBinaryMultiplicative> getAllRows() {
        rows.clear();
        int seedTemporal = seed, i = 1;
        do {
            int columnOne = (multiplicativeConstant * seedTemporal) % module;
            rows.add(new RowBinaryMultiplicative(i, columnOne));
            i++;
            seedTemporal = columnOne;
        } while (seedTemporal != seed);
        rows.add(new RowBinaryMultiplicative(i, (multiplicativeConstant * seedTemporal) % module));
        return rows;
    }

    private boolean areRestrictionsValid() {
        messageNotification = "";
        if (seed % 2 == 0 || seed % 5 == 0 || ! isRelativelyPrime(seed, module)) {
            messageNotification = "Semilla no debe ser divisible entre 2 o 5\ny ser relativamente primo\n";
        }
        if (! isModuleValid(module)) {
            messageNotification += "m puede ser 2^d";
        }
        return messageNotification.isEmpty();
    }
}
