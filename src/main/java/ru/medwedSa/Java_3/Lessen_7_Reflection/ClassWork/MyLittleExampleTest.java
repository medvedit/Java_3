package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork;


import ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example.Cat;
import ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example.Employee;
import ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example.LittleExample;

/**
 *  Хочу сделать так, что бы у меня была некая сущность которую нужно разложить на базу данных.
 *  Для этого создан пакет Little_Example, в котором есть:
 *  Классы:
 *  Cat (кот) - поля класса, конструктор, геттеры и сеттеры.
 *  Employee (сотрудник) - поля класса, конструктор, геттеры и сеттеры.
 *  LittleExample (маленький пример) создания таблицы, базы данных jdbc:sqlite, а именно create table (создание таблицы)
 *  - создание полей таблицы и insert into (вставить в) - вставки данных в таблицу.
 *  Аннотации:
 *  Column
 *  Id
 *  Table
 *  Дальнейшие комментарии в классах и в аннотациях...
 */
public class MyLittleExampleTest {
    public static void main(String[] args) {
//        LittleExample.createTable(Cat.class); // Вызвали клас + метод с данными класса Cat для здания таблицы.
//        LittleExample.createTable(Employee.class); // Вызвали клас + метод с данными класса Employee для здания таблицы.

        Cat cat1 = new Cat(0, "Barsik", "black");
        Cat cat2 = new Cat(0, "Murzik", "white");
        Cat cat3 = new Cat(0, "Vaska", "grey");
        Cat cat4 = new Cat(0, "Tishka", "red");

        LittleExample.insertObject(cat1);
        LittleExample.insertObject(cat2);
        LittleExample.insertObject(cat3);
        LittleExample.insertObject(cat4);

        Employee employee1 = new Employee(1, "Сергей",23, 42_000, "sergey@jdhf.ru",
                "+7(922)661-33-00", "jhgjggjhggjGJHhg");
        Employee employee2 = new Employee(2, "Кирил",27, 33_000, "kiril@kkfi.ru",
                "+7(922)661-44-00", "dsjhfgdsjhgfajhdg");
        Employee employee3 = new Employee(3, "Виктор",22, 56_000, "viktor@sjjf.ru",
                "+7(922)661-66-00", "wehaskfjbksjf");
        Employee employee4 = new Employee(4, "Марина",24, 44_000, "marina@hdal.ru",
                "+7(922)661-88-00", "wjwiejowjewkqw");

        LittleExample.insertObject(employee1);
        LittleExample.insertObject(employee2);
        LittleExample.insertObject(employee3);
        LittleExample.insertObject(employee4);



    }
}
