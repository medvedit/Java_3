package ru.medwedSa.Java_3.Lessen_7_Reflection.HomeWork;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionTester {

    public static void start(String className) { // Метод start() который принимает имя класса.
        try {
            Class<?> c = Class.forName(className); // Преобразует переданную стоку с именем класса в .class
            start(c); // И запускает метод start по полученному классу. Метод start ниже.
        } catch (ClassNotFoundException e) { // Исключение если метод не обнаружен.
            throw new RuntimeException("переданный в метод класс не обнаружен");
        }
    }

    public static void start(Class<?> classMethod) { // Запуск метода start() по переданному классу.
//        Constructor constructor = c.getDeclaredConstructor();
//        Object o = constructor.newInstance();
        /* По условию задания методы с аннотациями BeforeSuite и AfterSuite должны выполняться по одному разу.
        *  Для этого добавляем отдельные ссылки before и after со значением null, для того что бы проверить уникальность
        *  выполнения методов с аннотациями BeforeSuite и AfterSuite.
        */
        Method before = null;
        Method after = null;
        Method[] methods = classMethod.getDeclaredMethods(); // Достаем все методы в массив.
        List<Method> list = new ArrayList<>(); // Создаем список методов из которого они будут вызывать в необходимом
        // порядке. Порядок вызова методов согласно приоритетам (по условию задания).
        for (Method method : methods) { // Идем по массиву методов, и ...
            if (method.isAnnotationPresent(Test.class)) { // Если метод имеет аннотацию Test, то ...
                int priority = method.getAnnotation(Test.class).priority(); // Создали переменную для
                                                                                     // хранения приоритетов.
                if (priority < 1 || priority > 10) { // Если приоритет выпадает из диапазона приоритетов по заданию, то
                    throw new RuntimeException("Исключение приоритета"); // Исключение.
                }
                list.add(method); // иначе добавляем метод в созванный список list.
            } else if (method.isAnnotationPresent(BeforeSuite.class)) { // Если метод имеет аннотацию
                                                                                  // BeforeSuite, то ...
                if (before != null) { // Если при этом метод с аннотацией BeforeSuite уже есть в нашем списке list, то
                    throw new RuntimeException("Метод BeforeSuite передается на выполнение более одного раза!!!");
                                                                                            // Выбрасываем исключение.
                }
                before = method; // Иначе в переменную before закидываем ссылку на метод с аннотацией BeforeSuite.
            } else if (method.isAnnotationPresent(AfterSuite.class)) { // Если метод имеет аннотацию
                                                                                // AfterSuite, то ...
                if (after != null) {  // Если при этом метод с аннотацией AfterSuite уже есть в нашем списке list, то
                    throw new RuntimeException("Метод AfterSuite передается на выполнение более одного раза!!!");
                                                                                            // Выбрасываем исключение.
                }
                after = method; // Иначе в переменную before закидываем ссылку на метод с аннотацией AfterSuite.
            }
        }

        /* Почему в коде не использован TreeSet, который позволяет исключить повторное появление повторяющихся
         * методов. Я сейчас о методах с аннотациями BeforeSuite и AfterSuite. Но в этом случае методы с аннотациями
         * Test и с одинаковыми приоритетами автоматом исключаются при использовании TreeSet, при условии сортировки
         * внутри TreeSet по признакам приоритета, а это противоречит заданию.
         * Поэтому производим проверки на уникальность необходимых методов выше по коду, а ниже производим сортировку
         * по приоритетам.
         */
        list.sort((o1, o2) -> o2.getAnnotation(Test.class).priority()
                - o1.getAnnotation(Test.class).priority()); // Сортировка методов с аннотацией Test по
                                                                      // приоритетам, от большего приоритета к меньшему.

        if (before != null) { // Если before имеет ссылку на объект, то ...
            list.add( 0,before); // Добавляем ее в начало списка. Метод выполнится первым.
        }

        if (after != null) { // Если after имеет ссылку на объект, то ...
            list.add(after); // Добавляем ее в конец списка. Метод выполнится последним.
        }

        for (Method nameMethod : list) { // Проходим по сформированному списку, преобразовывая названия методов
                                         // из list в методы.

            try {
                nameMethod.invoke(null); // И запускаем по-очереди каждый из методов. Так как в данном примере методы
                // запускаются static, то на invoke передаем null. Если бы методы были не static, а метод start
                // принимал бы объект, а не класс, в invoke передавался бы объект вместо null. Либо можно так же
                // передавать в метод start метод, но тогда нужно было делать constructor и constructor.newInstance()
                // а это -> Reflection (строка 22 и 23) и также передавать в invoke объект, а не null.
                // Все это можно послушать на Java_3 Lessen_7 самое начало урока.
            } catch (IllegalAccessException e) { // Возможные исключения.
                throw new RuntimeException("запрещен доступ к классу");
            } catch (InvocationTargetException e) { // Возможные исключения.
                throw new RuntimeException("исключение выданное вызванным методом или конструктором");
            }
        }
    }
}
