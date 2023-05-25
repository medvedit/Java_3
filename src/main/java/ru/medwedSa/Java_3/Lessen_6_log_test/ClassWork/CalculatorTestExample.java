package ru.medwedSa.Java_3.Lessen_6_log_test.ClassWork;

public class CalculatorTestExample {
    /**
     * Описание создания тестов Java_3 лекция_6 1 час 30 мин.
     * Предположим, есть класс калькулятор, работу которого нужно протестить.
     * Для этого в классе жмем ⌘N и генерируем test... (лекция 6 1:36:22) В этом примере показана работа с jUnit5.
     * После этого в папке test генерируются пакеты для тестов и создается класс одноименный исходному + Test на конце
     * имени класса.
     * Дальнейшая работа и комменты в папке test/..../CalculatorTestExampleTest
     */
    public int add(int a, int b) { return a + b; }
    public int sub(int a, int b) { return a - b; }
    public int mul(int a, int b) { return a * b; }
    public int div(int a, int b) { return a / b; }
}
