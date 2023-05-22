package ru.medwedSa.Java_3.Lessen_5_Multi_Threading_Part_2.HomeWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private final ArrayList<Stage> stages;
    private final int carsCount;
    private final CyclicBarrier barrier;
    private int finishCount;
    private RaceState state;

    public Race(int carsCount, Stage... stages) {
        this.carsCount = carsCount;
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.barrier = initBarrier();
        this.state = RaceState.READY;
    }

    public void makeRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Car[] cars = new Car[carsCount];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }

    private CyclicBarrier initBarrier() {
        return new CyclicBarrier(carsCount, () -> {
            switch (state) {
                case PROCESS -> System.out.println("\nВажное объявление >>> Гонка закончилась!!!");
                case READY -> {
                    System.out.println("\nВажное объявление >>> Гонка началась!!!");
                    state = RaceState.PROCESS;
                }
            }
        });
    }

    public synchronized void finish(Car car) {
        if (finishCount++ == 0) {
            System.out.printf("У еас есть победитель: %s\n", car.getName());
        } else {
            System.out.printf("%s приехал %s_м\n", car.getName(), finishCount);
        }
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public int getCarsCount() {
        return carsCount;
    }
    public static enum RaceState {
        READY, PROCESS, FINISH
    }
}
