package learning.operator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathTest {

    @Test
    public void testSqrt() {
        double x = 4;
        double y = Math.sqrt(x);
        assertEquals(2, y);
    }

    @Test
    public void testPow() {
        double x = 4;
        double a = 2;
        double y = Math.pow(x, a);
        assertEquals(16, y);
    }

    @Test
    public void testRound() {
        double x = 9.997;
        int nx = (int) Math.round(x);
        assertEquals(10, nx);
    }
}
