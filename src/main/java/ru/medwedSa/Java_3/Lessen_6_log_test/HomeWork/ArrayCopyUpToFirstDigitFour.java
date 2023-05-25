package ru.medwedSa.Java_3.Lessen_6_log_test.HomeWork;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayCopyUpToFirstDigitFour {
    private static final Logger log = LogManager.getLogger(ArrayCopyUpToFirstDigitFour.class.getName());

    public int[] newArray(int[] arr) {

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4)
                return Arrays.copyOfRange(arr, i + 1, arr.length);
        }
        throw new RuntimeException("В этом массиве нет цифры 4"); // для тестирования.
//        log.throwing(Level.ERROR, new RuntimeException("В этом массиве нет цифры 4")); // для запуска main
//        return arr; // для запуска main
    }
}
