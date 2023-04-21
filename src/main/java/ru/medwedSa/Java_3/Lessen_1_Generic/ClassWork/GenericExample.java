package ru.medwedSa.Java_3.Lessen_1_Generic.ClassWork;

import java.util.ArrayList;
import java.util.List;

public class GenericExample {

    public static void main(String[] args) {
//        simpleBoxExample();
//        simpleGenericBoxExample();
//        rawUseExample();
//        boxMultiExample();
//        variantExample();
//        numbersWildcardExample();
//        comparingDouble();
//        genericMethodExample();

    }

    //<editor-fold desc=" Метод работы с обобщёнными методами. Ковариантность и Контрвариантность">
    private static void genericMethodExample() {
        List<Integer> integerList = List.of(1, 2, 3, 4);
        List<Integer> integerList1 = List.of(23, 38, 54, 3);
//        System.out.println(getFirsObj(integerList));
        int sum = (int) getFirsObj(integerList) + (int) getFirsObj(integerList1); // Приведение к типу данных. Нет дженерика.
        System.out.println(sum);

        int sum1 = getFirsObjGeneric(integerList) + getFirsObjGeneric(integerList1);// Не требует приведения типов. Есть дженерик.
        System.out.println(sum1);

        int sum2 = getFirsObjGeneric(integerList) + getFirsObjGeneric(integerList1); // Дженерик наследник Number.
        System.out.println(sum1);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<Integer> integersTest = new ArrayList<>(integerList);

        /**
         * При объявлении переменной integers используется extends или еще говорят "ограничение сверху". extends
         * (Ковариантность) говорит, что в списке могут быть Integer или его наследники.
         * Ковариантный список дает возможность читать данные, и они будут читаться в данном примере как int из
         * присвоенного integers, НО НЕ дает их ИЗМЕНЯТЬ!
         * Producer Extends (extends) - может отдавать данные, но не может их менять.
         */
        List<? extends Integer> extend = integersTest; // так объявленный extend отлично примет в себя integersTest
        for (Integer integer : extend) {
            System.out.println(integer); // И все отлично распечатает.
        }
//        extend.add(10); // Нет возможности добавить, изменить. Immutable object - Неизменяемый объект.
        /**
         * При объявлении переменной extend2 используется super или еще говорят "ограничение снизу". super
         * (Контрвариантность) говорит, что в списке могут быть Integer или его родительские классы.
         * Контрвариантный список даст возможность изменять его добавлением элементов, НО НЕ дает возможности прочитать
         * их как int, тк обращение, при чтении, в случае контрвариантности идет не к Integer, а к Object.
         * Consumer Super (super) - может изменяться, но из него нельзя получить данные, кроме, как в виде Object
         */
        List<? super Integer> extend2 = integersTest;
        for (Object o : extend2) { // Если используется super, то при чтении может использоваться только Object
            System.out.println(o);
        }
        extend2.add(10); // есть возможность добавить элемент, те изменили extend2, который вначале был равен integersTest
    }
    //</editor-fold>

    //<editor-fold desc="Пример обобщенного метода">

    /**
     *  Пример простого обобщенного метода. Метод с дженериками и без них. Тут, как и в случае с обобщенными типами
     *  данных, если, при объявлении метода не указан дженерик, то после создания переменной с указанием типа данных
     *  при вычислении (как в примере genericMethodExample) требуется явное приведение типов данных и проверки на
     *  входящие типы данных через сеттеры. В обобщенном методе с использованием дженериков все будет гораздо проще,
     *  нам будет помогать компилятор при написании кода.
     * @param list
     * @return
     * @param <T>
     */

    private static <T extends  Number> T getFirsNum(List<T> list) { // Обобщенный метод
        return list.get(1);
    }
    private static <T> T getFirsObjGeneric(List<T> list) { // Обобщенный метод
        return list.get(1);
    }
    private static Object getFirsObj(List list) { // Обобщенный метод
        return list.get(0);
    }
    //</editor-fold>

