package ru.medwedSa.Java_3.Lessen_4_Multi_Threading_Part_1.HomeWork;

public class HomeWork {
    private static final Object mon = new Object();
    private static char letter = 'A';
    public static void main(String[] args) {
        new Thread(HomeWork::printA).start();
        new Thread(HomeWork::printB).start();
        new Thread(HomeWork::printC).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();

        new Thread(() -> printUniversal('A')).start();
        new Thread(() -> printUniversal('B')).start();
        new Thread(() -> printUniversal('C')).start();
    }

    //<editor-fold desc="Вариант решения с отдельными методами на каждый символ">
    private static void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'A') {
                        mon.wait();
                    }
                    System.out.print(letter);
                    letter = 'B';
                    mon.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'B') {
                        mon.wait();
                    }
                    System.out.print(letter);
                    letter = 'C';
                    mon.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'C') {
                        mon.wait();
                    }
                    System.out.print(letter + "  ");
                    letter = 'A';
                    mon.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Вариант решения одним методом">
    public static void printUniversal(char symbol) {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (letter != symbol) {
                        mon.wait();
                    }
                    System.out.print(symbol);
                    switch (symbol) {
                        case 'A' -> {
                            letter = 'B';
                            mon.notifyAll();
                        }
                        case 'B' -> {
                            letter = 'C';
                            mon.notifyAll();
                        }
                        case 'C' -> {
                            System.out.print("  ");
                            letter = 'A';
                            mon.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>
}
