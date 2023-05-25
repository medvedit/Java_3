package ru.medwedSa.Java_3.Lessen_6_log_test.HomeWork;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIntSumTest {

    public static Stream<Arguments> dataForAddOperation() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.of(new int[] {3, 3, 3}, 9));
        out.add(Arguments.of(new int[] {3, 4, 3}, 10));
        out.add(Arguments.of(new int[] {3, 3, 33}, 39));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForAddOperation")
    void arraySum(int[] array, int result) {
        ArrayIntSum calculator = new ArrayIntSum();
        assertEquals(result, calculator.arraySum(array));
    }
}