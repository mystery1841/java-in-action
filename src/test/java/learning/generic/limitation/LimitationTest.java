package learning.generic.limitation;


import learning.generic.Pair;
import learning.oop.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LimitationTest {

    @Test
    public void testGenericArray() {
        @SuppressWarnings("unchecked")
        Pair<String>[] table = (Pair<String>[]) new Pair<?>[10];
        Object[] objArray = table;
        assertThrows(ArrayStoreException.class, () -> objArray[0] = "Hello");
        Pair<Employee> e = new Pair<>();
        e.setFirst(new Employee("Harry Hacker", 50000, 1989, 1, 10));
        objArray[0] = e;
        assertThrows(ClassCastException.class, () -> table[0].getFirst().length());
    }

    @Test
    public void testGenericArrayVarargs() {
        List<Pair<String>> list = new ArrayList<>();
        Varargs.addAll(list, new Pair<>(),new Pair<>());
    }

    public void testGenericArrayVarargsException() {

    }
}
