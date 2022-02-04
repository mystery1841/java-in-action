package learning.collection.list;

import learning.concurrency.Bank;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayListTest {

    @Test
    public void testEnsureCapacity() {
        var staff = new ArrayList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        staff.ensureCapacity(100);
        ConcurrentHashMap<Bank,String> map = new ConcurrentHashMap<>();
        Bank b = new Bank(1,2);
        Bank b2 = new Bank(2,2);
        map.put(b,"1");
        map.put(b2,"2");
        System.out.println(map.get(b));
    }
}
