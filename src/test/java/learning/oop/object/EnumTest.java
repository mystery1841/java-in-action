package learning.oop.object;

import learning.oop.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnumTest {

    @Test
    public void testToString() {
        Size medium = Size.MEDIUM;
        assertEquals("MEDIUM", medium.toString());
    }

    @Test
    public void testValueOf() {
        Size small = Enum.valueOf(Size.class, "SMALL");
        assertEquals(Size.SMALL, small);
    }

    @Test
    public void testValues() {
        Size[] values = Size.values();
        assertArrayEquals(new Size[]{Size.SMALL, Size.MEDIUM, Size.LARGE, Size.EXTRA_LARGE}, values);
    }

    @Test
    public void testOrdinal() {
        Size medium = Size.MEDIUM;
        assertEquals(1, medium.ordinal());
    }

    @Test
    public void testCompareTo() {
        Size small = Enum.valueOf(Size.class, "SMALL");
        Size medium = Size.MEDIUM;
        assertEquals(-1, small.compareTo(medium));
    }
}
