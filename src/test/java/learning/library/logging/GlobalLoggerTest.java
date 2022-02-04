package learning.library.logging;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class GlobalLoggerTest {

    @Test
    public void testGlobalLogging() {
        Logger.getGlobal().info("File->Open menu item selected");
    }
}
