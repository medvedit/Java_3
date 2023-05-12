package ru.medwedSa.Java_3.Lessen_4_Multi_Threading_Part_1.ExampleThreading;

/**
 * Класс для наглядной работы потоков "демонов".
 */
public class DaemonExample {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); // availableProcessors - количество процессоров
                                                          // в системе, на которой в данный момент производится работа.
//        Runtime.getRuntime().exec(); exec - запуск других процессов.
        Thread.getAllStackTraces().forEach((k, v) -> System.out.println(k + ": " + v)); // Покажет все потоки
                                                                                        // "демоны" самой JAVA машины.

    }
}