    //<editor-fold desc="Наглядная погрешность при вычислении double">
    private static void comparingDouble() {
        double a = 0.7;
        double b = .0;

        for (int i = 0; i < 70; i++) {
            b += 0.01;
        }

        System.out.println(a == b);
        System.out.println(a);
        System.out.println(b);
    }
    //</editor-fold>

    //<editor-fold desc="Метод описывающий возможности сравнения, вычисления с использованием дженериков">
    private static void numbersWildcardExample() {
        BoxWithNumbers<Number> numBox = new BoxWithNumbers<>(10, 11, 12); // Пример создания "коробки"
        BoxWithNumbers<Integer> intBox = new BoxWithNumbers<>(10, 11, 12); // Пример создания "коробки"
        BoxWithNumbers<Integer> intBox2 = new BoxWithNumbers<>(10, 11, 12); // Пример создания "коробки"
        BoxWithNumbers<Float> floatBox = new BoxWithNumbers<>(10f, 11f, 12f); // Пример создания "коробки"
//        BoxWithNumbers<String> strBox = new BoxWithNumbers<>("Hello"); // Компилятор не позволит создать строку, тк
        // extends Number

        System.out.println(intBox.equalsByAvg(intBox2)); // true
        System.out.println(intBox.equalsByAvg(numBox)); // при сравнении "коробок" с разными типами данных
        // в дженериках, и при установленной N вместо знака вопроса во входящих аргументах, в методе equalsByAvg -
        // компилятор на стадии написания кода выдаст ошибку.
        System.out.println(floatBox.equalsByAvg(numBox)); // true
    }
    //</editor-fold>

    //<editor-fold desc="Пример инвариантности и ковариантности">

    /**
     *  Массивы ковариантны и массив типа Integer можно положить в массив типа Number (Integer является Number),
     *  а вот дженерики так не работают, те коробку с типом Integer нельзя положить в коробку с питом Number.
     *  Дженерики в таком простом выражении - инвариантны. Дженерики по классике - инвариантны, однако для них тоже
     *  есть возможность сделать ковариантность и контравариантность.
     *...
     * Ковариантность (covariance)— перенос наследования исходных типов на производные от них типы в прямом порядке.
     * Контравариантность (contravariance)— перенос наследования исходных типов на производные от них типы в обратном порядке.
     * Инвариантность — ситуация, когда наследование исходных типов не переносится на производные.
     */
    private static void variantExample() {
        GenericBox<Integer> intBox = new GenericBox<>(10);
//        GenericBox<Number> numBox = intBox; // ОЩИБКА НА СТАДИИ НАПИСАНИЯ !!!

        Integer[] intArr = new Integer[10];
        Number[] numArr = intArr;
    }
    //</editor-fold>

    //<editor-fold desc="Метод с явным приведением к типу данных">
    /**
     Для арифметических действий с цифрами или конкатенации строк, нужно явно указать приведение типов данных, тк
     класс Box наследуется напрямую от Object без указания дженериков и компилятор точно не уверен, что лежит в
     переменной и как с ней работать.
     **/
    private static void simpleBoxExample() { // Пример явного приведения к типу данных при вычислениях, работе с данными и
                                             // выполнение проверки входящих типов данных
        Box boxInt = new Box(100500); // В boxInt число.
        Box boxInt2 = new Box(50); // В boxInt2 число.

        Box stringBox = new Box("Hello"); // В stringBox строку.
        Box stringBox2 = new Box(" word!"); // В stringBox2 строку.

        // Предположим, что тут много написанного кода...

        boxInt2.setObj("Добавлю_ка я, в boxInt2 вот эту строчку..."); /* Он не знал или забыл, что в boxInt2 лежат int.
        При этом JAVA, на стадии написания кода, не выдаст ошибку, а только на стадии компиляции выдаст GenericExample.
        По этому нужно делать проверку на приведение типов данных(ниже в блоке if()*/

        // Предположим, что тут много написанного кода...
        int sum = 0;
        if (boxInt.getObj() instanceof Integer && boxInt2.getObj() instanceof Integer) {
            sum = (Integer) boxInt.getObj() + (Integer) boxInt2.getObj(); // Приведение к int.
        } else {
            System.out.println("Нет возможности сложить заданные типы данных...");
        }

        String newString = (String) stringBox.getObj() + (String) stringBox2.getObj(); // приведение к String.

        System.out.println("Сумма = " + sum);
        System.out.println("Полученная строка -> " + newString);
    }
    //</editor-fold>

