package learning.fp.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertStreamTest {

    @Test
    public void testStreamFilter() {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add(",");
        words.add("world");
        Stream<String> longWords = words.stream().filter(w -> w.length() > 4);
        assertEquals(2, longWords.count());
    }

    @Test
    public void testStreamMap() {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add(",");
        words.add("world");
        Stream<String> firstLetters = words.stream().map(s -> s.substring(0, 1));
        assertEquals("h,w", firstLetters.collect(Collectors.joining()));
    }

    @Test
    public void testStreamLimit() {
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        assertEquals(100, randoms.count());
    }

    @Test
    public void testStreamTakeWhile() {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add(",");
        words.add("world");
        Stream<String> longWords = words.stream().takeWhile(w -> w.length() > 4);
        assertEquals(1, longWords.count());
    }

    @Test
    public void testStreamPeek() {
        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(x -> {
                    return;
                })
                .limit(20).toArray();
        assertEquals(20, powers.length);
    }
}
