package learning.library.i18n;

import java.text.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.spi.CurrencyNameProvider;

public class LocaleTest {

    public static void main(String[] args) {
        Locale locale = Locale.CANADA;
        Locale.getDefault(Locale.Category.DISPLAY);
        Locale.getDefault(Locale.Category.FORMAT);
        Locale loc = Locale.GERMAN;
        NumberFormat currFmt = NumberFormat.getCurrencyInstance(loc);
        double amt = 123456.78;
        String result = currFmt.format(amt);
        NumberFormat euroFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        Currency c = Currency.getInstance("EUR");
        System.out.println(c.toString());
        System.out.println(c.getCurrencyCode());
        System.out.println(c.getNumericCode());
        System.out.println(c.getNumericCodeAsString());
        euroFormatter.setCurrency(Currency.getInstance("EUR"));
        System.out.println(euroFormatter.format(amt));
        var fmt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
        System.out.println(fmt.format(new Date()));
        Collator coll = Collator.getInstance(Locale.CHINA);
        coll.setStrength(Collator.PRIMARY);
        System.out.println(coll.equals("Angstrom", "Ångstrom"));
        String name = "Ångström";
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD);
        int i = normalized.charAt(8);
        System.out.println("\u0308");
        String msg = MessageFormat.format("On {2}, a {0} destroyed {1} houses and caused {3} of damage.",
                "hurricane", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E8);
    }
}
