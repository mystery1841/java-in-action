package learning.oop.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InitializationTest {

    @Test
    public void testInitializationSequence() {
        Data data = new Data();
        assertEquals("a2", data.getA());
        assertEquals("b1", data.getB());
        assertEquals("c0", data.getC());
    }

    @Test
    public void testInitializationCallToAnotherConstructor() {
        Data data = new Data("x");
        assertEquals("x", data.getA());
        assertEquals("b2", data.getB());
        assertEquals("c3", data.getC());
    }
}
