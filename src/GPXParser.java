import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class GPXParser {
    public static NodeList parseGPX(String path) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("wpt");

            System.out.println(NodesList.getElementsCount(nList));
            System.out.println( nList.getLength());
            NodesList.clearTextNodes(nList);
            for (int i = 0, l = nList.getLength(); i < l; i++) {
                Node nNode = nList.item(i);
                    if (nNode.hasAttributes()) {
                        System.out.println(nNode.getChildNodes().item(6).getNodeName());
                        System.out.println(nNode.getNodeName());
                        System.out.println(nNode.getAttributes().getNamedItem("lat"));
                        System.out.println(nNode.getAttributes().getNamedItem("lon"));
                    }
            }
            return nList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
