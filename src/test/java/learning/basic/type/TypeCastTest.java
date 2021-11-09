package learning.basic.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeCastTest {

    @Test
    public void testForcedCast() {
        double x = 9.997;
        int nx = (int) x;
        assertEquals(9, nx);
    }

    @Test
    public void testByteCalculation() {
        byte b1 = 2;
        byte b2 = -3;
        byte b3 = (byte) (b1 + b2);
        assertEquals(-1, b3);
    }

}
