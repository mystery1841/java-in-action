package learning.fp.stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void testOrElseGet() {
        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElseGet(() -> System.getProperty("myapp.default"));
        assertNull(result);
    }

    @Test
    public void testOrElseThrow() {
        Optional<String> optionalString = Optional.empty();
        assertThrows(IllegalStateException.class, () -> optionalString.orElseThrow(IllegalStateException::new));
    }
}
