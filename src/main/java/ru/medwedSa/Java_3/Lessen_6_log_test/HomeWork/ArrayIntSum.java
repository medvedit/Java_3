package ru.medwedSa.Java_3.Lessen_6_log_test.HomeWork;

public class ArrayIntSum {

    public int arraySum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }
}
