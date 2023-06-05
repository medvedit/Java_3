package ru.medwedSa.Java_3.Lessen_7_Reflection.HomeWork;

public class Main {
    public static void main(String[] args) {

    /* Запуск обоих методов start*/
        ReflectionTester.start(Testing.class); // Запуск метода start() по переданному классу.

        ReflectionTester.start(Testing.class.getName()); // Запуск метода start() по переданному имени класса.
    }
}
