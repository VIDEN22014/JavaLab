import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodesList {
    public static void clearTextNodes(org.w3c.dom.NodeList nodeList) {
        for (int i = 0, l = nodeList.getLength(); i < l; i++) {
            Node nNode = nodeList.item(i);
            NodeList childNodes = nNode.getChildNodes();
            for (int j = 0; j < childNodes.getLength();j++) {
                if (childNodes.item(j).getNodeType() == Node.TEXT_NODE) {
                    nNode.removeChild(childNodes.item(j));
                }
            }
        }
    }
}
