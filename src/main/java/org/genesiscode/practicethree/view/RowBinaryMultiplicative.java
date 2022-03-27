package org.genesiscode.practicethree.view;

public class RowBinaryMultiplicative {

    int numberRow;
    private final Integer columnOne;

    public RowBinaryMultiplicative(int numberRow, Integer columnOne) {
        this.numberRow = numberRow;
        this.columnOne = columnOne;
    }

    public int getNumberRow() {
        return numberRow;
    }

    public Integer getColumnOne() {
        return columnOne;
    }

    @Override
    public String toString() {
        return String.format("RowBinaryMultiplicative {numberRow=%s, columnOne=%s}", numberRow, columnOne);
    }
}
