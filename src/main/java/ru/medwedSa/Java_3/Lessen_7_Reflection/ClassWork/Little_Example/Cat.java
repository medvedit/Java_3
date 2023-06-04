package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example;

/**
 * Создали класс Cat, в котором прописаны поля, конструктор. На основании аннотаций присвоенных классу и полям будет
 * создаваться таблица. Далее в созданную таблицу, в столбцы, будут insert созданные Cat в виде строчек, данных.
 */

@Table(name = "little_cats") // Эта аннотация говорит о том, что для этого класса нужно создать
                             // таблицу с названием little_cats. При "срабатывании" этой аннотации создаться таблица.
public class Cat {
    @Column(name = "identifier") // Поле in будет столбцом и его имя будет identifier
    @Id(autoincrement = true) // Для поля будет использоваться авто инкремент.
    private int id;
    @Column
    private String name; // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> name.
    @Column
    private String color; // Будет столбец с именем по умолчанию, т.е. как объявлено тут -> color.


    public Cat(int id, String name, String color) { // Конструктор класса.
        this.id = id;
        this.name = name;
        this.color = color;
    }

    //<editor-fold desc="Геттеры и сеттеры класса Cat">
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
