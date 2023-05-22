package ru.medwedSa.Java_3.Lessen_5_Multi_Threading_Part_2.HomeWork;

import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {

        Race race = new Race(4,
                new Road(60),
                new Tunnel(80, 2),
                new Road(40),
                new Tunnel(30, 2),
                new Road(100),
                new Road(20));
        race.makeRace();
    }
}

