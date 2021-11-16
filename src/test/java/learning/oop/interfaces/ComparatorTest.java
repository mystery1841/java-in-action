package learning.oop.interfaces;

import learning.oop.Person;
import learning.oop.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorTest {


    @Test
    public void testStringComparator() {
        String[] friends = {"Peter", "Paul", "Mary"};
        Arrays.sort(friends, new LengthComparator());
        assertArrayEquals(new String[]{"Paul", "Mary", "Peter"}, friends);
    }

    @Test
    public void testComparatorComparing() {
        Person[] people = {new Student("Peter", "1st"),
                new Student("Paul", "2nd"),
                new Student("Mary", "3rd")};
        Arrays.sort(people, Comparator.comparing(Person::getName));
        String name1 = people[0].getName();
        String name2 = people[1].getName();
        String name3 = people[2].getName();
        assertEquals(name1, "Mary");
        assertEquals(name2, "Paul");
        assertEquals(name3, "Peter");
    }

    @Test
    public void testComparatorKeyNull() {
        Person[] people = {new Student("Peter", "1st"),
                new Student(null, "2nd"),
                new Student("Mary", "3rd")};
        assertThrows(NullPointerException.class, () ->
                Arrays.sort(people, Comparator.comparing(Person::getName)));
    }

    @Test
    public void testComparatorNullsFirst() {
        Person[] people = {new Student("Peter", "1st"),
                new Student(null, "2nd"),
                new Student("Mary", "3rd")};
        Arrays.sort(people, Comparator.comparing(Person::getName,
                Comparator.nullsFirst(Comparator.naturalOrder())));
        String name = people[0].getName();
        assertNull(name);
    }
}
