package ru.medwedSa.Java_3.Lessen_6_log_test.ClassWork;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Если необходимо отключить какой-либо тест от его исполнения, то используется аннотация @Disabled
 */
class CalculatorTestExampleTest {
    private CalculatorTestExample calculator; // Создали объект калькулятор.

    @BeforeAll // Аннотация его активирует
    static void beforeAll() { /* Можно прописать метод, который выполнится однажды перед всеми методами и прописать в
                                 нем какую то необходимую, общую логику */
        System.out.println("before all");
    }

    @AfterAll // Аннотация его активирует
    static void afterAll() { /* Метод подобный beforeAll, но выполнится в конце всех тестируемых методов */
        System.out.println("after all");
    }

    @BeforeEach // Выполнится перед каждым методом/тестом (before each -> перед каждым)
    void init() {
        System.out.println("before each"); // Для наглядности, что перед каждым...
        calculator = new CalculatorTestExample(); /* Что бы в каждом тесте не прописывать этк строку. Выполняем ее
                                                     перед каждым тестом. */
    }

    @AfterEach // Выполнится всегда после каждого теста. (after each -> после каждого)
    void close() {
        System.out.println("after each");
    }

    @Test // Аннотация @Test над методом - говорить, что это тест и будет в тестах запускаться.
    void addTest() {
//        calculator = new CalculatorTestExample(); // создаем новый объект калькулятора.
        int a = 6;
        int b = 4;
        int result = 10;
        /*
         * Assertions -> утверждение. И в нем (через . есть разные assert)
         * assertEquals -> сравнили результат и значение сложения
         * calculator.add -> в калькулятор добавляем то метод, который тестим.
         */
        Assertions.assertEquals(result, calculator.add(a, b));
    }

    @Test
    void subTest() {
//        calculator = new CalculatorTestExample();
        int a = 8;
        int b = 4;
        int result = 4;
//        Assertions.assertEquals(result, calculator.sub(a, b));
        assertEquals(result, calculator.sub(a, b)); /* Можно импортировать в статическом виде,
                                                                             без Assertions. вначале. */
    }

    @Test
    void mulTest() {
        assertEquals(10, calculator.mul(5, 2)); /* Сразу сравнил цифру 10 при вызове метода mul
                                с переданными в него данными для получения результата и сравнения его в дальнейшем. */
    }

    @Test
    void divTest() {
        assertEquals(2, calculator.div(8, 4));
    }

    @Test
    void shouldThrowArithmeticExceptionWhenDividingByZero() { /*assertThrows -> должен бросать. Должен бросать
             исключение ArithmeticException при делении на ноль. Проверка на истинность этого исключения.
             Если исключение ArithmeticException заменить в коде на NumberFormatException, то тест не будет пройден.*/
        assertThrows(ArithmeticException.class, () -> calculator.div(3, 0));
    }

    @Test
    void massAddTest1() {
        /*
         * Предположим, есть кейс при котором есть много проверяемых assert и при таком коде каждый запуск его будет
         * указывать на первую ошибку теста, а последующие проверки не будет исполняться. Нужно одним запуском проверить
         * все assert. Как быть??? Для этого смотрим код massAddTest2.
         */
        int a = 6;
        int b = 4;
        int result = 10;
        assertEquals(result, calculator.add(a, b));
        assertEquals(12, calculator.add(4, 8)); /* если искусственно сделать ошибку, запуск теста
                                                    остановится на первой ошибке и стальные проверки делать не будет.*/
        assertEquals(30, calculator.add(10, 20));
        assertEquals(13, calculator.add(6, 7));
        assertEquals(3, calculator.add(2, 1));
    }

    @Test
    @Disabled
    void massAddTest2() {
        /*
         * В этом случае, при использовании assertAll, все assert будут проверены одним запуском кода, независимо
         * сколько и где ошибке в assert.
         * В консоли увидим какие и где были ошибки.
         */
        int a = 6;
        int b = 4;
        int result = 10;
        assertAll( // Множественные проверки.
                () -> assertEquals(result, calculator.add(a, b)),
                () -> assertEquals(12, calculator.add(6, 7)),
                () -> assertEquals(22, calculator.add(20, 2)),
                () -> assertEquals(23, calculator.add(1, 23)),
                () -> assertEquals(50, calculator.add(40, 11))
        );
    }

    @CsvSource({ /* Аннотация в которую передали параметры для проверки. Данные в формате csv. Данные передаются в
                             том же порядке, как их принимает метод massAddTest3 в аргументах. */
            "30, 15, 15",
            "3, 1, 2",
            "22, 10, 12"
    })
    @ParameterizedTest /* Аннотация ParameterizedTest говорит, что тест параметризован, принимает параметры.*/
    void massAddTest3(int result, int a, int b) { /* Пример параметризованного теста. Этот тест проверит все входящие в
        него данные и выведет результат по всем данным, не зависимо от количества ошибок. Похож на assertAll*/
        assertEquals(result, calculator.add(a, b));
    }

    @ParameterizedTest
    @CsvFileSource(files = {"test_file/test1.csv", "test_file/test2.csv"}) /* В этом примере в параметр аннотации
                                         @CsvFileSource передаются пути к файлам с данными которые нужно проверить.*/
    void massAddTest4(int result, int a, int b) {
        assertEquals(result, calculator.add(a, b));
    }

    @ParameterizedTest
    @MethodSource("addMethodGenerator") // Метод, как источник данных для проверок/тестирования.
    void massAddTest5(int a, int b, int result) {
        assertEquals(result, calculator.add(a, b));
    }

    public static Stream<Arguments> addMethodGenerator() { // Метод требующий возвращение потока стрима аргументов.
        int count = 1000;
        List<Arguments> args = new ArrayList<>();
        for (int i = 0; i < count; i++) {
             int a = (int) (Math.random() * count);
             int b = (int) (Math.random() * count);
             int result = a + b;
             args.add(Arguments.of(a, b, result));
        }
        return args.stream();
    }
}