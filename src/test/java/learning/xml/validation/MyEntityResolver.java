package learning.xml.validation;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.URL;

public class MyEntityResolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicID, String systemID) throws IOException {
        if (publicID != null && publicID.equals("my")) {
            URL url = getClass().getClassLoader().getResource("xml/my.dtd");
            if (url != null) {
                return new InputSource(url.openStream());
            }
        }
        if (systemID != null) {
            if (systemID.endsWith("my.xsd")){
                URL url = getClass().getClassLoader().getResource("xml/my.xsd");
                if (url != null) {
                    return new InputSource(url.openStream());
                }
            }
        }
        return null;
    }
}
