package learning.oop.object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ObjectTest {

    @Test
    public void testInstanceFieldDefaultsValue() {
        Data data = new Data();
        assertEquals((byte) 0, data.by);
        assertEquals((short) 0, data.s);
        assertEquals(0, data.i);
        assertEquals(0L, data.l);
        assertEquals('\u0000', data.c);
        assertEquals(0.0f, data.f);
        assertEquals(0.0d, data.d);
        assertFalse(data.bl);
        assertNull(data.e);
    }

    @Test
    public void testUninitializedObjectReference() {
        Data data = new Data();
        assertThrows(NullPointerException.class, () -> data.e.getSalary());
    }

    @Test
    public void testFinalKeywordMutableClass() {
        Employee employee = new Employee("maki", 2000, 1991, 11, 20);
        assertEquals("", employee.getEvaluations().toString());
        employee.giveGoldStar();
        assertThat(employee.getEvaluations().toString(),containsString(": Gold star!\n"));
    }

}
