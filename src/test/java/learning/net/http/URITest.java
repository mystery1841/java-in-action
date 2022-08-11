package learning.net.http;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class URITest {

    @Test
    public void testCreateURI() throws MalformedURLException {
        URI uri = URI.create("file:C:/logs/certificate/log.txt");
        URL url = uri.toURL();
    }
}
