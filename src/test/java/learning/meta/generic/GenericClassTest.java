package learning.meta.generic;

import learning.generic.Pair;
import learning.oop.Size;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class GenericClassTest {

    @Test
    public void testGenericConstant() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? super Pair<String>> clazz = Pair.class;
        Constructor<? super Pair<String>> constructor = clazz.getConstructor();
        Object pair = constructor.newInstance();
        assertSame(pair.getClass(), Pair.class);
    }
}
