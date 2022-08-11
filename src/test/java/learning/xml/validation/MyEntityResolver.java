package learning.xml.validation;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

public class MyEntityResolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicID, String systemID) throws IOException {
        if (publicID.equals("my")) {
            URL url = getClass().getClassLoader().getResource("xml/my.dtd");
            if (url != null) {
                return new InputSource(url.openStream());
            }
        }
        return null;
    }
}
