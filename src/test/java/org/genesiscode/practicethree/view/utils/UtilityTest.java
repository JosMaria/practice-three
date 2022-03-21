package org.genesiscode.practicethree.view.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.genesiscode.practicethree.view.utils.Utility.compressToD;
import static org.genesiscode.practicethree.view.utils.Utility.zerosToLeft;
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
    @DisplayName("verify the method static compress to D")
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
}