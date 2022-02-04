package learning.fp.stream;

import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReductionTest {

    @Test
    public void testStreamMax() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add(",");
        list.add("world");
        Stream<String> words = list.stream();
        Optional<String> largest = words.max(String::compareToIgnoreCase);
    }

    public void testStreamReduce() {
        List<Integer> values = List.of(1, 2, 3);
        Optional<Integer> sum = values.stream().reduce((x, y) -> x + y);
    }

    public void testStreamReduce3Argument() {
        Stream<String> words = Stream.of("hello", ", ", "world", ".");
        int result = words.reduce(0,
                (total, word) -> total + word.length(),
                (total1, total2) -> total1 + total2);

    }
}
