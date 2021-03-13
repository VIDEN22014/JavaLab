import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class KMLCreator {
    public static void kmlCreate() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Attr attr;
// root element
    Element rootElement = doc.createElement("kml");
    doc.appendChild(rootElement);

        Element documentElement = doc.createElement("Document");
        rootElement.appendChild(documentElement);
            //Styles
            Element styleElementGreen = doc.createElement("Style");
            attr = doc.createAttribute("id");
            attr.setValue("green");
            styleElementGreen.setAttributeNode(attr);
            documentElement.appendChild(styleElementGreen);

                Element iconstyleElement = doc.createElement("IconStyle");
                styleElementGreen.appendChild(iconstyleElement);

                    Element iconElement = doc.createElement("Icon");
                    iconstyleElement.appendChild(iconElement);

                        Element hrefElement = doc.createElement("href");
                        iconElement.appendChild(hrefElement);
                        hrefElement.appendChild(doc.createTextNode("http://maps.google.com/mapfiles/kml/pushpin/grn-pushpin.png"));

                    Element hotSpotElement = doc.createElement("hotSpot");
                    attr = doc.createAttribute("x");
                    attr.setValue("20");
                    hotSpotElement.setAttributeNode(attr);
                    attr = doc.createAttribute("y");
                    attr.setValue("2");
                    hotSpotElement.setAttributeNode(attr);
                    attr = doc.createAttribute("xunits");
                    attr.setValue("pixels");
                    hotSpotElement.setAttributeNode(attr);
                    attr = doc.createAttribute("yunits");
                    attr.setValue("pixels");
                    hotSpotElement.setAttributeNode(attr);
                    iconElement.appendChild(hotSpotElement);
            //
            Element styleElementBlue = doc.createElement("Style");
            attr = doc.createAttribute("id");
            attr.setValue("blue");
            styleElementBlue.setAttributeNode(attr);
            documentElement.appendChild(styleElementBlue);

                iconstyleElement = doc.createElement("IconStyle");
                styleElementBlue.appendChild(iconstyleElement);

                    iconElement = doc.createElement("Icon");
                    iconstyleElement.appendChild(iconElement);

                        hrefElement = doc.createElement("href");
                        iconElement.appendChild(hrefElement);
                        hrefElement.appendChild(doc.createTextNode("http://maps.google.com/mapfiles/kml/pushpin/ltblu-pushpin.png"));

                    hotSpotElement = doc.createElement("hotSpot");
                    attr = doc.createAttribute("x");
                    attr.setValue("20");
                    hotSpotElement.setAttributeNode(attr);
                    attr = doc.createAttribute("y");
                    attr.setValue("2");
                    hotSpotElement.setAttributeNode(attr);
                    attr = doc.createAttribute("xunits");
                    attr.setValue("pixels");
                    hotSpotElement.setAttributeNode(attr);
                    attr = doc.createAttribute("yunits");
                    attr.setValue("pixels");
                    hotSpotElement.setAttributeNode(attr);
                    iconElement.appendChild(hotSpotElement);

            //Folder
            Element folderElement = doc.createElement("Folder");
            documentElement.appendChild(folderElement);

                Element nameElement = doc.createElement("name");
                folderElement.appendChild(nameElement);
                nameElement.appendChild(doc.createTextNode("Українські Карпати - Блуд"));

                Element folderElement0 = doc.createElement("Folder");
                folderElement.appendChild(folderElement0);

                    nameElement = doc.createElement("name");
                    folderElement0.appendChild(nameElement);
                    nameElement.appendChild(doc.createTextNode("L-35-003"));

//Transformating
TransformerFactory transformerFactory = TransformerFactory.newInstance();
Transformer transformer = transformerFactory.newTransformer();
transformer.setOutputProperty(OutputKeys.INDENT, "yes");
transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
DOMSource source = new DOMSource(doc);
StreamResult result = new StreamResult(new File("output.xml"));
transformer.transform(source, result);
StreamResult consoleResult = new StreamResult(System.out);
transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
