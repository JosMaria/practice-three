package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;

public class BlumBlumShub {

    private int seed;
    private int p;
    private int q;
    private int limit;
    private String messageToNotification;
    private final ObservableList<RowMultiplicativeAndBlum> rows = FXCollections.observableArrayList();

    public void loadData(int seed, int p, int q, int limit) {
        if (areRestrictionsValid(seed, p, q, limit)) {
            this.seed = seed;
            this.p = p;
            this.q = q;
            this.limit = limit;
        } else {
            throw new IllegalArgumentException(messageToNotification);
        }
    }

    public ObservableList<RowMultiplicativeAndBlum> getAllRows() {
        rows.clear();
        long m =  (long) p * q;
        for (int i = 0; i < limit; i++) {
            long columnOne = (long) seed * seed;
            long ultColumn = columnOne % m;
            rows.add(new RowMultiplicativeAndBlum(i + 1, String.valueOf(seed), String.valueOf(columnOne),
                    String.valueOf(m), String.valueOf(ultColumn)));
            seed = (int) ultColumn;
        }
        return rows;
    }

    private boolean areRestrictionsValid(int seed, int p, int q, int limit) {
        if (seed <= 0 || p <= 0 || q <= 0 || limit <= 0) {
            messageToNotification = "Los valores ingresados deben ser mayores a 0.";
            return false;
        }
        return true;
    }
}
