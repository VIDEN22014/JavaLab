package Zavd;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class HtmlTableCreator {
    public static void createHtmlTable(List<String> stringList, String outFileName){
        //Побудова Html таблиці
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = dBuilder.newDocument();
        Attr attr;
        // root element
        Element rootElement = doc.createElement("html");
        attr = doc.createAttribute("lang");
        attr.setValue("uk");
        rootElement.setAttributeNode(attr);
        doc.appendChild(rootElement);

        Element headElement = doc.createElement("head");
        rootElement.appendChild(headElement);
        Element titleElement = doc.createElement("title");
        titleElement.appendChild(doc.createTextNode("Назви"));
        headElement.appendChild(titleElement);


        Element bodyElement = doc.createElement("body");
        rootElement.appendChild(bodyElement);

        Element tableElement = doc.createElement("table");
        attr = doc.createAttribute("border");
        attr.setValue("1");
        tableElement.setAttributeNode(attr);
        bodyElement.appendChild(tableElement);

        int counter = 1;
        for (String i : stringList) {
            Element trElement = doc.createElement("tr");
            tableElement.appendChild(trElement);
            Element tdElement = doc.createElement("td");
            tdElement.appendChild(doc.createTextNode(String.valueOf(counter)));
            trElement.appendChild(tdElement);
            Element td1Element = doc.createElement("td");
            td1Element.appendChild(doc.createTextNode(i));
            trElement.appendChild(td1Element);
            counter++;
        }

//Transformating
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult resultFile = new StreamResult(new File(outFileName));
        try {
            transformer.transform(source, resultFile);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
