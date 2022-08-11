package learning.xml.validation;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentTypeDefinitionTest {

    @Test
    public void parsePublicIdentifier() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(new MyEntityResolver());
        builder.setErrorHandler(new MyErrorHandler());
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/unverified.xml");
        try {
            Document doc = builder.parse(in);
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.println("验证失败");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNodeList() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(new MyEntityResolver());
        builder.setErrorHandler(new MyErrorHandler());
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/unverified.xml");
        Document doc = builder.parse(in);
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        assertEquals(1, children.getLength());
    }
}
