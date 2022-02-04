package learning.collection.list;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorTest {

    @Test
    public void testIteratorOfListConcurrentModification() {
        var staff = new LinkedList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        Iterator<String> iter1 = staff.iterator();
        Iterator<String> iter2 = staff.iterator();
        iter1.next();
        iter1.remove();
        assertThrows(ConcurrentModificationException.class, iter2::next);
    }

    @Test
    public void testIteratorOfSetConcurrentModification() {
        var staff = new HashSet<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        Iterator<String> iter1 = staff.iterator();
        Iterator<String> iter2 = staff.iterator();
        iter1.next();
        iter1.remove();
        assertThrows(ConcurrentModificationException.class, iter2::next);
    }
}
