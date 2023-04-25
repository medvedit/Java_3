package ru.medwedSa.Java_3.Lessen_1_Generic.HomeWork;


import java.util.ArrayList;
import java.util.Arrays;

public class Array {

    public static ArrayList<Integer> replaceArray(ArrayList<Integer> arr, int i, int i1) {
        int temp = arr.get(i);
        arr.set(i, arr.get(i1));
        arr.set(i1, temp);
        return arr;
    }

    public static <T> ArrayList<T> arrToList(T[] array) {
        ArrayList<T> result = new ArrayList<>(array.length);
//        result.addAll(Arrays.asList(array));
        for (int i = 0; i < array.length; i++) {
            result.add(array[i]);
        }
        return result;
    }
}



