package ru.medwedSa.Java_3.Lessen_6_log_test.ClassWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Trace << Debug << Info << Warn << Error << Fatal -  уровни/виды логирования.
public class LoggingExample {
    private static final Logger log = LogManager.getLogger();
    public static void main(String[] args) {
        log.trace("Trace log");
        log.debug("Debug log");
        log.info("Info log");
        log.warn("Warn log");
        log.error("Error log");
        log.fatal("Fatal log");
    }
}
