package learning.library.logging;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class DefaultLoggerTest {

    @BeforeAll
    public static void startup() throws IOException {
        System.setProperty("java.util.logging.config.file", "logging.properties");
        LogManager.getLogManager().updateConfiguration(key->key.endsWith(".level")?((oldValue,newValue)->newValue):((oldValue,newValue)->oldValue));
    }

    @Test
    public void testFinerLogging() {
        Logger logger = Logger.getLogger("com.mycompany.myapp");
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
    }

    @Test
    public void testSubLevelLogger() {
        Logger logger = Logger.getLogger("com.mycompany");
        Logger logger2 = Logger.getLogger("com.mycompany.myapp");
        logger.setLevel(Level.WARNING);
        logger2.severe("severe");
        logger2.warning("warning");
        logger2.info("info");
    }

    @Test
    public void testThrowingLogging() {
        IOException e = new IOException();
        Logger logger = Logger.getLogger("com.mycompany");
        logger.log(Level.WARNING, "warning", e);
    }

    @Test
    public void testTrackLogging() {
        Logger logger = Logger.getLogger("com.mycompany");
        logger.entering("com.mycompany", "play", 1);
    }
}
