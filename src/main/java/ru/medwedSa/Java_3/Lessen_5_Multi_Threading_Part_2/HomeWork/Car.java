package ru.medwedSa.Java_3.Lessen_5_Multi_Threading_Part_2.HomeWork;

public class Car implements Runnable {
    private static int carsCount;
    private final Race race;
    private final int speed;
    private final String name;


    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        carsCount++;
        this.name = "Участник #" + carsCount;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            race.getBarrier().await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            race.finish(this);
            race.getBarrier().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getName() {
        return  name;
    }

    public int getSpeed() {
        return speed;
    }
}
