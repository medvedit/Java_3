package ru.medwedSa.Java_3.Lessen_4_Multi_Threading_Part_1.ClassWork;



public class MyltiThreadingExample_java2 {

    private static final Object mom1 = new Object();
    public static final Object mom2 = new Object();

    private static int wordNum = 0;

    public static void main(String[] args) {
//        threadCreationExample();
//        threadStopExample();
//        raceConditionExample();
//        stuntDeadLockExample();
//        mainFinishedFirstExample();
//        Desktop desktop = Desktop.getDesktop();
//        desktop.browse(URI.create("https://yandex.ru"));
//        threadQueue();

    }

    private static void threadQueue() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printHello();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printWorld();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printHello();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printWorld();
            }
        }).start();
    }
    private static void printWorld() {
        synchronized (mom1) {
            while (1 != wordNum) {
                try {
                    mom1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(" world!");
            wordNum = 0;
            mom1.notifyAll();
        }
    }
    private static void printHello() {
        synchronized (mom1) {
            while (0 != wordNum) {
                try {
                    mom1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.print("Hello");
            wordNum = 1;
            mom1.notifyAll();
        }
    }
    private static void stuntDeadLockExample() {
        new Thread(MyltiThreadingExample_java2::doA).start();
        new Thread(MyltiThreadingExample_java2::doB).start();
    }
    private static void doA() {
        System.out.println("doA Start");

        synchronized (mom1) {
            try {
                System.out.println("dot mon 1");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        doB();
    }
    private static void doB() {
        System.out.println("doB Start");

        synchronized (mom2) {
            try {
                System.out.println("dot mon 2");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        doA();
    }
    private static void raceConditionExample() {
        RaceConditionExample example = new RaceConditionExample();
        Thread t1 = new Thread(() -> increment(example));
        Thread t2 = new Thread(() -> increment(example));
        Thread t3 = new Thread(() -> increment(example));
//        t1.setPriority(5);
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        example.printVariables();
    }
    private static void increment(RaceConditionExample example) {
        for (int i = 0; i < 1000; i++) {
            example.increment();
        }
    }

    private static void threadStopExample() {
        /**
         * Пример метода в котором перед запуском созданного потока происходит назначение потока - потока "демоном".
         * Смена флага с true на false - говорит о функции "демона" у потока.
         * setDaemon(true) - поток "демон"
         * setDaemon(false) - обычный поток
         * По умолчанию создается обычный поток.
         * Если, в созданном методе/приложении, существуют/созданы потоки как "демоны", так и обычные потоки, то когда
         * в работающем методе/приложении из работающих потоков останутся только потоки "демоны", то приложение/метод
         * завершает свою работу, завершая все потоки "демоны".
         */
        Thread t = new Thread(() ->{
            while (!Thread.currentThread().isInterrupted()) { // пока текущий поток не прерван цикл будет бесконечен.
                try {
                    Thread.sleep(500);
                    System.out.println("Ping");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Исключение, прерывание работы. Поток не Демон");
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.setDaemon(true);
        t.start();
        System.out.println("Thread started");
        try {
            Thread.sleep(2000);
            t.interrupt();
//            t.suspend(); // С версии 1.2 не рекомендуется применять.
//            t.resume(); // С версии 1.2 не рекомендуется применять.
//            t.stop(); // С версии 1.2 не рекомендуется применять.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    } // Работа с потоками Демонами.

    private static void mainFinishedFirstExample() {
        /**
         * Пример того нюанса, что один поток, в данном примере main может, завершить работу, а другие потоки, если
         * эти потоки не "демоны" могут продолжить свою работу.
         */
        Thread main = Thread.currentThread();
        Thread t = new Thread(() ->{
                try {
                    Thread.sleep(2000);
                    System.out.println(main.getState());
                    System.out.println("Thread completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
            }
        });
        t.start();
        System.out.println("Thread finished");
    } // Пример работы с потоками НЕ Демонами.
    private static void threadCreationExample() {
        System.out.printf("Hello fom main! Thread name is: %s\n", Thread.currentThread().getName());
        MyThread myThread = new MyThread(); // Можно создать экземпляр класс MyThread
//        myThread.run; // запустит поток, но в том же методе, в котором он был создан
        myThread.start();// запустит поток, именно отдельный поток.
//        myThread.start(); // Нельзя дважды стартовать один поток, но...
//        new Thread(myThread).start(); // если очень нужно, можно запустить новый поток, с той же реализацией метода
//        run myThread.
        Thread myRunnable = new Thread(new MyRunnable());// Можно создать экземпляр Thread и передать в него
                                                              // нечто implements Runnable.
        myRunnable.start();

        Thread anon = new Thread(new Runnable() { // Создание анонимного потока,
            @Override
            public void run() { // с переопределенным методом run.
                System.out.printf("Hello from Anonymous! Thread name is: %s\n", Thread.currentThread().getName());
            }
        });
        anon.start();

        new Thread(() -> System.out.printf("Hello from lambda! Thread name is: %s\n",
                Thread.currentThread().getName())).start(); // Создание и запуск в виде лямбды.
        new Thread(MyltiThreadingExample_java2:: printSomething).start(); // Создание и запуск в виде референс.
    } // Варианты создания потоков.
    private static void printSomething() { // Метод описывающий работу созданного потока способом reference.
        System.out.printf("Hello from method reference! Thread name is: %s\n", Thread.currentThread().getName());
    }




    static class RaceConditionExample {
        private final Object mon = new Object();
        private int a;
        private int b;
        private int c;
        public RaceConditionExample() {
            a = 0;
            b = 0;
            c = 0;
        }
        // static -> mon = class
        // non static -> mon = this
        public synchronized void increment() {
//            synchronized (mon) {
                a++;
                b++;
                c++;
//            }
        }
        public synchronized void printVariables() {
            System.out.printf("a = %d, b = %d, c = %d\n", a, b, c);
        }
    }
    static class MyRunnable implements Runnable { // Отдельный класс описывающий работу потока MyRunnable
                                                  // наследника интерфейса Runnable.
        @Override
        public void run() { // с переопределенным методом run
            System.out.printf("Hello from MyRunnable! Thread name is: %s\n", Thread.currentThread().getName());
        }
    }
    static class MyThread extends Thread { // Отдельный класс описывающий работу потока MyThread
                                           // наследника от класса Thread.
        @Override
        public void run() { // с переопределенным методом run
            System.out.printf("Hello from MyThread! Thread name is: %s\n", Thread.currentThread().getName());

        }
    }
}
