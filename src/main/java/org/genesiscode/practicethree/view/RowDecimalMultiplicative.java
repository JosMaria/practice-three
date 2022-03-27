package org.genesiscode.practicethree.view;

public class RowDecimalMultiplicative {

    int numberRow;
    private final int columnOne;
    private final int columnTwo;
    private final int columnThree;
    private final String columnFour;

    public RowDecimalMultiplicative(int numberRow, int columnOne, int columnTwo, int columnThree, String columnFour) {
        this.numberRow = numberRow;
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
        this.columnThree = columnThree;
        this.columnFour = columnFour;
    }

    public int getNumberRow() {
        return numberRow;
    }

    public int getColumnOne() {
        return columnOne;
    }

    public int getColumnTwo() {
        return columnTwo;
    }

    public int getColumnThree() {
        return columnThree;
    }

    public String getColumnFour() {
        return columnFour;
    }

    @Override
    public String toString() {
        return String.format("RowDecimalMultiplicative {numberRow=%s, columnOne=%s, columnTwo=%s, columnThree=%s, columnFour=%s}",
                                                        numberRow, columnOne, columnTwo, columnThree, columnFour);
    }
}
