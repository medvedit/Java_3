package ru.medwedSa.Java_3.Lessen_3_Java_io.ClassWork;

import java.io.Serializable;
import java.util.Objects;

public class Cat extends Animal implements Serializable {
    /**
     *  При создании ObjectInputStream и записи в файл объекта Cat, если заранее не был создан serialVersionUID, то в
     *  процессе записи данных в файл он создается по умолчанию. При дальнейшем чтении из файла + сравнение объектов,
     *  с добавлением в класс Cat переопределенных методов toString(), equals(Object o) и hashCode() выпал Exception,
     *  в котором был stream classdesc serialVersionUID = 2685563806039936415 и
     *  local class serialVersionUID = 3564887442325524964
     *  ************
     *  При создании объекта класса Cat и его сериализации - конструктор класса вызывается, а
     *  при десериализации конструктор НЕ вызывается.
     */
    public static final long serialVersionUID = 2685563806039936415L;
    public transient final String priv = "priv";
    private String name;
    private String color;

    public Cat() {
        super("cat");
        System.out.println("Cat born");
    }

    public Cat(String name, String color) {
        this();
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat cat)) return false;
        return Objects.equals(name, cat.name) && Objects.equals(color, cat.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
