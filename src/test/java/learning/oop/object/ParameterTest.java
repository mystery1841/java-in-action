package learning.oop.object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterTest {

    public static double max(double... values) {
        double largest = Double.NEGATIVE_INFINITY;
        for (double v : values) if (v > largest) largest = v;
        return largest;
    }

    public static double min(double[] values) {
        double smallest = Double.POSITIVE_INFINITY;
        for (double v : values) if (v < smallest) smallest = v;
        return smallest;
    }

    @Test
    public void testVarargsParameter() {
        double m = max(3.1, 40.4, -5);
        assertEquals(40.4, m);
    }

    @Test
    public void testVarargsParameterCallByArray() {
        double m = max(new double[]{3.1, 40.4, -5});
        assertEquals(40.4, m);
    }

    @Test
    public void testArrayParameter() {
        double m = min(new double[]{3.1, 40.4, -5});
        assertEquals(-5, m);
    }

}
