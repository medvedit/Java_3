package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example;

/**
 * Создали класс Employee, в котором прописаны поля, конструктор, геттеры и сеттеры. На основании аннотаций присвоенных
 * классу и полям будет создаваться таблица. Далее в созданную таблицу, в столбцы, будут insert созданные Employee
 * в виде строчек, данных.
 */

@Table(name = "Employee_table") // Эта аннотация говорит о том, что для этого класса нужно создать
                            // таблицу с названием Employee_table. При "срабатывании" этой аннотации создаться таблица.
public class Employee {
    @Column // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> id.
    @Id // Сам номер id будет задаваться при создании сущности Employee, без использования autoincrement,
        //  т.к. по умолчанию аннотация @Id -> autoincrement() default false
    private int id;

    @Column // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> name.
    private String name;
    @Column // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> age.
    private int age;
    @Column // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> salary.
    private int salary;
    @Column // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> email.
    private String email;
    @Column // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> phone.
    private String phone;
    private String address; // address создаваться в таблице не будет, т.к. нет аннотации @Column у поля.

    public Employee(int id, String name, int age, int salary, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    //<editor-fold desc="Геттеры и сеттеры класса Employee">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //</editor-fold>
}
