package learning.operator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringTest {

    @Test
    public void testSubstring() {
        String greeting = "Hello";
        String s = greeting.substring(0, 3);
        assertEquals("Hel", s);
    }

    @Test
    public void testSplicing() {
        String expletive = "Expletive";
        String PG13 = "deleted";
        String message = expletive + PG13;
        assertEquals("Expletivedeleted", message);
    }

    @Test
    public void testSeparator() {
        String all = String.join(" / ", "S", "M", "L", "XL");
        assertEquals("S / M / L / XL", all);
    }

    @Test
    public void testImmutable() {
        String greeting = "Hel";
        greeting = greeting.substring(0, 3) + "p!";
        assertEquals("Help!", greeting);
    }

    @Test
    public void testEqual() {
        String a = new String("123");
        String b = new String("123");
        assertNotSame(a, b);
    }

    @Test
    public void testUseEqualOperator() {
        String greeting = "Hello";
        assertTrue(greeting == "Hello");
        assertFalse(greeting.substring(0, 3) == "Hel");
    }

    @Test
    public void testLength() {
        String greeting = "Hello";
        int n = greeting.length();
        int cpCount = greeting.codePointCount(0, n);
        assertEquals(5, n);
        assertEquals(5,cpCount);
    }
}
