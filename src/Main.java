import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class Main {
    public static void main(String argv[]) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
// root element
            Element rootElement = doc.createElement("order");
            doc.appendChild(rootElement);
// setting attribute to element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            rootElement.setAttributeNode(attr);

            Comment comment = doc.createComment("Важливе замовлення для Івасика-Телесика");

            rootElement.appendChild(comment);
            Element elementPerson = doc.createElement("person");
            rootElement.appendChild(elementPerson);

            elementPerson.appendChild(doc.createTextNode("Івасик-Телесик"));
            Element elementItem = doc.createElement("item");
            rootElement.appendChild(elementItem);
            attr = doc.createAttribute("id");
            attr.setValue("1");
            elementItem.setAttributeNode(attr);
            attr = doc.createAttribute("status");
            attr.setValue("delivered");
            elementItem.setAttributeNode(attr);
            Element elementTitle = doc.createElement("title");
            elementItem.appendChild(elementTitle);
            elementTitle.appendChild(doc.createTextNode("Гуси-лебеді"));
            Element elementQuantity = doc.createElement("quantity");
            elementItem.appendChild(elementQuantity);
            elementQuantity.appendChild(doc.createTextNode("5"));
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