package learning.library.time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

public class LocalDateTest {

    public void testCreateLocalDate() {
        LocalDate today = LocalDate.now();
        LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
        alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
        alonzosBirthday.until(today, ChronoUnit.DAYS);
    }

    public void testCreateLocalDateStream() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate endExclusive = LocalDate.now();
        Stream<LocalDate> allDays = start.datesUntil(endExclusive);
        Stream<LocalDate> firstDaysInMonth = start.datesUntil(endExclusive, Period.ofMonths(1));
    }

    @Test
    public void testCreatePeriod() {
        Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
        Duration tenDas = Duration.between(LocalDate.of(2013, 3, 8).atTime(LocalTime.MIN),
                LocalDate.of(2014, 3, 18).atTime(LocalTime.MIN));
        Period tenDays2 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    public void testCreateTemporalAdjusters() {
        LocalDate today = LocalDate.now();
        TemporalAdjuster NEXT_WORKDAY = w ->
        {
            var result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            }
            while (result.getDayOfWeek().getValue() >= 6);
            return result;
        };
        LocalDate backToWork = today.with(NEXT_WORKDAY);
    }

    public void testCreateTemporalAdjustersNoneCast() {
        LocalDate today = LocalDate.now();
        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w ->
        {
            LocalDate result = w;
            do {
                result = result.plusDays(1);
            }
            while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });
        LocalDate backToWork = today.with(NEXT_WORKDAY);
    }
}
