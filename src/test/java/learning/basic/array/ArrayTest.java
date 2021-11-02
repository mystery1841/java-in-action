package learning.basic.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTest {

    @Test
    public void testAnonymousArray() {
        int[] a = {1, 2, 3, 4};
        //a={1, 2, 3, 4}; error
        a = new int[]{2, 3, 4};
        assertArrayEquals(new int[]{2, 3, 4}, a);
    }

    @Test
    public void testPrintArray() {
        int[] a = {1, 2, 3, 4};
        assertEquals("[1, 2, 3, 4]", Arrays.toString(a));
    }
}
