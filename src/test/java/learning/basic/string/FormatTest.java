package learning.basic.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormatTest {

    @Test
    public void testFormatStringAndDecimal() {
        String name = "maki";
        int age = 18;
        String message = String.format("Hello, %s. Next year, you'll be %d",
                name, age);
        assertEquals("Hello, maki. Next year, you'll be 18", message);
    }
}
