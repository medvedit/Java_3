package ru.medwedSa.Java_3.Lessen_6_log_test.HomeWork;

import java.util.Arrays;
//import ru.medwedSa.Java_3.Lessen_6_log_test.HomeWork.ArrayCopyUpToFirstDigitFour

public class LoggingLog4j {

    public static void main(String[] args) {

        ArrayCopyUpToFirstDigitFour method = new ArrayCopyUpToFirstDigitFour();
        CheckArrayForDigitOneOrFour searchForNumbers = new CheckArrayForDigitOneOrFour();

        int[] arr = {1, 4, 8, 5, 2, 3, 4, 1, 7};
        int[] arr1 = {1, 5, 8, 4, 3, 2, 1, 7, 7};
        int[] arr2 = {2, 9, 3, 5, 2, 3, 5, 9, 7};


        System.out.println("*********************************");
        System.out.println(Arrays.toString(method.newArray(arr)));
        System.out.println(Arrays.toString(method.newArray(arr1)));
        System.out.println(Arrays.toString(method.newArray(arr2)));
        System.out.println("*********************************");


        for (int[] ints : Arrays.asList(arr, arr1, arr2)) {
            System.out.println(searchForNumbers.fourOne(ints));
        }
    }
}

