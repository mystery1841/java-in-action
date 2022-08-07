package learning.xml.parsing;

import org.junit.jupiter.api.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParsingDocumentTest {

    @Test
    public void testReadDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        Document doc = builder.parse(in);
        Element root = doc.getDocumentElement();
        String tagName = root.getTagName();
        assertEquals("config", tagName);
    }

    @Test
    public void testNodeList() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/font.xml");
        Document doc = builder.parse(in);
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        assertEquals(5, children.getLength());
    }

    @Test
    public void testNodeListIgnoreWhitespace() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/font.xml");
        Document doc = builder.parse(in);
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        int num = 0;
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                Element childElement = (Element) child;
                num++;
            }
        }
        assertEquals(2, num);
    }

    @Test
    public void testElementGetData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/font.xml");
        Document doc = builder.parse(in);
        Element element = doc.getDocumentElement();
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++)
        {
            Node attribute = attributes.item(i);
            String name = attribute.getNodeName();
            String value = attribute.getNodeValue();

        }
    }
}
