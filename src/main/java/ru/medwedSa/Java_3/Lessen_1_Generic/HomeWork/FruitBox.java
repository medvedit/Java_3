package ru.medwedSa.Java_3.Lessen_1_Generic.HomeWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FruitBox<F extends Fruit> { // Может содержать все что угодно, но только Fruit или его наследники.

    private List<F> fruits; // fruits будет храниться в списке List, типа <F>.

    public FruitBox() { // Можно пустой конструктор.
        this.fruits = new ArrayList<>();
    }

    public FruitBox(List<F> fruits) { // Конструктор получающий список фруктов.
//        this.fruits = fruits; // Не лучший вариант инициализации из-за непредсказуемых результатов. Может принять иммутабельный список.
        this.fruits = new ArrayList<>(fruits);
    }

    public FruitBox(F... fruits) { // Конструктор получает фрукты в виде Varargs.
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public void addFruit(F fruit){ // Метод добавления фрукта в коробку, по типу фрукта (задан при создании коробки).
        this.fruits.add(fruit);
    }

    public double getWeight() { // Метод вычисления общего веса фруктов в коробке.
        double weight = 0.0; // Переменная для суммы.
        for (F fruit : fruits) { // Идем по всему списку fruits.
            weight += fruit.getWeight(); // Считаем сумму.
        }
        return weight;
    }

    public boolean equalsByWeight(FruitBox<?> another) { // Сравниваем вес коробок.
        return Math.abs(getWeight() - another.getWeight()) < 0.0001;
    }

    public void transferAll(FruitBox<? super F> another) { // Метод пересыпания фруктов в другую коробку.
        if(this == another) return; // Что бы не пересыпать из коробки в саму себя.
        another.fruits.addAll(fruits); // Пересыпали.
        fruits.clear(); // Очистили коробку из которой пересыпали.
    }

    @Override
    public String toString() {
        return "FruitBox{" +
                "fruits=" + fruits +
                '}';
    }

    public List<F> getFruits() { // Геттер.
        return fruits;
    }
}
