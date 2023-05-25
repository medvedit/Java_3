package ru.medwedSa.Java_3.Lessen_6_log_test.HomeWork;

public class CheckArrayForDigitOneOrFour {
    public boolean fourOne(int[] arr) {

        boolean four = false;
        boolean one = false;

        for (int j : arr) {
            if (j == 1) {
                one = true;
            }
            else if (j == 4) {
                four = true;
            }
        }
        return one && four;
    }
}
