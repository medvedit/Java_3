package ru.medwedSa.Java_3.Lessen_5_Multi_Threading_Part_2.HomeWork;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore smp; // создал семафор для туннеля
    public Tunnel(int length, int width) { // в конструктор передал длину тоннеля и количество полос для движения.
        this.length = length; // параметр длины тоннеля.
        this.description = "Тоннель " + length + " метров";
        this.smp = new Semaphore(width); // Передаваемая ширина тоннеля. Количество полос для движения.
        // В моем случае количество машин проезжающих через тоннель на один раз.
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire(); // заняли семафор
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release(); // отпустили семафор
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
