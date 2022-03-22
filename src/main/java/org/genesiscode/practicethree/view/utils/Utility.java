package org.genesiscode.practicethree.view.utils;

import org.genesiscode.practicethree.view.Row;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;

public class Utility {

    public static String zerosToLeft(String number, int countDigits) {
        boolean isParCountDigits = number.length() % 2 == 0;
        String result;

        if (countDigits % 2 == 0) {
            result = isParCountDigits ? number : String.format("0%s", number);
        } else {
            result = isParCountDigits ? String.format("0%s", number) : number;
        }
        return result;
    }

    public static String compressToD(String number, int D) {
        int index = (number.length() - D) / 2;
        return number.substring(index, number.length() - index);
    }

    public static String addZeroAndPoint(String numberCompressed) {
        return String.format("0.%1s", numberCompressed);
    }

    public static boolean areRowsEquals(Row rowOne, Row rowTwo) {
        return rowOne.getColumnOne().equals(rowTwo.getColumnOne())
                && rowOne.getColumnTwo().equals(rowTwo.getColumnTwo())
                && rowOne.getColumnThree().equals(rowTwo.getColumnThree())
                && rowOne.getColumnFour().equals(rowTwo.getColumnFour())
                && rowOne.getColumnFive().equals(rowTwo.getColumnFive());
    }

    public static boolean areRowsMultiplicativeEquals(RowMultiplicativeAndBlum rowOne, RowMultiplicativeAndBlum rowTwo) {
        return rowOne.getColumnOne().equals(rowTwo.getColumnOne())
                && rowOne.getColumnTwo().equals(rowTwo.getColumnTwo())
                && rowOne.getColumnThree().equals(rowTwo.getColumnThree())
                && rowOne.getColumnFour().equals(rowTwo.getColumnFour());
    }
}
