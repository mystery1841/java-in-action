package learning.library.time;

import java.time.*;

public class ZoneTimeTest {

    public void testCreateZoneDateTime() {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
    }
}
