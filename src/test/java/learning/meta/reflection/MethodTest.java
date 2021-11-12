package learning.meta.reflection;

import learning.oop.Employee;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class MethodTest {

    @Test
    public void testInvoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var harry = new Employee("Harry Hacker", 50000, 1989, 1, 10);
        Method m1 = Employee.class.getMethod("getName");
        Method m2 = Employee.class.getMethod("raiseSalary", double.class);
        String n = (String) m1.invoke(harry);
        m2.invoke(harry, 1);
        assertEquals("Harry Hacker", n);
        assertEquals(50500.0, harry.getSalary());
    }
}