    //<editor-fold desc="Метод с использованием джинериков">

    /**
     * В этом методе используются ждинерики- <тип данных>, что позволяет избежать дополнительных проверок при
     * изменении данных через сеттеры, и при решении вычислений компилятору не нужно дополнительно указывать тип данных
     * тк это сделано уже при объявлении переменной и он уже точно знает, что в переменных exampleInt и exampleInt2
     * лежат int, а в переменных и exampleStr2 строки. И если какой-то "Вася" при работе в методе захочет положить в
     * переменною типа int строчное значение, то компилятор еще на стадии написания кода скажет что это не возможно, а
     * не во время запуск/компиляции кода. Компилятор сам отслеживает, что бы другие типы данных, кроме тех, что указаны
     * на стадии объявления, в нашу коробку не попадали.
     */
    public static void simpleGenericBoxExample() {
        GenericBox<Integer> boxInt = new GenericBox<>(50);
        GenericBox<Integer> boxInt2 = new GenericBox<>(100);

        GenericBox<String> stringBox = new GenericBox<>("Максимка ");
        GenericBox<String> stringBox2 = new GenericBox<>(" Боровитин");

//        exampleInt2.setObj("Число"); // Компилятор подчеркнет ошибку присвоения. Раскомментируй и увидишь.
//        exampleStr.setObj(777); // Компилятор подчеркнет ошибку присвоения. Раскомментируй и увидишь.

        int sum;
        String text;
        sum = boxInt.getObj() + boxInt2.getObj();
        text = stringBox.getObj() + stringBox2.getObj();
        System.out.println(sum);
        System.out.println(text);
    }
    //</editor-fold>

    //<editor-fold desc="Метод работы с объектами от класса с джинериками, но без использования таковы внутри метода.">

    /**
     * Метод создает объекты от класса GenericExample, в котором есть возможность использовать дженерики при создании
     * объектов. Но в методе rawUseExample, они, дженерики не использованы, что допускается, но не рекомендуется.
     * Компилятор услужливо подчеркивает объект и говорит -> Raw use of parameterized class 'GenericBox'
     */
    private static void rawUseExample() {
        GenericBox boxInt = new GenericBox(50);
        GenericBox boxInt2 = new GenericBox(100);

        GenericBox stringBox = new GenericBox("Максимка ");
        GenericBox stringBox2 = new GenericBox(" Боровитин");

//        exampleInt2.setObj("Число"); // Компилятор подчеркнет ошибку присвоения. Раскомментируй и увидишь.
//        exampleStr.setObj(777); // Компилятор подчеркнет ошибку присвоения. Раскомментируй и увидишь.

        int sum = 0;

        if (boxInt.getObj() instanceof Integer && boxInt2.getObj() instanceof Integer) {
            sum = (Integer) boxInt.getObj() + (Integer) boxInt2.getObj(); // Приведение к int.
        } else {
            System.out.println("Нет возможности сложить заданные типы данных...");
        }

        String text = (String) stringBox.getObj() + (String) stringBox2.getObj();

        System.out.println(sum);
        System.out.println(text);
    }
    //</editor-fold>

    //<editor-fold desc="Пример метода с большим количеством типов данных в дженерике ">
    /**
     * Пример описания большого количества данных в дженерике.
     */
    private static void boxMultiExample() {
        GenericBoxMyltiType<Integer, String, Double, Float, String, String> multi = new GenericBoxMyltiType<>(
                10,
                "Hello",
                20.4,
                2f,
                "word",
                "!"
        );

        System.out.printf("1 = %s, 2 = %s, 3 = %s, 4 = %s, 5 = %s, 6 = %s",
                multi.getXObj(),
                multi.getYObj(),
                multi.getZObj(),
                multi.getAObj(),
                multi.getBObj(),
                multi.getCObj()
        );
    }
    //</editor-fold>
}
