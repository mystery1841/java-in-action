package learning.library.time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.chrono.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Locale;

public class DateTimeFormatterTest {

    @Test
    public void testStandardFormatter() {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11launch);
        String formatted2 = DateTimeFormatter.RFC_1123_DATE_TIME

                .format(apollo11launch);
        System.out.println(formatted);
        System.out.println(formatted2);
    }

    @Test
    public void testLocaleFormatter() {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        String formatted = formatter.format(apollo11launch);
        formatted = formatter.withLocale(Locale.ENGLISH).format(apollo11launch);
        System.out.println(formatted);
    }

    @Test
    public void testDisplayNameOfWeek() {
        for (DayOfWeek w : DayOfWeek.values())
            System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");
    }

    @Test
    public void testOfPattern() {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("xxx");
        System.out.println(df.format(apollo11launch));
    }

    @Test
    public void testHijrahDate() {
        HijrahDate ramadanDate =
                HijrahDate.now().with(ChronoField.DAY_OF_MONTH, 1)
                        .with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("Ramadan starts on " +
                IsoChronology.INSTANCE.date(ramadanDate) +
                " and ends on " +
                IsoChronology.INSTANCE.date(
                        ramadanDate.with(
                                TemporalAdjusters.lastDayOfMonth())));
    }
}
