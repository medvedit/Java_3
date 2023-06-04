package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionExample {
    /**
     * Reflection - тема опасная и она выбрасывает кучу исключений. Тут в классе main все эти исключения просто
     * пробросил, но по-хорошему, в реальной жизни их нужно каждое обрабатывать в коде.
     * @param args
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
        /*
         * Ниже, разделением через ряд **** будут приведены 3 варианта получения объектов типа Class
         */
        // *****************************************
//        Class<Cat> catClass = Cat.class; // №1 Получили объект catClass типа Class от класса Cat.
        // *****************************************
        Cat cat = new Cat("Barsik", "Red", 3); // №2 Создали объект cat на основании конструктора
                                                                // от класса Cat.
        Class catClass1 = cat.getClass(); // №2 И уже из имеющегося объекта cat получаем объект пита Class catClass1.
                                           // В этом случае метод .getClass() возвращает объект типа Class.
        // *****************************************
//        Class catClass = Class.forName("ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork" +
//                ".ReflectionExample$Cat"); // №3 .forName принимает в себя имя класса который
        // переводит в объект типа Class. Так как в этом примере класс вложенный, всё в одном файле, то вместо
        // имени идет полная ссылка.
        // *****************************************
        /*
         * Пакет java.lang.reflect предоставляет классы и интерфейсы, которые используются для получения отражающей
         * информации о классах и объектах. Reflection API обеспечивает программный доступ к информации о полях,
         * методах и конструкторах загруженных классов. Это позволяет использовать отраженные поля,
         * методы и конструкторы для работы с их базовыми аналогами в рамках ограничений безопасности.
         * Эта ссылка проведет вас через простые и практичные методы, доступные в пакете java.lang.reflect.
         * _________________ Классы java.lang.reflect _________________
         * AccessibleObject - Класс AccessibleObject является базовым классом для объектов Field, Method и Constructor.
         * Array - Класс Array предоставляет статические методы для динамического создания массивов Java и доступа к ним.
         * Constructor - Constructor предоставляет сведения об одном конструкторе класса и доступ к нему.
         * Executable - Общий суперкласс для общих функций Method и Constructor.
         * Field - Field предоставляет сведения об одном поле класса или интерфейса и динамический доступ к нему.
         * Method - Method предоставляет сведения об одном методе и доступ к нему в классе или интерфейсе.
         * Modifier - Класс Modifier предоставляет static методы и константы для декодирования модификаторов доступа к
         *            классам и членам.
         * Parameter - Информация о параметрах метода.
         * Proxy - Proxy предоставляет статические методы для создания динамических прокси-классов и экземпляров,
         *         а также является суперклассом всех динамических прокси-классов, созданных этими методами.
         * ReflectPermission - Класс Permission для рефлексивных операций.
         */

        /* Итак, получили объект catClass1 типа класса Class вариантом №2 и сейчас можем с ним что-то поделать,
        *  а именно: */
        System.out.println(catClass1.getName()); // Получить полное имя, адрес нахождения класса.
        System.out.println(catClass1.getSimpleName()); // Получить только имя класса.
        int mods = catClass1.getModifiers(); // Запрос модификатора у класса. Модификатор это число int.
        int b = catClass1.getConstructor().getModifiers(); // Запрос модификатора у конструктора.
        System.out.println(Modifier.toString(b)); // Вывод поля конструктора.
        System.out.println(Modifier.isStatic(mods)); // Вывод, является ли класс статическим?
        System.out.println(Modifier.isPrivate(mods)); // Вывод, является ли класс private?
        System.out.println(Modifier.toString(mods)); // Вывод полей класса.
        /* Можем доставать поля класса: */
        Field nameField = catClass1.getField("name"); // Получил имя поля класса Cat, НО... метод .getField
                                                            // позволяет получить только public поля!
        System.out.println(nameField.getName()); // Вывод имени поля класса Cat.
        Field nameField1 = catClass1.getDeclaredField("privateField"); // Получил имя поля класса Cat у
                                                          // ПРИВАТНОГО ПОЛЯ !!! метод .getDeclaredField это позволяет.
        System.out.println(nameField1.getName()); // Вывод имени поля класса Cat.
        /* Так как Reflection используется для работы с классами, методами, полями которые нам не известны, то
           можно получить массив полей, методов и т.д. */
        Field[] fields = catClass1.getFields(); // Запрашиваем все ПУБЛИЧНЫЕ поля класса.
        for (Field field : fields) {
            System.out.println(field.getName()); // Выводим все имена ПУБЛИЧНЫХ полей в консоль.
        }
        Field[] fields1 = catClass1.getDeclaredFields(); // Запрашиваем ВСЕ поля класса, не зависимо от модификатора.
        for (Field field : fields1) {
            System.out.println(field); // Печатаем их в консоль.
        }
        /* Получаем приватное поле класса Cat, выводим его значение, а далее изменяем его. */
        Field privateField = catClass1.getDeclaredField("privateField"); // Получили доступ к ПРИВАТНОМУ полю.
//        privateField.get(null); // null у статического поля.
//        System.out.println(privateField.get(null));
        System.out.println(privateField.get(cat)); // Вывели его значение в консоль.
        privateField.setAccessible(true); // Явно разрешили изменения значения ПРИВАТНОГО поля.
        privateField.set(cat, 100500); // Изменили значение полученного ранее приватного поля.
        System.out.println(privateField.get(cat)); // Вывели измененное значение в консоль.

        Field someField = catClass1.getDeclaredField("some"); // Получили доступ к полю.
        System.out.println(someField.get(cat)); // Вывели значение поля.
        someField.setAccessible(true); // Разрешили изменения значения поля.
        someField.set(cat, "Который"); // Меняем значение поля some.
        System.out.println(cat); // Выводим значения всех полей класса Cat в консоль.
        // НО!!! Reflection НЕ МОЖЕТ ИЗМЕНЯТЬ ЗНАЧЕНИЯ ПРОИНИЦИАЛИЗИРОВАННЫХ ПОЛЕЙ!!! some не изменится!

        // А это (код ниже) будет работать, т.к. поле name не было назначено при инициализации.
        Field namefield = catClass1.getDeclaredField("name"); // Получили доступ к полю.
        namefield.setAccessible(true); // Разрешили изменения значения поля.
        namefield.set(cat, "Murzik"); // Меняем значение поля name.
        System.out.println(cat); // Barsik заменится на Murzik !!!

        System.out.println(privateField.getType()); // Вывод типа переменной privateField
        System.out.println(privateField.getType().getModule()); // Вывод модуля, в которой хранится тип
                                                                // переменной privateField
        Constructor[] constructor = catClass1.getDeclaredConstructors(); // создал массив всех полей конструктора.
        System.out.println(Arrays.toString(constructor)); // Вывел в консоль.

        /* Так же можно получить поля конкретного конструктора и работать, изменять их */
        Constructor<Cat> constructor1 = catClass1.getConstructor
          (String.class, String.class, int.class); // Указываем конкретные поля в искомом конструкторе.
        Cat reflected = constructor1.newInstance("Tishka", "black", 12); // получаем нового Cat
                                                          // constructor1.newInstance передовая в него нужные аргументы.
        System.out.println(reflected); // Распечатал в консоль нового Cat.

        /* Ещё один способ создания объекта Cat. */
        Cat reflected1 = (Cat) catClass1.newInstance(); // Сработает только в случае если в классе есть
                                                        // конструктор без параметров.
        System.out.println(reflected1); // Распечатал в консоль нового Cat.

        /* Можно достать все методы: */
        Method[] methods = catClass1.getDeclaredMethods(); // Получаем массив всех методов. Declared - и приватные.
        for (Method method : methods) {
            System.out.println(method); // Модификаторы метода + Весь путь до метода + Аргументы передаваемые в метод
            System.out.println(method.getName()); // Вывод всех методов в консоль. Только имя метода.
        }
        /* Можно достать конкретный метод и запустить его. */
        Method runMethod = catClass1.getDeclaredMethod("run", int.class); // Достали из класса
                                                                           // по имени и входящим в метод аргументам.
        runMethod.setAccessible(true); // Разрешили работу с методом.
        runMethod.invoke(cat, 200); // В Reflection метод можно запустить методом .invoke, в который
                                                // передается объект у которого вызывается метод и аргументы.
        /* Получение метода к которому подключена аннотация + получение данных из этого метода. */
        Method[] methods1 = catClass1.getDeclaredMethods(); // Получаем массив всех методов. Declared - и приватные.
        for (Method method : methods1) {
            if (method.isAnnotationPresent(MyAnno.class)) { // если у метода присутствует MyAnno.
                System.out.println(method); // Распечатать путь до метода + входящие в него параметры + его поля
                System.out.println(method.getAnnotation(MyAnno.class).someParam()); // Распечатать
                                                                             // значение someParam из аннотации MyAnno.
            }
        }
    }
    public static final class Cat {
        public final String some = "Некоторый";
        static final String type = "CAT";
        public final String name;
        public String color;
        private int privateField;
        final int age = 1;
        private Bowl b;

        public Cat() {
//            age = 1;
            name = "Безымянный";
        }


        public Cat(String name, String color, int age) {
            this.name = name;
            this.color = color;
//            this.age = age;
        }
        @MyAnno(someParam = 10)
        public void voce() {
            System.out.println(name + " new");
        }

        private void  run(int distance) {
            System.out.println(name + " пробежал " + distance + " метров.");
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "some='" + some + '\'' +
                    ", name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    ", privateField=" + privateField +
                    ", age=" + age +
                    ", b=" + b +
                    '}';
        }
    }

    public static class Bowl {
        int food;
    }
}


