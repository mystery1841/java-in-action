package learning.library.math;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class MathTest {

    @Test
    public void testAbs() {
        int abs1 = Math.abs(10);
        int abs2 = Math.abs(-20);
        assertEquals(10, abs1);
        assertEquals(20, abs2);
    }

    @Test
    public void testCeil() {
        double ceil = Math.ceil(7.343);
        assertEquals(8.0, ceil);
    }

    @Test
    public void testFloor() {
        double floor = Math.floor(7.343);
        assertEquals(7.0, floor);
    }

    @Test
    public void testFloorDiv() {
        int result1 = Math.floorDiv(-100, 9);
        int result2 = -100 / 9;
        assertEquals(-12, result1);
        assertEquals(-11, result2);
    }

    @Test
    public void testFloorMod() {
        assertEquals(1, Math.floorMod(7, 3));
        assertEquals(-1, Math.floorMod(-7, -3));
        assertEquals(2, Math.floorMod(-7, 3));
        assertEquals(-2, Math.floorMod(7, -3));
    }

    @Test
    public void testMinAndMax() {
        int min = Math.min(10, 20);
        int max = Math.max(10, 20);
        assertEquals(10, min);
        assertEquals(20, max);
    }

    @Test
    public void testRound() {
        double roundedDown = Math.round(23.445);
        double roundedUp = Math.round(23.545);
        assertEquals(23, roundedDown);
        assertEquals(24, roundedUp);
    }

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
    public void testExp() {
        double exp1 = Math.exp(1);
        double exp2 = Math.exp(2);
        assertEquals(2.718281828459045, exp1);
        assertEquals(7.38905609893065, exp2);
    }

    @Test
    public void testLog() {
        double log1 = Math.log(1);
        double log10 = Math.log(10);
        assertEquals(0.0, log1);
        assertEquals(2.302585092994046, log10);
    }

    @Test
    public void testLog10() {
        double log10_1 = Math.log10(1);
        double log10_100 = Math.log10(100);
        assertEquals(0.0, log10_1);
        assertEquals(2.0, log10_100);
    }
}
