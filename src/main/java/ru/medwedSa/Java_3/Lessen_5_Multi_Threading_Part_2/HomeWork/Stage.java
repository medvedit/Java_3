package ru.medwedSa.Java_3.Lessen_5_Multi_Threading_Part_2.HomeWork;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}
