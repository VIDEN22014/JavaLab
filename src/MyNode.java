import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MyNode {
    public static void clearChildrenNodes(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.TEXT_NODE) {
                node.removeChild(nNode);
            }
            NodesList.clearTextNodes(node.getChildNodes());
        }
    }

    public static Node getChildNodeByName(Node node, String name) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeName().equals(name)) {
                return nNode;
            }
        }
        return null;
    }
}
