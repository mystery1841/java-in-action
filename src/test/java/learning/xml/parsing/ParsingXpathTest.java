package learning.xml.parsing;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingXpathTest {

    @Test
    public void testXpathGetText() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        Document doc = builder.parse(in);
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();
        String username = path.evaluate("/config/entry/font/name/text()", doc);
        assertEquals("Helvetica", username);
    }

    @Test
    public void testXpathGetElement() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        Document doc = builder.parse(in);
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();
        NodeList nodes = (NodeList) path.evaluate("/config/entry/font", doc, XPathConstants.NODESET);
        assertEquals(1, nodes.getLength());
        Node fontNode = nodes.item(0);
        assertEquals("font", fontNode.getNodeName());
        NodeList children = fontNode.getChildNodes();
        assertEquals(5, children.getLength());
    }

    @Test
    public void testXpathEvaluateExpression() throws ParserConfigurationException, IOException, SAXException, XPathException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        Document doc = builder.parse(in);
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();
        XPathNodes result = path.evaluateExpression("/config/entry/font", doc, XPathNodes.class);
        assertEquals(1, result.size());
        Node fontNode = result.get(0);
        assertEquals("font", fontNode.getNodeName());
        NodeList children = fontNode.getChildNodes();
        assertEquals(5, children.getLength());
    }

    @Test
    public void testXpathEvaluateExpressionGetNode() throws ParserConfigurationException, IOException, SAXException, XPathException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        Document doc = builder.parse(in);
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();
        Node node = path.evaluateExpression("/config/entry/font", doc, Node.class);
        assertEquals("font", node.getNodeName());
        NodeList children = node.getChildNodes();
        assertEquals(5, children.getLength());
    }

    @Test
    public void testXpathEvaluateExpressionGetCount() throws ParserConfigurationException, IOException, SAXException, XPathException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream("xml/basic.xml");
        Document doc = builder.parse(in);
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();
        int count = path.evaluateExpression("count(/config/entry/font)", doc, Integer.class);
        assertEquals(1, count);
    }
}
