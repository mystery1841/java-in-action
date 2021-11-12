package learning.meta.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ClassTest {

    @Test
    public void testGetName() {
        var generator = new Random();
        Class cl = generator.getClass();
        String name = cl.getName();
        assertEquals("java.util.Random", name);
    }

    @Test
    public void testForName() {
        String className = "java.util.Random";
        try {
            Class cl = Class.forName(className);
            String name = cl.getName();
            assertEquals("java.util.Random", name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShorthandClass() {
        Class cl1 = Random.class;
        Class cl2 = int.class;
        Class cl3 = Double[].class;
        assertEquals("java.util.Random", cl1.getName());
        assertEquals("int", cl2.getName());
        assertEquals("[Ljava.lang.Double;", cl3.getName());
    }

    @Test
    public void testNewInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        var className = "java.util.Random";
        Class cl = Class.forName(className);
        Object obj = cl.getConstructor().newInstance();
        assertTrue(obj instanceof Random);
    }

}
