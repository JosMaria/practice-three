package org.genesiscode.practicethree.view;

public class RowMultiplicativeAndBlum {

    int numberRow;
    private final String columnOne;
    private final String columnTwo;
    private final String columnThree;
    private final String columnFour;

    public RowMultiplicativeAndBlum(int numberRow, String columnOne, String columnTwo,
                                    String columnThree, String columnFour) {
        this.numberRow = numberRow;
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
        this.columnThree = columnThree;
        this.columnFour = columnFour;
    }

    @Override
    public String toString() {
        return String.format("Row\t{\t%s,\t%s,\t%s,\t%s,\t%s\t}",
                numberRow, columnOne, columnTwo, columnThree, columnFour);
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
}
