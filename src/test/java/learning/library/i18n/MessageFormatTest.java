package learning.library.i18n;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class MessageFormatTest {


    @Test
    public static void main(String[] args) {
        String pattern = "{0,choice,0#no houses|1#one house|2#{1} houses}";
        String result = MessageFormat.format(pattern, 0);
        String result2 = MessageFormat.format(pattern, 1);
        String result3 = MessageFormat.format(pattern, 2,5);
        String result4 = MessageFormat.format(pattern, -1);
        String result5 = MessageFormat.format(pattern, 3,10);
        assertEquals("no houses",result);
        assertEquals("one house",result2);
        assertEquals("5 houses",result3);
        assertEquals("no houses",result4);
        assertEquals("10 houses",result5);
        
    }
}
