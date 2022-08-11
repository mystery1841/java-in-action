package learning.xml.creation;

import org.junit.jupiter.api.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Test
    public void testLSSerializer() throws ParserConfigurationException, IOException {
        Path path = Paths.get("C:\\Text\\test2.xml");
        String rootName = "xml";
        String childName = "name";
        String textContents = "maki";
        String name = "hello";
        String value = "world";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        String namespace = "http://www.w3.org/2000/svg";
        Element rootElement = doc.createElementNS(namespace,rootName);


        rootElement.setAttributeNS(namespace,"luck","123");
        Element childElement = doc.createElement(childName);
        Text textNode = doc.createTextNode(textContents);
        doc.appendChild(rootElement);
        rootElement.appendChild(childElement);
        childElement.appendChild(textNode);
        rootElement.setAttribute(name, value);
        DOMImplementation impl = doc.getImplementation();
        var implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
        LSSerializer ser = implLS.createLSSerializer();
        LSOutput out = implLS.createLSOutput();
        out.setEncoding("UTF-8");
        out.setByteStream(Files.newOutputStream(path));
        ser.write(doc, out);
    }

    public void testNamespaceAware() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        String namespace = "http://www.w3.org/2000/svg";
        Element rootElement = doc.createElementNS(namespace, "svg");
        Element svgElement = doc.createElementNS(namespace, "svg:svg");
    }
}
