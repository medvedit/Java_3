package ru.medwedSa.Java_3.Lessen_6_log_test.ClassWork;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Прописывается эта зависимость, которая сразу исключает дополнительное создание переменной log, как
 // это было прописано при использовании библиотеки log4/2 (в примерах LoggingExample и LoggingExample1),
 // а именно строки -> private static final Logger log = LogManager.getLogger(LoggingExample.class);
public class LoggingExampleWithSlf4jAndLombok {
    public static void main(String[] args) {

        double d = .0324;
        String str = "Привет!";

        log.info("Number {} and String {}", d, str);

        log.trace("Trace log");
        log.debug("Debug log");
        log.info("Info log");
        log.warn("Warn log");
        log.error("Error log");

        new Thread(() -> log.info("Этот поток")).start();

        try { // Специальное создание ошибки, для логирования/записи в логи
            throw new RuntimeException("Тут проблемка...");
        } catch (RuntimeException e) {
            log.error("произошла ошибка", e); // В библиотеке Slf4j отсутствует метод .throwing. Только .error
        }
    }
}
