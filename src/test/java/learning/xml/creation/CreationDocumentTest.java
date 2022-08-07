package learning.xml.creation;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.XMLReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CreationDocumentTest {

    @Test
    public void testCreateDocument() throws ParserConfigurationException, TransformerException, FileNotFoundException {
        File file = new File("C:\\Text\\test.xml");
        String rootName = "xml";
        String childName = "name";
        String textContents = "maki";
        String name = "hello";
        String value = "world";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element rootElement = doc.createElement(rootName);
        Element childElement = doc.createElement(childName);
        Text textNode = doc.createTextNode(textContents);
        doc.appendChild(rootElement);
        rootElement.appendChild(childElement);
        childElement.appendChild(textNode);
        rootElement.setAttribute(name, value);
        Transformer t = TransformerFactory.newInstance().newTransformer();
        String systemIdentifier = "";
        String publicIdentifier = "";
        t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemIdentifier);
        t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, publicIdentifier);
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(file)));
    }
}
