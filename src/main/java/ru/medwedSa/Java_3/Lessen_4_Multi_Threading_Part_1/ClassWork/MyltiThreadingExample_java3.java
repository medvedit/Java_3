package ru.medwedSa.Java_3.Lessen_4_Multi_Threading_Part_1.ClassWork;

import java.util.concurrent.*;

public class MyltiThreadingExample_java3 {
    public static void main(String[] args) {
//        futureExample();
//        ExecutorServiceExample_1();
//        ExecutorServiceExample_2();
//        ExecutorServiceExampleShutdown();
//        ExecutorServiceExampleShutdownNow();
//        ExecutorServiceExampleAwaitTermination();
//        ExecutorServiceExampleFixedThreadPool();
//        ExecutorServiceExampleCachedThreadPool();
//        ExecutorServiceExampleSingleThreadScheduledExecutor();
    }



    private static void ExecutorServiceExampleSingleThreadScheduledExecutor() {
        /**
         * newSingleThreadScheduledExecutor - новый однопоточный запланированный исполнитель.
         * scheduleWithFixedDelay - прописал: что делать, отложенный старт, период запуска, в секундах. Этот процесс
         * бесконечен, по этому awaitTermination через 20 секунд.
         */
        try (ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor()) {
            service.scheduleWithFixedDelay(() -> System.out.println("schedule -> расписание"),
                    5, 1, TimeUnit.SECONDS);
            try {
                service.awaitTermination(20, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static void ExecutorServiceExampleCachedThreadPool() {
        /**
         * newCachedThreadPool - метод позволяющий создать столько потоков, сколько необходимо для решения задачи.
         * Гипотетически при использовании этого метода есть шанс, что придет слишком много пользователей разом, или
         * вас могут подосить можно получить OutOfMemoryError.
         * newCachedThreadPool - позволяет переиспользовать потоки. Т. е. созданные потоки в методе умирают не сразу
         * и вновь поступающие задачи могут решаться в освободившихся потоках. Этот метод не держит очередь задач.
         * Он либо вставляет задачу в освободившийся поток, а если нет свободных потоков, то создает новый.
         * Свободный поток по истечении 60 секунд завершиться.
         */
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 10; i++) {
                int j = i;
                executorService.execute(() -> {
                    System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                    try {
                        Thread.sleep((long) (2000 + 2000 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
                });
            }
            System.out.println("Все задачи тредам розданы. Процесс выполнения запущен.");
//            executorService.shutdown();
        }
    }
    private static void ExecutorServiceExampleFixedThreadPool() {
        /**
         * newFixedThreadPool - позволяет прописать количество потоков для решения. В данном примере будет создано 4
         * потока - nThreads: 4. Что может ускорить вычисление, исполнение задачи, но потребовать больше ресурсов
         * от системы.
         */
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 10; i++) {
                int j = i;
                executorService.execute(() -> {
                    System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                    try {
                        Thread.sleep((long) (2000 + 2000 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
                });
            }
            System.out.println("Все задачи тредам розданы. Процесс выполнения запущен.");
//            executorService.shutdown();
        }
    }
    private static void ExecutorServiceExampleAwaitTermination() {
        /**
         * Еще один вариант завершения выполнения потоков это метод awaitTermination. Он принимает в себя цифру как
         * начала отсчета и в каких единцах производить отсчет(сек, мин, часы...)
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.execute(() -> {
                System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                try {
                    Thread.sleep((long) (2000 + 2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
            });
        }
        System.out.println("Все задачи тредам розданы. Процесс выполнения запущен.");
//        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
//            executorService.shutdown();
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void ExecutorServiceExampleShutdownNow() {
        /**
         * Еще один способ завершения работы ExecutorService это вызов метода shutdownNow(), который возвращает
         * List<Runnable>. Т. е. ExecutorService выполнить те задачи и процессы которые уже выполняются, а те задачи
         * которые в очереди выполняться не будут. Никакие новые задачи приниматься не будут.
         * В данном примере в возвращаемом List<Runnable> от метода shutdownNow() могут находиться те процессы,
         * которые были в очереди на исполнение, но не были выполнены.
         * Кроме этого метод shutdownNow() шлет всем потокам sleep interrupted.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.execute(() -> {
                System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                try {
                    Thread.sleep((long) (2000 + 2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
            });
        }
        System.out.println("Все задачи тредам розданы. Процесс выполнения запущен.");
        executorService.shutdownNow();
//        List<Runnable> runnableList = executorService.shutdownNow(); // пример возврата данных
//        System.out.println(runnableList.toString());
    }
    private static void ExecutorServiceExampleShutdown() {
        /**
         * Завершать работу ExecutorService НУЖНО! Для этого используется метод shutdown().
         * shutdown() - при вызове этого метода, ExecutorService перестанет попринимать новые задачи, но те, которые
         * поставлены на выполнение, или те, которые у него уже в очереди на выполнение ExecutorService обязательно
         * выполнить и только после этого завершит свою работу.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.execute(() -> {
                System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                try {
                    Thread.sleep((long) (2000 + 2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
            });
        }
        System.out.println("Все задачи тредам розданы. Процесс выполнения запущен.");
        executorService.shutdown();
    }
    private static void ExecutorServiceExample_2() {
        /**
         *  Executors с перегрузкой new ThreadFactory(). new ThreadFactory()- это интерфейс который на вход принимает
         *  Runnable r, а возвращает Thread с прикрученной к нему логикой.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
//                t.setDaemon(true);
                t.setName("Some thread");
                return t;
            }
        });
        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.execute(() -> {
                System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                try {
                    Thread.sleep((long) (2000 + 2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
            });
        }
    }
    private static void ExecutorServiceExample_1() {
        /**
         * ExecutorService -> <>https://java-online.ru/concurrent-executor.xhtml</>
         * Данное средство служит альтернативой классу Thread, предназначенному для управления потоками.
         * В основу сервиса исполнения положен интерфейс Executor, в котором определен один метод :
         *                                                                              void execute(Runnable thread);
         * При вызове метода execute исполняется поток thread.
         * То есть, метод execute запускает указанный поток на исполнение.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.execute(() -> {
                System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                try {
                    Thread.sleep((long) (2000 + 2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
            });
        }
    }
    private static void futureExample() {
        /**
         * Пример работы с фьючами.
         * Когда мы создаем и гоняем потоки с использованием интерфейса Runnable, то мы переопределяем метод run()
         * который void, а т.е. он ничего не возвращает и не бросает исключения. Но, если нам необходимо вернуть
         * значение потока, после его завершения, для дальнейшего использования, то используются фьючи.
         * Для этого используем можно использовать реализацию FutureTask, которая обобщенная и при ее объявлении можно
         * сразу указать тип данных, который вернется по завершении работы метода, потока. Вместо конкретного типа
         * данных при объявлении FutureTask, можно использовать <Object>.
         * При создании new FutureTask<>, в нее можно передать интерфейс Runnable или Callable<тип данных>.
         * Интерфейс Callable является обобщённым и содержит в себе метод call() который возвращает результат работы и
         * может выбрасывать любые исключения.
         */
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(4000);
//                throw new RuntimeException("AAAAAAAAAAAAAAAAAAA"); // исключение Caused by (закомментить нижнюю строку)
                return "Hello from future task";
            }
        });
        new Thread(futureTask).start(); // Создали поток, в который передали возвращаемый futureTask.
        /**
         * Для возврата результата:
         */
        String result = null; // Содали переменную, null изначально.
        try {
            result = futureTask.get(5, TimeUnit.SECONDS); // Присвоили к ней значение методом get(),
            // который ожидает получения результата! 5,TimeUnit.SECONDS - максимальное время ожидания ответа.
        } catch (InterruptedException | ExecutionException | TimeoutException e) { // Метод get() генерирует
                                                                                   // исключения:
            // InterruptedException - сработает, если мы попытаемся interrupt(), остановить поток, который ожидает
            // получение результата.
            // ExecutionException - в целевом потоке случилось исключение, возможна цепочка вызовов исключений (Caused by)
            // TimeoutException - если за установленное время ожидания ответ не был получен.
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
