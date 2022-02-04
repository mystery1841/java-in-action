package learning.library.time;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public void testCreateDate() {
        Date date = new Date();
        System.out.println(date);
        long msec = date.getTime();
        System.out.println("Milliseconds since Jan. 1, 1970 GMT = " + msec);
    }

    @Test
    public void testCreateCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 29);
        calendar.set(Calendar.SECOND, 22);
        System.out.print("Updated time: ");
        System.out.print(calendar.get(Calendar.HOUR) + ":");
        System.out.print(calendar.get(Calendar.MINUTE) + ":");
        System.out.println(calendar.get(Calendar.SECOND));
        Instant i = Instant.now();
    }
}
