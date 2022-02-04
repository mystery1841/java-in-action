package learning.fp.stream;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

public class CreateStreamTest {

    @Test
    public void testLimitedStream() {
        var limit = new BigInteger("10000000");
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO,
                n -> n.compareTo(limit) < 0,
                n -> n.add(BigInteger.ONE));
        assertEquals(1_0000_000, integers.count());
    }

    @Test
    public void testIterableStream() {
        Collection<String> iterable = new ArrayList<>();
        iterable.add("");
        Stream<String> stream = StreamSupport.stream(iterable.spliterator(), false);
        assertEquals(1, stream.count());
    }

    @Test
    public void test2() {
        Collection<String> iterable = new ArrayList<>();
        iterable.add("");
        Iterator<String> iterator = iterable.iterator();
        Stream<String> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        assertEquals(1, stream.count());
        
    }
}
