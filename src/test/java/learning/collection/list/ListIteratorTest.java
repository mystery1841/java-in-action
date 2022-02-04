package learning.collection.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

public class ListIteratorTest {

    @Test
    public void testAdd() {
        var staff = new LinkedList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        ListIterator<String> iter = staff.listIterator();
        iter.next();
        iter.add("Doug");
        iter.add("Erica");
        assertEquals("Amy", staff.get(0));
        assertEquals("Doug", staff.get(1));
        assertEquals("Erica", staff.get(2));
    }

    @Test
    public void testConcurrentModification() {
        var staff = new LinkedList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        ListIterator<String> iter1 = staff.listIterator();
        ListIterator<String> iter2 = staff.listIterator();
        iter1.next();
        iter1.remove();
        assertThrows(ConcurrentModificationException.class, iter2::next);
    }

    @Test
    public void testConcurrentSetMethod() {
        var staff = new LinkedList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        ListIterator<String> iter1 = staff.listIterator();
        ListIterator<String> iter2 = staff.listIterator();
        iter1.next();
        iter1.set("Doug");
        assertDoesNotThrow(iter2::next);
    }
}
