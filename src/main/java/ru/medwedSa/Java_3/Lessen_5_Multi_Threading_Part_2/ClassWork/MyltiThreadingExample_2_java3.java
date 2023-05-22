package ru.medwedSa.Java_3.Lessen_5_Multi_Threading_Part_2.ClassWork;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyltiThreadingExample_2_java3 {
    /**
     * Будут описаны варианты синхронизации потоков из стандартных библиотек JAVA.
     * Из пакета java.base.java.util.concurrent .
     * *******************************************
     * Для информации: Тут же, в пакете .atomic хранятся потокобезопасные обертки для примитивов.
     */
    public static void main(String[] args) {
//        reentrantLockExample();
//        reentrantLockExample_If_Else();
//        reentrantReadWriteLockExample();
//        countDownLatchExample();
//        semaphoreExample();
//        cyclicBarrierExample();
//        copyOnWriteArrayListExample();
//        CopyOnWriteArraySetExample();
//        hashtableExample();
//        collectionsExample();

    }

    private static void collectionsExample() {
        /*
         * Потокобезопасные коллекции.
         */
        List<String> list = new ArrayList<>(); // Для List есть следующие потокобезопасные исполнения:
        /*
         * Vector<>() - брат близнец ArrayList<>(). Очень схожие внутренние составляющие за исключением, что все
         * внутренние методы класса Vector<>() - ЗАСИНХРОНИЗИРОВАННЫ.
         * Класс Vector является реализацией динамического массива. Похож на ArrayList за исключением того, что:
         * Класс Vector содержит много устаревших методов, которые не являются частью Collection Framework.
         * В Java класс Vector оказывается очень полезным, если вы заранее не знаете размер массива или вам нужен
         * только тот, который может изменять размеры за время жизни программы.
         * <>https://proglang.su/java/vector-class</>
         */
        List<String> threadSafeList = new Vector<>(); // Потокобезопасный List.
        /*
         * CopyOnWriteArrayList - это потокобезопасный вариант of ArrayList. Подобно ArrayList, CopyOnWriteArray,
         * он управляет массивом для хранения его элементов. Разница в том, что все мутативные операции,
         * такие как add, set, remove, clear,... создают новую копию массива, которым он управляет.
         * Читать его могут несколько потоков одновременно. CopyOnWriteArrayList создает клонированную внутреннюю копию
         * базового ArrayList для каждой операции add () или set () . Из-за этих дополнительных накладных расходов
         * в идеале мы должны использовать CopyOnWriteArrayList только тогда, когда у нас очень частые операции чтения,
         * а не много вставок или обновлений.
         */
        List<String> threadSafeList1 = new CopyOnWriteArrayList<>(); // Потокобезопасный List.
        /*
         * Почитать тут -> <>https://java-online.ru/concurrent-collections.xhtml#java.util.concurrent</>
         */
        List<String> threadSafeList2 = Collections.synchronizedList(list); // Обертка List.


        Set<String> set = new HashSet<>(); // Для Set есть следующие потокобезопасные исполнения:
        /*
         * Почитать тут -> <>https://java-online.ru/concurrent-collections.xhtml#copyonwritearrayset</>
         * Лучше всего CopyOnWriteArraySet использовать для read-only (только для чтения) коллекций небольших размеров.
         * Если в данных коллекции произойдут изменения, накладные расходы, связанные с копированием,
         * не должны быть ресурсоёмкими.
         * Необходимо помнить, что итераторы класса CopyOnWriteArraySet не поддерживают операцию remove().
         * Попытка удалить элемент во время итерирации приведет к вызову исключения UnsupportedOperationException.
         * В своей работе итераторы используют «моментальный снимок» массива, который был сделан на момент
         * создания итератора.
         * Таким образом, если набор данных небольшой и не подвержен изменениям, то лучше использовать CopyOnWriteArraySet.
         */
        Set<String> threadSafeSet = new CopyOnWriteArraySet<>(); // Потокобезопасный Set.
        /*
         * Collections.SynchronizedSet() блокирует весь набор, блокируя параллельный доступ для нескольких потоков.
         * Другими словами, одновременно к набору может обращаться только один поток, что снижает производительность.
         * Collections.SynchronizedSet() следует избегать, если не важна согласованность данных.
         * Почитать тут -> <>https://www.techiedelight.com/ru/difference-between-concurrentskiplistset-synchronizedset-java/#:~:text=SynchronizedSet()%20</>
         */
        Set<String> threadSafeSet1 = Collections.synchronizedSet(set); // Обертка Set.
        NavigableSet<String> navigableSet = new TreeSet<>(); // Для HashMap есть следующие потокобезопасные исполнения:
        /*
         * ConcurrentSkipListSet Класс позволяет безопасно выполнять операции вставки, удаления и доступа к
         * набору одновременно несколькими потоками. Его следует предпочесть другим Set реализации интерфейса,
         * когда требуется одновременная модификация набора несколькими потоками.
         * Читаем тут -> <>https://spec-zone.ru/RU/Java/Docs/7/api/java/util/concurrent/ConcurrentSkipListSet.html</>
         */
        NavigableSet<String> threadSafeNavigableSet = new ConcurrentSkipListSet<>(); // Потокобезопасный Set
        /*
         * Читать тут -> <>https://spec-zone.ru/openjdk~8/java/util/collections#synchronizedNavigableSet-java.util.NavigableSet-</>
         */
        NavigableSet<String> threadSafeNavigableSet1 = Collections.synchronizedNavigableSet(navigableSet); // Обертка NavigableSet

        /*
         * HashMap — это несинхронизированная неупорядоченная карта пар ключ-значение (key-value).
         * Она допускает пустые значения и использует хэш-код в качестве проверки на равенство
         */
        Map<String, String> map = new HashMap<>(); // Для HashMap есть следующие потокобезопасные исполнения:
        /*
         * Hashtable представляет собой синхронизированную упорядоченную карту пар ключ-значение.
         * Все метода внутри Hashtable - synchronized
         * Она не допускает пустых значений по ключу и использует метод equals() для проверки на равенство.
         * <>https://javarush.com/groups/posts/3965-kofe-breyk-173-v-chem-raznica-mezhdu-hashmap-i-hashtable-kak-nayti-i-ispravitjh-iskljuchenie-nu#:~:text=Hashtable%20</>
         * Удручающе медленная работа.
         */
        Map<String, String> threadSafeMap = new Hashtable<>();
        /*
         * Почитать тут -> <>https://poltora.info/ru/blog/kak-ispolzovat-concurrenthashmap-v-java/</>
         */
        Map<String, String> threadSafeMap1 = new ConcurrentHashMap<>();
        NavigableMap<String, String> navigableMap = new TreeMap<>();
        /*
         * Почитать ткт -> <>https://spec-zone.ru/RU/Java/Docs/8/api/java/util/concurrent/ConcurrentSkipListMap.html</>
         */
        NavigableMap<String, String> threadSafenavigableMap = new ConcurrentSkipListMap<>();
    }
    private static void hashtableExample() {
        Map<String, Integer> numbers = new Hashtable<String, Integer>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        System.out.println(numbers.keySet());
        System.out.println(numbers.entrySet());
        Integer n = numbers.get("two");
        if (n != null) {
            System.out.println("two = " + n);
        }
    }
    public static void CopyOnWriteArraySetExample() {
        /**
         * Рассмотрим пример ArraySetExample с использованием класса CopyOnWriteArraySet.
         * В примере формируется набор данных list, на основании которого создается потокобезопасный набор cowSet
         * типа CopyOnWriteArraySet. В качестве данных используется внутренний класс User. Данные коллекции cowSet
         * с помощью итератора выводятся в консоль два раза. В первом цикле в коллекцию вносятся изменения: изменяется имя
         * одного объекта и добавляется другой. Во втором цикле данные выводятся без изменений.
         * ****************************************************************************************************
         * Выполнение примера при запуске в консоли:
         * В первом цикле в консоль выведены исходные данные несмотря на внесение изменений в набор.
         * Во втором цикле — измененный набор данных.
         * Пример подтверждает, что итератор набора данных CopyOnWriteArraySet не вызвал исключения
         * ConcurrentModificationException при одновременном переборе и изменении значений.
         */
        List<User> list  ;
        CopyOnWriteArraySet<User> cowSet;
        list = new ArrayList<>();
        list.add(new User("Прохор "));
        list.add(new User("Георгий"));
        list.add(new User("Михаил"));

        cowSet = new CopyOnWriteArraySet<User>(list);

        System.out.println("Цикл с изменением");

        Iterator<User> itr = cowSet.iterator();
        int cnt = 0;
        while (itr.hasNext()) {
            User user = itr.next();
            System.out.println("  " + user.name);
            if (++cnt == 2) {
                cowSet.add(new User("Павел"));
                user.name += " Иванович";
            }
        }

        System.out.println("\nЦикл без изменения");
        itr = cowSet.iterator();
        while (itr.hasNext()) {
            User user = itr.next();
            System.out.println("  " + user.name);
        }
    }
    public static class User { // Конструктор для работы в методе CopyOnWriteArraySetExample()
        private String name;
        public User(String name) {
            this.name = name;
        }
    }
    private static void copyOnWriteArrayListExample() {
        /**
         * Рассмотрим простой пример CopyOnWriteArrayListExample, в котором используем класс CopyOnWriteArrayList.
         * В примере формируется набор данных lst, на основании которого создается потокобезопасная коллекция list
         * типа CopyOnWriteArrayList. Данные коллекции list с помощью итератора выводятся в консоль два раза.
         * В первом цикле в коллекцию вносятся изменения, во втором цикле данные выводятся без изменений.
         * Результаты работы примера ниже после листинга примера.
         * ***************************************************************************
         * Выполнение примера:
         * В первом цикле, несмотря на внесение изменений в набор, в консоли представлены исходные данные.
         * Во втором цикле — измененный набор данных. Пример демонстрирует, что итератор набора данных
         * CopyOnWriteArrayList не вызвал исключения ConcurrentModificationException при одновременном внесении
         * изменений и переборе значений — это значит, что алгоритм CopyOnWrite действует.
         * <>https://java-online.ru/concurrent-collections.xhtml#copyonwritearraylist</>
         */
        List<String> lst = new ArrayList<>(); // Создали список lst с типом данных ArrayList и заполнили его.
        lst.add("Java");
        lst.add("J2EE");
        lst.add("J2SE");
        lst.add("Collection");
        lst.add("Concurrent");

        List<String> list = new CopyOnWriteArrayList<>(lst); // Создали список list с типом данных CopyOnWriteArrayList
                                                                // и передали в конструктор lst
        System.out.println("ЦИКЛ с изменением");
        printCollection(true, list);
        System.out.println("\nЦИКЛ без изменения");
        printCollection(false, list);
    }
    private static void printCollection(boolean change, List<String> list) { /* метод перебора + вывода в консоль + изменения коллекции для copyOnWriteArrayListExample()*/
//        Iterator<String> iterator = list.iterator(); // Перебор элементов
//        while (iterator.hasNext()) { // с помощью
//            String element = iterator.next(); // iterator
        for (String element : list) { // Или все в одну строчку вместо трех верхних.
            System.out.printf("  %s\n", element); // печать в консоль.
            if (change) { // если true
                if (element.equals("Collection")) { // если есть элемент Collection, то...
                    list.add("Новая строка"); // добавили и ....
                    list.remove(element); // удалили найденный.
                }
            }
        }
    }
    private static void cyclicBarrierExample() {
        /**
         * CyclicBarrier – барьер для потоков, который ломается, отпускает в работу потоки при достижении критической
         * массы ожидающих. Это тоже класс из Java Concurrency Framework. Поток также встает на ожидание методом await()
         * Ожидающие потоки называются parties, их лимит также устанавливается в конструкторе при создании переменной
         * типа CyclicBarrier.
         * В примере ниже, ПРЕДСТАВИМ, что есть гоночная трасса, "внутри" которой есть 10 машин. Они начинают подготовку
         * к заезду, собираются на линии старта, начинают свой заезд, по завершении заезда, на финише снова собираются
         * все вместе и только после этого разъезжаются по своим боксам.
         * Создали количество машин, которое передали в конструктор при объявлении переменной типа CyclicBarrier.
         * Далее циклом создали "машины", которые вначале готовятся к заезду, далее выезжают на линию старта и именно
         * там запускается метод await(). Именно он будет ожидать полного сбора всех машин, на основании переданного
         * значения при создании переменной типа CyclicBarrier. После того как метод await() "всех собрал", все его
         * слоты заняты, он отпустит все машины (потоки). Кроме этого, после того, как метод await() отпустил все машины
         * /потоки, он обнуляется и его можно использовать повторно. Что и сделано на финише, где все машины снова
         * собираются и только после этого разрежаются по своим боксам.
         * await() может использовать таймаут. Метод await() может выбрасывать исключение BrokenBarrierException.
         * BrokenBarrierException - Исключение, выданное, когда поток пытаются ожидать на барьер, а он находится в
         * нарушенном состоянии, или который вводит нарушенное состояние, в то время как поток ожидает. Например, когда
         * его кто-то еще до барьера интерапнул или поток ждали с таймаутом и не дождались.
         * **********************************************************************************
         * У метода CyclicBarrier есть метод .getNumberWaiting() - который покажет сколько потоков его сейчас ожидают.
         * **********************************************************************************
         * В конструктор метода CyclicBarrierCyclicBarrier помимо int parties, можно передать Runnable barrierAction.
         * В этом случае можно прописать действие, которое будет выполняться кода барьер будет отпускать потоки.
         */
        int carsCount = 10;
//        CyclicBarrier cb = new CyclicBarrier(carsCount); // Буду работать при наборе 10 машин.
        CyclicBarrier cb = new CyclicBarrier(carsCount / 2, () -> { // Буду работать при наборе
            // 5 машин и выполнять определенные действия. carsCount поделен на 2 для примера.
            System.out.println("**************************************");
            System.out.println("СОВЕРШАЮ ДЕЙСТВИЕ КАЖДЫ РАЗ, кода .await() отпускает потоки...");
            System.out.println("**************************************");
        });

        for (int i = 0; i < carsCount; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    System.out.printf("Машина #%d готовится к заезду\n", j);
                    Thread.sleep((long) (500 + 8000 * Math.random())); // имитация процесса работы.
                    System.out.printf("Машина #%d выехала на линию старта\n", j);
                    cb.await(); // ожидание всех машин.
                    System.out.printf("Машина #%d начала свой заезд\n", j);
                    Thread.sleep((long) (500 + 8000 * Math.random())); // имитация процесса работы.
                    System.out.printf("Машина #%d приехала на финиш\n", j);
//                    System.out.printf("#%d слотов в ожидании отправки в бокс\n", cb.getNumberWaiting());
                    cb.await(); // снова ожидаем всех на финише
                    System.out.printf("Машина #%d уехала в свой бокс\n", j);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    private static void semaphoreExample() {
        /**
         * Semaphore - Семафоры представляют еще одно средство синхронизации для доступа к ресурсу.
         * Для управления доступом к ресурсу семафор использует счетчик, представляющий количество разрешений.
         * Если значение счетчика больше нуля, то поток получает доступ к ресурсу, при этом счетчик уменьшается
         * на единицу. После окончания работы с ресурсом поток освобождает семафор, и счетчик увеличивается на единицу.
         * Если же счетчик равен нулю, то поток блокируется и ждет, пока не получит разрешение от семафора.
         * Semaphore(int permits)
         * Semaphore(int permits, boolean fair)
         * Параметр permits указывает на количество допустимых разрешений для доступа к ресурсу.
         * Параметр fair во втором конструкторе позволяет установить очередность получения доступа.
         * Если он равен true, то разрешения будут предоставляться ожидающим потокам в том порядке,
         * в каком они запрашивали доступ. Если же он равен false,
         * то разрешения будут предоставляться в неопределенном порядке.
         * Для получения разрешения у семафора надо вызвать метод acquire(), который имеет две формы:
         * void acquire()
         * void acquire(int permits)
         * После окончания работы с ресурсом полученное ранее разрешение надо освободить с помощью метода release():
         * void release()
         * void release(int permits)
         * <>https://metanit.com/java/tutorial/8.6.php</>
         * В примере ниже представим дорогу с большим количеством полос движения в одном направлении. Машины - как
         * один из потоков. Дорога упирается в мост, на котором дорога из 10 полос перестраивается в движение по
         * 4 полосам:
         */
        Semaphore semaphore = new Semaphore(4);

        for (int i = 0; i < 20; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    System.out.printf("Машина #%d подъехала к мосту\n", j);
                    semaphore.acquire(1); // занять семафор
                    System.out.printf("Машина #%d начала движение по мосту\n", j);
                    Thread.sleep((long) (500 + 8000 * Math.random())); // имитация процесса работы.
                    System.out.printf("Машина #%d проехала мост. Продолжила движение далее...\n", j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // освободить семафор
                }
            }).start();
        }
    }
    private static void countDownLatchExample() {
        /**
         * CountDownLatch, дословно «Запор с обратным отсчетом», – примитив синхронизации из стандартной
         * библиотеки Java. Он останавливает пришедшие потоки, пока внутренний счетчик не достигнет нуля.
         * Чтобы поставить поток на ожидание, нужно вызвать из него метод await() .
         * CountDownLatch - можно рассматривать, как продвинутый join. Позволяет "заджойница" на "кучу потоков",
         * при этом не на завершение всех потоков, а на определенный момент. В примере закомментированная строка, в
         * которой new CountDownLatch(threads / 2) - на 5 щелчков, а строка new CountDownLatch(threads) - на 10.
         * Сам процесс отсчета реализовывается методом countDown().
         * Кроме этого метод .await(5, TimeUnit.SECONDS) с перегрузкой по времени возвращает boolean -> по-этому
         * использован блок if - else. Задумка тайминга в этом методе заключается в следующем: Если за указанное время
         * количество считаемых потоков new CountDownLatch(threads) или new CountDownLatch(threads / 2) успевает
         * запуститься + выполнить свою работу + завершиться, а считаем/отщелкиваем их методом .countDown(), то
         * выполняется блок if иначе else. При этом в цикле for всегда создается 10 потоков, а следить и ждать
         * исполнения мы можем 5 потоков -> new CountDownLatch(threads / 2). При этом остальные потоки не
         * останавливаются, а продолжают свою работу и закрываются по завершении выполнения своих задач.
         * Добавить дополнительные щелчки в метод .countDown() по сле его запуска мы не сможем. То есть запустился,
         * отщелкал сове, указанное при создании и всё...
         * .getCount() -> возвращает оставшееся количество щелчков из .countDown()
         */
        int threads = 10;
//        CountDownLatch cdl = new CountDownLatch(threads);
        CountDownLatch cdl = new CountDownLatch(threads / 2);

        for (int i = 0; i < threads; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    System.out.printf("Поток #%d начал свою работу\n", j);
                    Thread.sleep((long) (500 + 8000 * Math.random())); // имитация процесса работы.
                    System.out.printf("Поток #%d закончил свою работу\n", j);
                    cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            if (cdl.await(15, TimeUnit.SECONDS)) {
                System.out.println("Все корректно отработало.");
            } else {
                System.out.println("Что то пошло не так. Время ожидания закончилось.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Осталось щелчков #%d\n", cdl.getCount());
    }
    private static void reentrantReadWriteLockExample() {
        /**
         * ReentrantReadWriteLock - может и прочитать и записать данные. Причем, потоки на чтение, одновременное чтение
         * не ограничены, но как только дойдет очередь до потока (поток S) которому необходимо записать, изменить
         * данные, то все остальные потоки в очереди будут дожидаться внесения изменений (потоком S) и только после
         * отключения (потока S) продолжится работа потоков в очереди. То есть - читать могут не ограниченное количество
         * потоков, а записывают только по одному, остальные ждут.
         * Такой метод будет хорош, если данные читаются чаще, чем изменяются!!!!!!
         */
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
        new Thread(() -> {
            System.out.println("Подходим к замку №1");
            try {
                rrwl.readLock().lock();
                System.out.println("Читаем замок №1");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №1");
                rrwl.readLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
        new Thread(() -> {
            System.out.println("Подходим к замку №2");
            try {
                rrwl.readLock().lock();
                System.out.println("Читаем замок №2");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №2");
                rrwl.readLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
        new Thread(() -> {
            System.out.println("Подходим к замку №3");
            try {
                rrwl.readLock().lock();
                System.out.println("Читаем замок №3");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №3");
                rrwl.readLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
        new Thread(() -> {
            System.out.println("Подходим к замку №4");
            try {
                rrwl.readLock().lock();
                System.out.println("Читаем замок №4");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №4");
                rrwl.readLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
        new Thread(() -> {
            System.out.println("Подходим к замку № 11111");
            try {
                rrwl.writeLock().lock();
                System.out.println("Делаем запись в замок № 11111");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок № 11111");
                rrwl.writeLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
        new Thread(() -> {
            System.out.println("Подходим к замку № 22222");
            try {
                rrwl.writeLock().lock();
                System.out.println("Делаем запись в замок № 22222");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок № 22222");
                rrwl.writeLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
        new Thread(() -> {
            System.out.println("Подходим к замку №5");
            try {
                rrwl.readLock().lock();
                System.out.println("Читаем замок №5");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №5");
                rrwl.readLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
        new Thread(() -> {
            System.out.println("Подходим к замку №6");
            try {
                rrwl.readLock().lock();
                System.out.println("Читаем замок №6");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №6");
                rrwl.readLock().unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
    }
    private static void reentrantLockExample_If_Else() {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            System.out.println("Подходим к замку №1");
            try {
                lock.lock(); /* Если lock свободен, то приваливаемся в блок try, закрываем за собой замок/доступ lock().
                    Делаем необходимые действия блока try. в любом случае finally и снимаем замок. Освобождаем поток.*/
                System.out.println("Заняли замок №1");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №1");
                lock.unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
/**
 * В этом примере добавлен блок if else. В if применен boolean метод tryLock(), который вернет true если реализация
 * метода свободна, не закрыта другим потоком. В противном случае выполнится блок else, в котором возможна другая логика
 * работы для этого, свободного потока. Кроме этого в метод tryLock() может использоваться как без передуваемых ему
 * аргументов -> вернет true или false. Либо передать в него возможное время ожидания для начала выполнения работы
 * потоком, если время ожидания истекло, а подключение не освободилось, то выполнится блок else.
 */
        new Thread(() -> {
            System.out.println("Подходим к замку №3");
            try {
                if (lock.tryLock(1, TimeUnit.SECONDS)) { // tryLock() возвращает  boolean и если true - то выполняем try.
                    try {
                        System.out.println("Заняли замок №3");
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Освободили замок №3");
                        lock.unlock();
                    }
                } else {

                    System.out.println("Пойду выполнять другое задание...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private static void reentrantLockExample() {
        /**
         * В этой реализации вместо synchronized используются встроенные методы lock() и unlock()
         * ПРИМЕЧАНИЕ: У такой реализации есть, возможные, проблемы. Дело в том, что никто не сможет запретить
         * занять/закрыть поток в одном методе, а освободить/открыть его в другом методе или даже в другом классе.
         * По-этому освобождать его лучше сразу в этом же методе, в блоке finally.
         */
        Lock lock = new ReentrantLock(); // ReentrantLock -> Принадлежит потоку, который последним успешно заблокировал,
        // но еще не разблокировал его.

        new Thread(() -> {
            System.out.println("Подходим к замку №1");
            try {
                lock.lock(); /* Если lock свободен, то приваливаемся в блок try, закрываем за собой замок/доступ lock().
                    Делаем необходимые действия блока try. в любом случае finally и снимаем замок. Освобождаем поток.*/
                System.out.println("Заняли замок №1");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №1");
                lock.unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();

        new Thread(() -> {
            System.out.println("Подходим к замку №2");
            try {
                lock.lock(); /* Если lock свободен, то приваливаемся в блок try, закрываем за собой замок/доступ lock().
                    Делаем необходимые действия блока try. в любом случае finally и снимаем замок. Освобождаем поток.*/
                System.out.println("Заняли замок №2");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Освободили замок №2");
                lock.unlock(); // Снимет замок. Освобождает поток.
            }
        }).start();
    }
}
