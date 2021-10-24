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

    @Test
    public void testLeftAlignment() {
        String message = String.format("%-15s %5s %10s", "Item", "Qty", "Price");
        assertEquals("Item              Qty      Price", message);
    }

    @Test
    public void testFloatWithPrecision() {
        String message = String.format("%-15.15s %5d %10.2f", "Jack's Magic Beans", 4, 4.257);
        assertEquals("Jack's Magic Be     4       4.26", message);
    }

    @Test
    public void testStringWithPrecision() {
        String message = String.format("%-15.15s %5d %10.2f", "Princess Peas", 3, 5.1);
        assertEquals("Princess Peas       3       5.10", message);
    }

    @Test
    public void testWidthAndPrecision() {
        String message1 = String.format("%-15s %5s %10s", "Item", "Qty", "Price");
        String message2 = String.format("%-15.15s %5d %10.2f", "Jack's Magic Beans", 4, 4.257);
        String message3 = String.format("%-15.15s %5d %10.2f", "Princess Peas", 3, 5.1);
        assertEquals("Item              Qty      Price", message1);
        assertEquals("Jack's Magic Be     4       4.26", message2);
        assertEquals("Princess Peas       3       5.10", message3);
    }
}


