package ru.medwedSa.Java_3.Lessen_3_Java_io.ClassWork;

import java.io.Serializable;

public class Animal implements Serializable {
    private String type;

    public Animal(String type) {
//        this.type = type;
        System.out.println("Animal born");
    }
}
