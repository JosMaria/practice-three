package org.genesiscode.practicethree.view.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.genesiscode.practicethree.view.utils.Utility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class UtilityTest {

    @ParameterizedTest(name = "#{index} - Test with number: {0}, D: {1}, output: {2}")
    @MethodSource("zerosToLeftData")
    @DisplayName("verify the static method zeros to left")
    void zerosToLeftVerify(String number, int countDigits, String output) {
        // WHEN
        String result = zerosToLeft(number, countDigits);

        // THEN
        assertEquals(output, result);
    }

    static Stream<Arguments> zerosToLeftData() {
        return Stream.of(
                arguments("354876", 5, "0354876"),
                arguments("30975", 4, "030975"),
                arguments("101761", 4, "101761"),
                arguments("1234321", 4, "01234321"),
                arguments("59763", 5, "59763"),
                arguments("87654321", 5, "087654321")
        );
    }

    @ParameterizedTest(name = "#{index} - Test with number: {0}, D: {1}, result: {2}")
    @MethodSource("dataByCompressToD")
    @DisplayName("verify the static method compress to D")
    void verifyCompressToD(String number, int D, String resultExpected) {
        //WHEN
        String resultActual = compressToD(number, D);

        //THEN
        assertEquals(resultExpected, resultActual);
    }

    static Stream<Arguments> dataByCompressToD() {
        return Stream.of(
                arguments("0354876", 5, "35487"),
                arguments("030975", 4, "3097"),
                arguments("101761", 4, "0176"),
                arguments("01234321", 4, "2343"),
                arguments("59763", 5, "59763"),
                arguments("087654321", 5, "76543")
        );
    }

    @ParameterizedTest(name = "#{index} - Test with number: {0}, result: {1}")
    @MethodSource("dataVerifyIsPrime")
    @DisplayName("verify the static method is prime")
    void verifyIsPrime(int number, boolean result) {
        // WHEN
        boolean numberPrime = isNumberPrime(number);

        // THEN
        assertEquals(result, numberPrime);
    }

    static Stream<Arguments> dataVerifyIsPrime() {
        return Stream.of(
                arguments(13, true),
                arguments(26, false),
                arguments(7, true),
                arguments(9, false)
        );
    }

    @ParameterizedTest(name = "#{index} - Test with number: {0}, list: {1}")
    @MethodSource("dataFoundMultiples")
    @DisplayName("verify the static method found multiples")
    void verifyFoundMultiples(int number, List<Integer> expected) {
        // WHEN
        List<Integer> actual = foundMultiples(number);

        // THEN
        assertEquals(expected, actual);
    }

    static Stream<Arguments> dataFoundMultiples() {
        return Stream.of(
                arguments(100, List.of(2, 5)),
                arguments(39, List.of(3, 13)),
                arguments(441, List.of(3, 7)),
                arguments(60, List.of(2, 3, 5)),
                arguments(40, List.of(2, 5))
        );
    }

    @ParameterizedTest(name = "#{index} - Test with number 1: {0}, number 2: {1}, result: {2}")
    @MethodSource("dataRelativelyPrime")
    @DisplayName("verify the static method is relatively prime")
    void verifyIsRelativelyPrime(int numberOne, int numberTwo, boolean result) {
        // WHEN
        boolean relativelyPrime = isRelativelyPrime(numberOne, numberTwo);

        // THEN
        assertEquals(result, relativelyPrime);
    }

    static Stream<Arguments> dataRelativelyPrime() {
        return Stream.of(
                arguments(25, 17, true),
                arguments(25, 15, false),
                arguments(15, 30, false),
                arguments(12, 13, true),
                arguments(39, 100, true),
                arguments(100, 441, true),
                arguments(60, 40, false)
        );
    }
}