package ru.medwedSa.Java_3.Lessen_6_log_test.HomeWork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LectureSixHomeworkTest {

    public static Stream<Arguments> sliceArrayEquals() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] {1, 4, 8, 5, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        out.add(Arguments.arguments(new int[] {1, 5, 8, 4, 3, 2, 1, 7, 7}, new int[]{3, 2, 1, 7, 7}));
        out.add(Arguments.arguments(new int[] {1, 5, 8, 4, 3, 2, 1, 7, 4}, new int[]{}));
        return out.stream();

    }

    @ParameterizedTest
    @MethodSource("sliceArrayEquals")
    void newArraySliceFromDigitFourTest(int[] in, int[] out) {
        ArrayCopyUpToFirstDigitFour result = new ArrayCopyUpToFirstDigitFour();
        Assertions.assertArrayEquals(out, result.newArray(in));
    }

    @Test
    void testSliceArrayRuntimeException() {
        ArrayCopyUpToFirstDigitFour result = new ArrayCopyUpToFirstDigitFour();
        Assertions.assertThrows(RuntimeException.class,
                () -> result.newArray(new int[]{2, 3, 7, 7}));
    }

    @Test
    void arrayMustHaveTheDigitOneAndFour() {
        CheckArrayForDigitOneOrFour resultBoolean = new CheckArrayForDigitOneOrFour();
        Assertions.assertTrue(resultBoolean.fourOne(new int[]{1, 3, 4, 1, 1}));
    }

    public static Stream<Arguments> dataForAddOperation() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.of(new int[] {3, 3, 3}, 9));
        out.add(Arguments.of(new int[] {3, 4, 3}, 10));
        out.add(Arguments.of(new int[] {3, 3, 33}, 39));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForAddOperation")
    void arrayIntSumTest(int[] array, int result) {
        ArrayIntSum calculator = new ArrayIntSum();
        assertEquals(result, calculator.arraySum(array));

    }
}