package ru.medwedSa.Java_3.Lessen_3_Java_io.ClassWork;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ClassWork {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        simpleExampleOfWorkingWithFiles(); // Описание работы в самом методе.
//        recursiveDirectoryTraversal(new File("./")); // Метод выведет в консоль все каталоги и файлы
                                   // от начала - указанного пути. "./" - путь от начала Java_3, выведет все содержимое.
//        exampleFileWrite();
//        exampleFileRead();
//        exampleFileReadByte();
//        exampleFileReadByteBuffer();
//        exampleFileReadBufferedInputStream();
//        exampleFileBufferReader();
//        exampleSequenceInputStream();
//        exampleRandomAccessFile();
//        exampleSerializable();
        CatEx cat = new CatEx("Murzik", "red");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("example/murzik.sr"))) {
            oos.writeObject(cat); // Записал объект cat в виде байтов в файл murzik.sr
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("example/murzik.sr"))) {
            CatEx deserialized = (CatEx) ois.readObject(); // считал данные из файла

            System.out.println(cat); // распечатать исходного кота
            System.out.println(deserialized); // распечатать десериализованного кота
            System.out.println(cat == deserialized); // один и тот-же этот объект кота или нет
            System.out.println(cat.equals(deserialized)); // одинаковые ли ссылки на эти объекты?
        }

    }

    private static void exampleSerializable() throws IOException, ClassNotFoundException {
        /**
         * Сериализация - это сохранение состояния объектов в последовательность байтов.
         * Десериализация - процесс извлечения или восстановления состояния объекта из последовательности байтов.
         * Почитать тут -> ></https://javarush.com/groups/posts/2898-chto-skrihvaet-modifikator-transient-v-java>
         * ***************
         * transient — это модификатор, указываемый перед полем класса (подобно другим модификаторам, таким как public,
         * final и т.д.) для обозначения того, что данное поле не должно быть сериализовано.
         * Поля, помеченные ключевым словом transient, не сериализуются.
         * Почитать тут -> ></https://javarush.com/groups/posts/2898-chto-skrihvaet-modifikator-transient-v-java>
         *
         */
        Cat cat = new Cat("Barsik", "red");
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("example/barsik.sr"))) {
//            oos.writeObject(cat); // Записал объект cat в виде байтов в файл barsik.sr
//        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("example/barsik.sr"))) {
            Cat deserialized = (Cat) ois.readObject(); // считал данные из файла

            System.out.println(cat); // распечатать исходного кота
            System.out.println(deserialized); // распечатать десериализованного кота
            System.out.println(cat == deserialized); // один и тот-же этот объект кота или нет
            System.out.println(cat.equals(deserialized)); // одинаковые ли ссылки на эти объекты?
        }
    }

    private static void exampleRandomAccessFile() throws IOException {
        /**
         * RandomAccessFile – класс пакета Java IO API, он позволяет перемещаться по файлу, читать из него или писать
         * в него, как вам будет угодно. Вы также сможете заменить существующие части файла, речь идет об обновлении
         * содержимого файла, а точней об обновлении фрагмента файла.
         * Ссылка по изучать -> ></https://devcolibri.com/урок-4-randomaccessfile-и-его-возможности/#:~:text=RandomAccessFile%20–%20класс%20пакета%20Java%20IO,точней%20о%20обновлении%20фрагмента%20файла>
         */
        try (RandomAccessFile raf = new RandomAccessFile("example/ex1.txt", "r")) {
            int b;
            raf.seek(41); // Можно указать позицию с которой начать вывод или сохранение файла.
            while ((b =  raf.read()) > -1) { // Пока есть что считывать... Метод read() - если читать нечего вернет -1
                System.out.print((char) b); // Вывод в виде строк в консоль.
            }
        }
    }

    private static void exampleSequenceInputStream() throws IOException {
        ArrayList<InputStream> streams = new ArrayList<>(); // Создал ArrayList со InputStream внутри.
        streams.add(new FileInputStream("example/ex1.txt")); // добавил несколько файлов для чтения данных.
        streams.add(new FileInputStream("example/ex3.txt")); // добавил несколько файлов для чтения данных.
        streams.add(new FileInputStream("example/ex4.txt")); // добавил несколько файлов для чтения данных.
        /**
         * SequenceInputStream - представляет логическую связь входных потоков. Это начинается с упорядоченным
         * набором входных потоков и читает из первого, пока конец файла не достигается, после чего читает из
         * второго, и так далее, пока конец файла не достигается на последнем из содержавших входных потоков.
         * ••••••••••••••
         * SequenceInputStream принимает в себя пронумерованные потоки, что достигается методом .enumeration()
         */
        try (SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(streams))) {
            /**
             * Никто не запретит завернуть SequenceInputStream в BufferedInputStream, что может прибавить скорости
             * вычисления для заданной в коде операции.
             */
//        BufferedInputStream bf = new BufferedInputStream
//                (new SequenceInputStream(Collections.enumeration(streams))); // Так завернуть можно.

            int b; // Переменная для сбора данных из буфера.
            while ((b = sis.read()) > -1) { // Пока есть что считывать... Метод read() - если читать нечего вернет -1
                System.out.print((char) b); // Вывод в виде строк в консоль.
            }
        }
    }

    private static void exampleFileBufferReader() throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader("example/ex1.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) { // Пока есть что читать в файле.
//                System.out.println(line); // распечатал содержимое файла в консоль.

                bf.lines() // получил стрим строк.
                        .distinct() // Удалил повторы из всего списка строк.
                        .filter(s -> s.contains(" ")) // отфильтровал, оставив все строки с пробелами.
                        .forEach(System.out::println); // распечатать в консоль оставшиеся строки.
            }
        }
    }

    private static void exampleFileReadBufferedInputStream() throws IOException {
        /**
         * Считывание данных из файла можно без использования руками прописанного буфера. С помощью встроенного
         * класса BufferedInputStream, который является наследником класса FilterInputStream. При создании переменной
         * для хранения считанных данных в буфере BufferedInputStream заворачивается FileInputStream с путем
         *  к файлу для считывая.
         *  Прироста скорости я не заметил... Возможно в некоторых случаях это удобнее... Но при увеличении вручную
         *  прописанного буфера с 512 до 200512 я получил:
         *  При создании буфера в ручную -> new byte[200512] = 3 мил. сек.
         *  При использовании BufferedInputStream -> 169 мил. сек.
         *  (файл для считывания один).
         */
        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("example/ex2.txt"))) {
            int b; // Переменная для сбора байтиков. Байт - цифра, по-этому переменная int.
            while ((b =  bis.read()) > 1) {
                // Пока есть что считывать... Метод read() - если читать нечего вернет -1
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void exampleFileReadByteBuffer() throws IOException {
        /**
         * Чтение из файла с помощью созданного буфера из 8 byte сократило время считывания в 7,5 раза, до 969 мил. сек.
         * Буфер сокращает количество обращений, запросов на чтение из файла.
         * При увеличении буфера до 512 byte, скорость увеличатся (от считывания без использования буфера) в 180 раз и
         * составит 37 мил. сек..
         */
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("example/ex2.txt")) { // Чтение из файла ,буфер 8 byte.
//            byte[] buf = new byte[8]; // Создал буфер из 8 byte.
            byte[] buf = new byte[512]; // Создал буфер из 512 byte.
            int b; // Переменная для сбора данных из буфера.
            while ((b =  fis.read(buf)) > 1) {
                // Пока есть что считывать... Метод read() - если читать нечего вернет -1
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void exampleFileReadByte() throws IOException {
        /**
         * Чтение файла побайтово. Файл размером 7,7 Мб читался 7150 мил. сек. Из кода видно, что чтение происходит
         * каждого байта отдельно, то есть каждый раз происходит запрос на чтение каждого байта. Итог: ДОЛГО!
         */
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("example/ex2.txt")) { // Чтение из файла, побайтово на время.
            int b; // Переменная для сбора байтиков. Байт - цифра, по-этому переменная int.
            while ((b =  fis.read()) > 1) {
                // Пока есть что считывать... Метод read() - если читать нечего вернет -1
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void exampleFileRead() throws IOException {
        try (FileInputStream fis = new FileInputStream("example/ex1.txt")) { // Чтение из файла, побайтово.
            int b; // Переменная для сбора байтиков. Байт - цифра, по-этому переменная int.
            while ((b =  fis.read()) > 1) { // Пока есть что считывать... Метод read() - если читать нечего вернет -1
                System.out.print((char) b); // Для вывода в виде букв привести к char ->  System.out.print((char) b);
                                            // Вывод в консоль в виде байтиков. ->  System.out.print(b);
            }
        }
        System.out.println("\n");
    }

    private static void exampleFileWrite() throws IOException {
        File file = new File("example/ex1.txt");
        String text = "Hello World!";

        try (FileOutputStream fos = new FileOutputStream(file)) { // Запись в файл.
            fos.write(text.getBytes()); // тк метод write может предать byte[], то преобразовали text.getBytes()
                     // В файле ex1.txt увидим снова текстовое представление из байтов, тк файл с расширением .txt
        }
    }

    private static void recursiveDirectoryTraversal(File root) {
        if (root.isFile()) { // если встретился файл, то...
            System.out.println("File ---> " + root.getPath()); // Печатаем File ---> + путь.
        } else { // иначе директория, и ...
            System.out.println("Catalog ---> " + root.getPath()); // Печатаем Catalog ---> + путь.
            File[] files = root.listFiles(); // создал массив для найденных данных,
            for (File file : files) { // Иду по всем директориям и файлам от начала пути указанного при запуске метода.
                recursiveDirectoryTraversal(file); // рекурсия запущена.
            }
        }
    }

    private static void simpleExampleOfWorkingWithFiles() throws IOException {
        /**
         * Создание объекта тип File - это не представление самого файла, это некая Java абстракция которая
         * позволяет с эим файлом работать. Сам по себе File представляет операции не чтения файла, а больше
         * операции типа создания, удаления, проверки наличия, установки атрибутов, есть ли права на чтение файла,
         * есть ли права на запись в файл, является ли файл исполняемым файлом, свободное пространство, имя файла,
         * является ли файл скрытым файлом, последнее время изменения, ...
         */
        File file = new File("example/ex1.txt"); // указал путь (каталог) и им.я файла
        File directory = new File("example"); // если каталога такого нет, то будем его создавать.
//        System.out.println(file.exists()); // Проверка на наличие файла. exists() - boolean.
        if (!directory.exists()) { // если каталог не существует, то...
            directory.mkdir(); // создать каталог.
        }
        if (!file.exists()) { // если файл не существует, то...
            file.createNewFile(); // создать файл.
        }
        System.out.println(file.isFile()); //  Являться file файлом? - true
        System.out.println(file.isDirectory()); // Является file директорией? - false
        System.out.println(directory.isFile()); // Являться directory файлом? - false
        System.out.println(directory.isDirectory()); // Является directory директорией? - true
//        file.delete(); // удалит файл.
//        directory.delete(); // Удалит директорию, но только после удаления файла из нее. Если внутри директории
        // есть хоть оди файл - удаление директории невозможно, при этом Exception не будет.
        /**
         * mkdir() - создаст один каталог, а
         * mkdirs() - может создать дерево каталогов.
         * •••••••••••••••
         * Примечание: Причем, ранее созданный файл ex1.txt так и останется в корне папки example, несмотря на
         * созданную структуру/дерево каталогов(папок).
         */
        File directory1 = new File("example/11/22/33/44/55"); // Желание создать дерево директорий.
        System.out.println(directory1.mkdirs()); // вывод о создании дерева каталога - true или false в консоль.
        /**
         *  Предположим, что в последней папке созданного дерева (ранее по коду) лежать три файла. Можно достать
         *  список, название находящихся там файлов.
         *  list() - возвращает String[].  Пройдет по всему созданному дереву и достанет ИМЕНА крайних файлов.
         *  listFiles() - возвращает File[]. Пройдет по всему созданному дереву и достанет ИМЕНА + ПУТЬ до крайних файлов.
         */
        String[] list = directory1.list();
        for (String s : list) {
            System.out.println(s); // Вывоз имена файлов в консоль из конца прописанной директории в directory1.
        }
        File[] files = directory1.listFiles();
        for (File file1 : files) {
            System.out.println(file1); // Вывоз имена + путь файлов в консоль из конца прописанной директории в directory1.
        }
        File[] files1 = directory1.listFiles((dir, name) -> name.matches("ex4.txt"));
        for (File file1 : files1) {
            System.out.println(file1); // Выведет файл по из указанного в фильтре + весь путь до него.
        }
        File[] files2 = directory1.listFiles(File::isDirectory);
        for (File file1 : files2) {
            System.out.println(file1); // Получение всех директорий + путь до них, по прописанному пути в directory1.
        }
    }
}
