package learning.library.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest {

    @Test
    public void testFindMatch() {
        Pattern p = Pattern.compile("\\d");
        String s = "abc1234abc";
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            String match = m.group();
            System.out.println(m.group());
        }
    }

    @Test
    public void testQuoteReplacement() {
        String s = "abc12\\abc";
        System.out.println(Matcher.quoteReplacement(s));
    }
}
