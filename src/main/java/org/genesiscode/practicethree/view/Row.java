package org.genesiscode.practicethree.view;

public class Row {

    int numberRow;
    private final String columnOne;
    private final String columnTwo;
    private final String columnThree;
    private final String columnFour;
    private final String columnFive;

    public Row(int numberRow, String columnOne, String columnTwo,
               String columnThree, String columnFour, String columnFive) {
        this.numberRow = numberRow;
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
        this.columnThree = columnThree;
        this.columnFour = columnFour;
        this.columnFive = columnFive;
    }

    public int getNumberRow() {
        return numberRow;
    }

    public String getColumnOne() {
        return columnOne;
    }

    public String getColumnTwo() {
        return columnTwo;
    }

    public String getColumnThree() {
        return columnThree;
    }

    public String getColumnFour() {
        return columnFour;
    }

    public String getColumnFive() {
        return columnFive;
    }

    @Override
    public String toString() {
        return String.format("Row {%s, %s, %s, %s, %s, %s}",
                numberRow, columnOne, columnTwo, columnThree, columnFour, columnFive);
    }
}
