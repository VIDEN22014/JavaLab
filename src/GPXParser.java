import org.w3c.dom.Document;
import org.w3c.dom.Node;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class GPXParser {
    public static Node parseGPX(String path) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Node node = doc.getDocumentElement();
            MyNode.clearChildrenNodes(node);

            return node;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
