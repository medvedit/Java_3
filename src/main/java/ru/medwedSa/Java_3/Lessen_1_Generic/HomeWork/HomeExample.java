package ru.medwedSa.Java_3.Lessen_1_Generic.HomeWork;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

import static ru.medwedSa.Java_3.Lessen_1_Generic.HomeWork.Array.arrToList;
import static ru.medwedSa.Java_3.Lessen_1_Generic.HomeWork.Array.replaceArray;

public class HomeExample {

    public static void main(String[] args) {
//        replaceElementsArray();
//        convertArrayToArraylist();


        FruitBox<Apple> appleFruitBox = new FruitBox<>(
                new Apple(0.1f),
                new Apple(0.3f),
                new Apple(0.2f));
        FruitBox<Orange> orangeFruitBox = new FruitBox<>(
                new Orange(0.1f),
                new Orange(0.3f),
                new Orange(0.2f));

        FruitBox<Apple> appleFruitBox1 = new FruitBox<>(new Apple(1.2f), new Apple(2.3f));

        System.out.println(appleFruitBox.equalsByWeight(orangeFruitBox));

        appleFruitBox.addFruit(new Apple(0.4f));
        System.out.println(appleFruitBox.getWeight());
        System.out.println(appleFruitBox1.getWeight());

        appleFruitBox.transferAll(appleFruitBox1);

        System.out.println(appleFruitBox.getWeight());
        System.out.println(appleFruitBox1.getWeight());

        System.out.println(orangeFruitBox.getWeight());

        orangeFruitBox.addFruit(new Orange(3.9f));

        System.out.println(orangeFruitBox.equalsByWeight(appleFruitBox1));

    }







    private static void convertArrayToArraylist() {
        Integer[] arr = {1, 2, 3, 4, 5};
        ArrayList<Integer> arrayList = arrToList(arr);
        arrayList.add(555);
        arrayList.remove(2);
        System.out.println(arrayList);
    }

    private static void replaceElementsArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(5);
        for (int i = 1; i < 10; i++) {
            arrayList.add(arrayList.get(i-1) + 5);
        }
        System.out.println(arrayList);
        System.out.println(replaceArray(arrayList, 2, 7));
    }
}
