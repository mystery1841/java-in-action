package learning.meta.generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class TypeLiteralTest {

    @Test
    public void testIdentifyLiteral() {
        TypeLiteral<ArrayList<Integer>> literal = new TypeLiteral<>(){};
        TypeLiteral<ArrayList<Integer>> literal2 = new TypeLiteral<>(){};
        TypeLiteral<ArrayList<String>> literal3 = new TypeLiteral<>(){};
        assertEquals(literal, literal2);
        assertNotEquals(literal, literal3);
    }
}
