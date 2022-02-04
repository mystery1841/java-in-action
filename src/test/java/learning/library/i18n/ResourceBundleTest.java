package learning.library.i18n;

import learning.security.CryptoClassLoader;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceBundleTest {

    @Test
    public void testLocatingResourceBundle() {
        ResourceBundle bundle = ResourceBundle.getBundle("MyProgramStrings");
        String computeButtonLabel = bundle.getString("computeButton");
        assertEquals("Rechnen", computeButtonLabel);
    }

    @Test
    public void testGetObjectResourceBundle() {
        ResourceBundle bundle = ResourceBundle.getBundle("learning.library.i18n.ProgramResources", Locale.GERMAN);
        double[] paperSize = (double[]) bundle.getObject("defaultPaperSize");
        assertArrayEquals(new double[]{210, 297}, paperSize);
    }

    @Test
    public void testGetResource() {
        CryptoClassLoader c = new CryptoClassLoader(1);
        ClassLoader l = c.getParent();
        System.exit(1);
    }
}
