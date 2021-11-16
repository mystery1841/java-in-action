package learning.oop.interfaces;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ComparableTest {

    @Test
    public void testBigDecimalCompareTo() {
        BigDecimal x = new BigDecimal("1.0");
        BigDecimal y = new BigDecimal("1.00");
        boolean b = x.equals(y);
        int n = x.compareTo(y);
        assertFalse(b);
        assertEquals(0, n);
    }
}
