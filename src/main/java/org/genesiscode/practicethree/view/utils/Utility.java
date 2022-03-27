package org.genesiscode.practicethree.view.utils;

import org.genesiscode.practicethree.view.Row;
import org.genesiscode.practicethree.view.RowDecimalMultiplicative;
import org.genesiscode.practicethree.view.RowMultiplicativeAndBlum;

import java.util.ArrayList;
import java.util.List;

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

    public static boolean areDecimalMultiplicativeRowEquals(RowDecimalMultiplicative rowOne, RowDecimalMultiplicative rowTwo) {
        return rowOne.getColumnOne() == rowTwo.getColumnOne()
                && rowOne.getColumnTwo() == rowTwo.getColumnTwo()
                && rowOne.getColumnThree() == rowTwo.getColumnThree()
                && rowOne.getColumnFour().equals(rowTwo.getColumnFour());
    }

    public static boolean isModuleValid(long module) {
        int exponent = 1;
        boolean isModuleValid = false;
        while (! isModuleValid && Math.pow(2, exponent) <= module) {
            if (Math.pow(2, exponent) == module) {
                isModuleValid = true;
            }
            exponent++;
        }
        return isModuleValid;
    }

    public static boolean isRelativelyPrime(int numberOne, int numberTwo) {
        boolean isRelativelyPrime;
        if (isNumberPrime(numberOne) || isNumberPrime(numberTwo)) {
            isRelativelyPrime = true;
        } else if (areConsecutiveNumbers(numberOne, numberTwo)) {
            isRelativelyPrime = true;
        } else {
            List<Integer> multiplesNumberOne = foundMultiples(numberOne);
            List<Integer> multiplesNumberTwo = foundMultiples(numberTwo);
            long count = multiplesNumberOne.stream()
                    .filter(multiplesNumberTwo::contains)
                    .count();

            isRelativelyPrime = count == 0;
        }
        return isRelativelyPrime;
    }

    static boolean areConsecutiveNumbers(int numberOne, int numberTwo) {
        return numberOne + 1 == numberTwo || numberOne - 1 == numberTwo;
    }

    static boolean isNumberPrime(int number) {
        boolean isPrime = true;
        int i = 2;
        while (i < number / 2 && isPrime) {
            if (number % i == 0) {
                isPrime = false;
            }
            i++;
        }
        return isPrime;
    }

    static List<Integer> foundMultiples(int number) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 2;
        int numberClone = number;
        while (i <= number) {
            if (number == i || number % i == 0) {
                if (! list.contains(i)) {
                    list.add(i);
                }
                number /= i;
            } else {
                i++;
            }
        }
        if (list.contains(numberClone)) {
            list.remove(numberClone);
        }
        return list;
    }
}
