package learning.meta.reflection;

import learning.oop.Employee;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    @Test
    public void testFieldGetValue() throws NoSuchFieldException, IllegalAccessException {
        var harry = new Employee("Harry Hacker", 50000, 1989, 1, 10);
        Class cl = harry.getClass();
        Field f = cl.getDeclaredField("name");
        assertThrows(IllegalAccessException.class, () -> {
            Object v = f.get(harry);
        });
    }
}
