package learning.library.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class InstantTest {

    public void testCreateInstant() {
        Instant start = Instant.now();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis();
    }

    public void testCreateDuration() {
        Instant start = Instant.now();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        Instant start2 = Instant.now();
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        boolean overTenTimesFaster = timeElapsed.multipliedBy(10).minus(timeElapsed2).isNegative();
    }

}
