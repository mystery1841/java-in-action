package learning.operator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperatorTest {

    @Test
    public void testAssignment() {
        int x = 0;
        x += 3.5;//(int)(x+3.5)
        assertEquals(3, x);
    }

    @Test
    public void testSelfIncreasing() {
        int a = 12;
        a++;
        assertEquals(13, a);

    }

    @Test
    public void testSelfIncreasingPrefixAndSuffix() {
        int m = 7;
        int n = 7;
        int a = 2 * ++m;
        int b = 2 * n++;
        assertEquals(16, a);
        assertEquals(14, b);
    }
}
