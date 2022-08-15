package learning.xml.parsing;

import org.junit.jupiter.api.Test;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class ParsingStreamTest {

    @Test
    public void testSAXParser() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) {
                System.out.println(qname);
            }

            public void characters(char[] data, int start, int length) {
                char[] datas = new char[length];

                System.arraycopy(data, start, datas, 0, length);
                System.out.println(datas);
            }
        };
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        parser.parse(in, handler);
    }
}
