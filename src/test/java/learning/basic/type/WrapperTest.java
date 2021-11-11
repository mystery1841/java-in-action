package learning.basic.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WrapperTest {

    @Test
    public void testWrapperEqual() {
        Integer a = 1000;
        Integer b = 1000;
        assertFalse(a == b);
        assertTrue(a.equals(b));
    }

    @Test
    public void testFixedValueWrapper() {
        Integer a = 100;
        Integer b = 100;
        assertTrue(a == b);
    }

    @Test
    public void testNullWrapper() {
        Integer n = null;
        assertThrows(NullPointerException.class, () -> {
            Integer x = 2 * n;
        });
    }
}
