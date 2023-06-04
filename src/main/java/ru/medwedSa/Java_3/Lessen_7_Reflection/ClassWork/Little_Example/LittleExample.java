package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Универсальный код для создания таблицы (create table) и для добавления данных в таблицу (insert into)
 */

public class LittleExample {
    private static Connection connection;
    public static <T> void insertObject(T o) { // Метод делающий insert в ранее созданную таблицу из метода createTable.
        Class c = o.getClass();
        if (!c.isAnnotationPresent(Table.class)) { // Если у переданного в метод класса нет аннотации Table, то...
            throw new IllegalArgumentException(); // Выбрасываем исключение.
        }

        Field[] fields = c.getDeclaredFields(); // Создаем массив всех полей, и приватных.
        checkId(fields); // Метод проверки полей на присутствие только одной аннотации Id.
        try {
            connect(); // Подключаемся к базе данных по адресу из метода connect().


// insert into little_cats (name, color) values ('значение', 'значение'); <- строка запроса которую формируем в коде ниже.

            try {
                StringBuilder sb = new StringBuilder("insert into ");/* Создаем sb стригбилдер для формирования
                                                                  строки запроса. Для добавления данных в созданную
                                                                   ранее таблицу в методе createTable*/
                sb.append(o.getClass().getAnnotation(Table.class).name()); // Если у входящего в метод
                // класса присутствует аннотация Table, то добавляем имя этого класса в строку формующегося запроса.
                sb.append(" ("); // добавили эти символы.

                int questions = 0; // переменная "вопросы".
                List<Object> values = new LinkedList<>(); // переменная значения.

                for (Field field : fields) { // Проходим по всем полям класса.
                    field.setAccessible(true); // Открываем доступ к полю.
                    if (field.isAnnotationPresent(Id.class)) { // Если в данный момент найдено поле с
                                                                              // аннотацией Id, и при этом ...
                        if (field.getAnnotation(Id.class).autoincrement()) { // Если у найденного поля с
                            // аннотацией Id значение autoincrement == true, то ...
                            continue; // Пропускаем. Значение Id изменять не будем.
                        }
                    }
                    /* Тут распишу то, что собирается в строку fieldName: Если у поля с аннотацией Column
                     * имя пустое (.name().isBlank()), то возьмем имя из поля (field.getName()). В противном случае
                     * возьмем имя присвоенное в аннотации Column (field.getAnnotation(Column.class).name()) */
                    if (field.isAnnotationPresent(Column.class)) {
                        String fieldName = field.getAnnotation(Column.class).name().isBlank() ?
                                field.getName() : field.getAnnotation(Column.class).name();
                        sb.append(fieldName); // добавляем найденное имя в sb.
                        sb.append(", "); // Запятая + пробел.
                        questions++; // Считаем количество найденных полей для добавления знаков вопросов.
                        values.add(field.get(o)); // Добавляем значение найденного поля для нашего объекта.
                    }
                }
                /* Выше, мы прошлись по всем полям, занесли/добавили их в (StringBuilder) sb и в сформированной строке
                 * запроса, в ее конце у нас осталась запятая + пробел. Нам нужно правильно закрыть строку запроса.
                 * Для этого ... */
                sb.setLength(sb.length() - 2); // Считываем всю длину/количество символов в строке sb и говорим,
                // что новая длинна/количество символов будет на два элемента меньше.
                // Тем самым убираем с конца пробел + запятую.
                sb.append(") values ("); // Добавляем вместо убранных символов значение из этого кода.

                for (int i = 0; i < questions; i++) { // Проходим от 0 до количества накопленных вопросов выше по коду, и ...
                    sb.append(i + 1 == questions ? "?);" : "?, "); // Добавляем в sb знаки вопроса, пока равенство
                    // не сработает. Как только равенство выполняется - добавляем ?);
                }

                try (PreparedStatement stmt = connection.prepareStatement(sb.toString())){
                    int number = 1;
                    for (Object value : values) { // Прошли по всем значениям из (LinkedList) values, и ...
                        stmt.setObject(number++, value); // добавили их в параметры для вставки в таблицу.
                    }
//                    System.out.println(stmt);// Увидеть в консоли сформированные строки запросов и параметры для вставки.
                    stmt.executeUpdate(); // Отправили сформированные параметры на insert
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } finally {
            disconnect(); // При любом исходе выполниться отключение от базы данных.
        }
    }

    public static <T> void createTable(Class<T> c) { // Метод генерации, создания таблицы.
        Map<Class, String> typeMap = getTypeMap(); // Создаем мапу из метода getTypeMap().

        if (!c.isAnnotationPresent(Table.class)) { // Если у класса нет аннотации Table, то...
            throw new IllegalArgumentException(); // Выбрасываем исключение.
        }
        /* Далее нужно проверить, что в полях найденного ранее класса присутствует только одна аннотация Id */
        Field[] fields = c.getDeclaredFields(); // Создаем массив всех полей, и приватных.
        checkId(fields); // Метод проверки полей на присутствие только одной аннотации Id.
        try {
            connect(); // Подключаемся к базе данных по адресу из метода connect().
            /* Ниже представлена строка которую будем формировать в (StringBuilder) -> sb */
            // create table little_cats (id integer primary key autoincrement, name text, color text);

            try (Statement stmt = connection.createStatement()){ // Создаем stmt по connection созданному в
                                                                 // методе коннект.
                StringBuilder sb = new StringBuilder("create table "); // Создаем sb стригбилдер для формирования
                                                             // запроса строки для создания таблицы с необходимыми
                                                           // полями, и разу заносим в него начало строки create table.
                sb.append(c.getAnnotation(Table.class).name()); // Добавили в формирующеюся строку имя
                                                                         // таблицы их аннотации прикрепленной к методу.
                sb.append(" ("); // Далее добавили пробел + скобка открывается.
                /* Сейчас будем формировать поля для столбцов таблицы на основании аннотации Column */
                for (Field field : fields) { // Проходим по массиву всех полей и ищем поля с аннотацией Column.
                    if (field.isAnnotationPresent(Column.class)) { // Если на поле есть аннотация Column, то ...
                        /* Тут распишу то, что собирается в строку fieldName: Если у поля с аннотацией Column
                        * имя пустое (.name().isBlank()), то возьмем имя из поля (field.getName()). В противном случае
                        * возьмем имя присвоенное в аннотации Column (field.getAnnotation(Column.class).name()) */
                        String fieldName = field.getAnnotation(Column.class).name().isBlank() ?
                                field.getName() : field.getAnnotation(Column.class).name();
                        sb.append(fieldName); // добавляем найденное имя в sb.
                        sb.append(" "); // Добавляем пробел.
                        sb.append(typeMap.get(field.getType())); // Добавляем тип поля из сформированной ранее мапы в
                                                             // методе getTypeMap() на основании найденного имени пля.
                        /* Кроме этого в процессе прохода по всем полям делаем проверку на авто инкремент у поля id.*/
                        if (field.isAnnotationPresent(Id.class)) { // Если поле имеет аннотацию Id, то ...
                            sb.append(" primary key"); // Добавляем в sb
                            if (field.getAnnotation(Id.class).autoincrement()) { // Если в аннотации Id
                                                             // прикрепленной к полю было autoincrement = true, то ...
                                sb.append(" autoincrement"); // Добавляем в sb.
                            }
                        }
                        sb.append(", "); // Добавили запятую + пробел.
                    }
                }
                /* Выше, мы прошлись по всем полям, занесли/добавили их в (StringBuilder) sb и в сформированной строке
                * запроса, в ее конце у нас осталась запятая + пробел. Нам нужно правильно закрыть строку запроса.
                * Для этого ... */
                sb.setLength(sb.length() - 2); // Считываем всю длину/количество символов в строке sb и говорим,
                // что новая длинна/количество символов будет на два элемента меньше.
                // Тем самым убираем с конца пробел + запятую.
                sb.append(");"); // Добавляем вместо убранных символов закрытая скобка + точка с запятой.

                stmt.execute(sb.toString()); // Передаем сформированную выше строку запроса для выполнения в stmt.

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally { // Обязательное для выполнения условие.
            disconnect(); // Отключение от базы данных из метода disconnect().
        }
    }

    private static void checkId(Field[] fields) {
        boolean hasId = false; // Ставим флаг изначально в положение false.
        for (Field field : fields) { // Проходим по всему массиву полей.
            if (field.isAnnotationPresent(Id.class)) { // Если присутствует аннотация Id, то ...
                                                                      // (ниже по коду -> hasId = true;)
                if (hasId) { // Если, при этом, hasId уже было true, то ...
                    throw new IllegalArgumentException("Более одного идентификатора..."); // исключение.
                }
                hasId = true; // Если у поля нашли аннотацию Id.
            }
        }
        if (!hasId) { // Если поле с аннотацией Id не найдено, то ...
            throw new IllegalArgumentException("Нет ни одного идентификатора..."); // Исключение.
        }
    }

    private static Map<Class, String> getTypeMap() { // Метод создания мапы где ключом будет класс поля из создаваемой
                                                     // или подключаемой в дальнейшем сущности.
        Map<Class, String> typeMap = new HashMap<>(); //
        typeMap.put(int.class, "integer");
        typeMap.put(String.class, "text");
        return typeMap;
    }

    private static void connect() { // Метод соединения с базой данных.
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:db/reflect.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect() { // Метод отключения от базы данных.
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
