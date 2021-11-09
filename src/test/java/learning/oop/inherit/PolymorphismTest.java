package learning.oop.inherit;

import learning.oop.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolymorphismTest {

    @Test
    public void testArrayPolymorphism() {
        Manager[] managers = new Manager[10];
        Employee[] staff = managers;
        assertThrows(ArrayStoreException.class, () ->
                staff[0] = new Employee("Harry Hacker", 10, 1, 1, 1));
    }
}
