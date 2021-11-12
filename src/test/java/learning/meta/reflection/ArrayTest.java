package learning.meta.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTest {

    public static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if (!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }

    public static Object[] badCopyOf(Object[] a, int newLength) {
        Class cl = a.getClass();
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return (Object[]) newArray;
    }


    @Test
    public void testArrayGoodCopyOf() {
        int[] a = {1, 2, 3};
        a = (int[]) goodCopyOf(a, 10);
        assertArrayEquals(new int[]{1, 2, 3, 0, 0, 0, 0, 0, 0, 0}, a);
    }

    @Test
    public void testBadCopyOf() {
        String[] b = {"Tom", "Dick", "Harry"};
        b = (String[]) badCopyOf(b, 4);
        assertArrayEquals(new String[]{"Tom", "Dick", "Harry", null}, b);
    }
}
