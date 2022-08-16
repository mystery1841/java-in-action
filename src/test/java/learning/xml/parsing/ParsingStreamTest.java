package learning.xml.parsing;

import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParsingStreamTest {

    @Test
    public void testSAXParser() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        List<String> list = new ArrayList<>();
        DefaultHandler handler = new DefaultHandler() {
            public void characters(char[] data, int start, int length) {
                char[] array = new char[length];
                System.arraycopy(data, start, array, 0, length);
                String string = new String(array);
                if (!string.isBlank()) {
                    list.add(string);
                }
            }
        };
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        parser.parse(in, handler);
        String[] strings = list.toArray(new String[]{});
        assertArrayEquals(new String[]{"Helvetica", "36"}, strings);
    }

    @Test
    public void testSAXParserValidate() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser parser = factory.newSAXParser();
        List<String> list = new ArrayList<>();
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void characters(char[] data, int start, int length) {
                char[] array = new char[length];
                System.arraycopy(data, start, array, 0, length);
                String string = new String(array);
                if (!string.isBlank()) {
                    list.add(string);
                }
            }
            @Override
            public InputSource resolveEntity(String publicID, String systemID) throws IOException {
                if (publicID != null && publicID.equals("my")) {
                    URL url = getClass().getClassLoader().getResource("xml/my.dtd");
                    if (url != null) {
                        return new InputSource(url.openStream());
                    }
                }
                return null;
            }
        };
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/unverified.xml");
        parser.parse(in, handler);
        String[] strings = list.toArray(new String[]{});
        assertArrayEquals(new String[]{"Helvetica", "36"}, strings);
    }

    @Test
    public void testStAXParser() throws XMLStreamException {
        List<String> list = new ArrayList<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
        XMLStreamReader parser = factory.createXMLStreamReader(in);
        while (parser.hasNext()) {
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                list.add(parser.getLocalName());
            }
        }
        String[] strings = list.toArray(new String[]{});
        assertArrayEquals(new String[]{"config", "entry", "font", "name", "size"}, strings);
    }

    @Test
    public void testStAXParserValidate() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/unverified.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            factory.setProperty(XMLInputFactory.IS_VALIDATING, true);
        });
    }
}
