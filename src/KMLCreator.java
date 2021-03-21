import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class KMLCreator {
    public static void kmlCreate(Node node) {
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

            NodeList nodesList = node.getChildNodes();
            for (int i = 0; i < nodesList.getLength(); i++) {
                Element placemarkElement = doc.createElement("Placemark");
                folderElement.appendChild(placemarkElement);

                String lon = nodesList.item(i).getAttributes().getNamedItem("lon").getNodeValue();
                String lat = nodesList.item(i).getAttributes().getNamedItem("lat").getNodeValue();
                String ele = MyNode.getChildNodeByName(nodesList.item(i),"ele").getChildNodes().item(0).getNodeValue();
                String cmt = MyNode.getChildNodeByName(nodesList.item(i),"cmt").getChildNodes().item(0).getNodeValue();
                String link = MyNode.getChildNodeByName(nodesList.item(i),"link").getAttributes().getNamedItem("href").getNodeValue();
                String sym = MyNode.getChildNodeByName(nodesList.item(i),"sym").getChildNodes().item(0).getNodeValue();

                nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(cmt));
                placemarkElement.appendChild(nameElement);

                Element descriptionElement = doc.createElement("description");
                descriptionElement.appendChild(doc.createTextNode(link));
                placemarkElement.appendChild(descriptionElement);

                Element styleUrlElement = doc.createElement("styleUrl");
                styleUrlElement.appendChild(doc.createTextNode(StyleConverter.convertStyles(sym)));
                placemarkElement.appendChild(styleUrlElement);

                Element pointElement = doc.createElement("Point");
                placemarkElement.appendChild(pointElement);

                Element coordinatesElement = doc.createElement("coordinates");
                pointElement.appendChild(coordinatesElement);
                coordinatesElement.appendChild(doc.createTextNode(lon+","+lat+","+ele));

            }


//Transformating
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("output.kml"));

            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
