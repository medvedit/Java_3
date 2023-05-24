package ru.medwedSa.Java_3.Lessen_6_log_test.ClassWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Trace << Debug << Info << Warn << Error << Fatal -  уровни/виды логирования.
public class LoggingLog4jExample {
//    private static final Logger log = LogManager.getLogger(); // getLogger() не передан не какой аргумент, по-этому
//                                             логирование будет производиться по настройке Root в файле log4/2.xml
//    private static final Logger log = LogManager.getLogger("super_logger"); // Логирование будет производиться
                                            // только для конфигурации Logger name="super_logger" в файле log4/2.xml
    private static final Logger log = LogManager.getLogger(LoggingLog4jExample.class);
    public static void main(String[] args) {
//        logExample();
//        logThrowingExample();
//        logPlaceholderExample();

    }

    private static void logPlaceholderExample() {
        /*
         * В логи можно вставлять данные плейсхолдерами.
         */
        String str = "Данные на 23 мая";
        int count = 123;

        log.info("Info {} : log {}", str, count); // Плейсхолдерами являются фигурные скобки.
        log.info("Thread log {}", Thread.currentThread()); // информация о потоке в плейсхолдер.

        new Thread(() -> log.info("Информация об этом Thread")).start(); // Вывод информации из созданного и
                                                                              // запущенного потока.
    }

    private static void logThrowingExample() {
        try {
            throw new Exception("Тут ошибка");
        } catch (Exception e) {
//            log.error(e); // При вызове .error нет вывода номера строки об ошибке.
            log.throwing(e); // По этому лучше и принято использовать .throwing
        }
    }

    private static void logExample() {
        log.trace("Trace log"); // Логирование каждого "чиха" приложения. Чаще для разраба.
        log.debug("Debug log"); // Информация для отладки. Чаще для разраба.
        log.info("Info log"); // Уровень для информативных сообщений. Для сис. администратора, как пример.
        log.warn("Warn log"); // Это еще не ошибка, но то, на что стоит обратить внимание.
        log.error("Error log"); // Уровень ошибки.
        log.fatal("Fatal log"); // Суши весла... Пиши заявление!
    }
}
