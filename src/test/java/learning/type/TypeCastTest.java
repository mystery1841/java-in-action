package learning.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeCastTest {

    @Test
    public void testForcedCast() {
        double x = 9.997;
        int nx = (int) x;
        assertEquals(9, nx);
    }
}
