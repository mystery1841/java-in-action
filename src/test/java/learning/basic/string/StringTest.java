package learning.basic.string;

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
        assertEquals(5, cpCount);
    }

    @Test
    public void testCharAt() {
        String greeting = "Hello";
        char first = greeting.charAt(0);
        char last = greeting.charAt(4);
        assertEquals('H', first);
        assertEquals('o', last);
    }

    @Test
    public void testCodePoint() {
        String greeting = "Hello😀";
        int i = 5;
        int index = greeting.offsetByCodePoints(0, i);
        int cp = greeting.codePointAt(index);
        int[] array = new int[]{cp};
        String emoji = new String(array, 0, array.length);
        assertEquals("😀", emoji);
    }

    @Test
    public void testTraversesCodePoint() {
        String greeting = "Hello😀";
        int i = 0;
        int j = 0;
        int length = greeting.codePointCount(0, greeting.length());
        int[] array = new int[length];
        while (i < greeting.length()) {
            int cp = greeting.codePointAt(i);
            array[j] = cp;
            if (Character.isSupplementaryCodePoint(cp)) {
                i += 2;
            } else {
                i++;
            }
            j++;
        }
        String result = new String(array, 0, array.length);
        assertEquals("Hello😀", result);
    }

    @Test
    public void testBackwardsByCodePoint() {
        String greeting = "Hello😀";
        int i = greeting.length();
        int j = 0;
        int length = greeting.codePointCount(0, greeting.length());
        int[] array = new int[length];
        while (i > 0) {
            i--;
            if (Character.isSurrogate(greeting.charAt(i)))
                i--;
            int cp = greeting.codePointAt(i);
            array[j] = cp;
            j++;
        }
        String result = new String(array, 0, array.length);
        assertEquals("😀olleH", result);
    }

    @Test
    public void testCodePoints() {
        String greeting = "Hello😀";
        int[] codePoints = greeting.codePoints().toArray();
        String result = new String(codePoints, 0, codePoints.length);
        assertEquals("Hello😀", result);
    }

    @Test
    public void testStringBuilder() {
        StringBuilder builder = new StringBuilder();
        String ch = "hello,";
        String str = "world!";
        builder.append(ch); // appends a single character
        builder.append(str); // appends a string
        String completedString = builder.toString();
        assertEquals("hello,world!",completedString);
    }

}
