package learning.fp.lamdba;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class MergeFunctionTest {


    @Test
    public void testFunctionAndThen() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        assertEquals(4, result);
    }

    public void testFunctionCompose() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.compose(g);
        int result = h.apply(1);
        assertEquals(3, result);
    }
}
