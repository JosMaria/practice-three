package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.Row;

public class Quadratic {

    private int a, b, c;
    private long m;
    private long seed;
    private String messageToNotification;
    private final ObservableList<Row> rows = FXCollections.observableArrayList();

    public void loadData(int a, int b, int c, long m, long seed) {
        if (areRestrictionsValid(a, b, c, m)) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.m = m;
            this.seed = seed;
        } else {
            throw new IllegalArgumentException(messageToNotification);
        }
    }

    public ObservableList<Row> getAllRows() {
        rows.clear();
        for (int i = 0; i < m; i++) {
            rows.add(getRow(i));
        }
        return rows;
    }

    private Row getRow(int index) {
        long result = ((a * (seed * seed)) + (b * seed) + c) % m;
        String columnOne = String.format("(%s*%s² + %s*%s + %s) mod %s = %s",
                a, seed, b, seed, c, m, result);
        String columnTwo = String.format("r%s = %s/%s", index, result, m - 1);
        Row row = new Row(index, columnOne, columnTwo, null, null, null);
        seed = result;
        return row;
    }

    private boolean areRestrictionsValid(int a, int b, int c, long m) {
        boolean isValid = true;
        if (a <= 0 || a % 2 != 0) {
            messageToNotification = "(a) debe ser numero par y mayor que 0";
            isValid = false;
        } else if ((b - a) % 4 != 1) {
            messageToNotification = "(b) debe cumplir la condicional (b - a) mod 4 == 1";
            isValid = false;
        } else if (c <= 0 || c % 2 == 0) {
            messageToNotification = "(c) debe ser numero impar y mayor que 0";
            isValid = false;
        } else if (! isModuleValid(m)) {
            messageToNotification = "(m) debe cumplir la condicional (m = 2^g)";
            isValid = false;
        }
        return isValid;
    }

    private boolean isModuleValid(long module) {
        int exponent = 1;
        boolean isModuleValid = false;
        while (! isModuleValid || Math.pow(2, exponent) <= module) {
            if (Math.pow(2, exponent) == module) {
                isModuleValid = true;
            }
            exponent++;
        }
        return isModuleValid;
    }
}
