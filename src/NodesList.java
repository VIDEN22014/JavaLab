import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodesList {
    public static int getElementsCount(org.w3c.dom.NodeList nodeList) {
        int counter = 0;
        for (int i = 0, l = nodeList.getLength(); i < l; i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() != Node.TEXT_NODE) {
                counter++;
            }
        }
        return counter;
    }
    public static void clearTextNodes(org.w3c.dom.NodeList nodeList) {
        for (int i = 0, l = nodeList.getLength(); i < l; i++) {
            Node nNode = nodeList.item(i);
            NodeList childNodes = nNode.getChildNodes();
            int counter=0;
            for (int j = 0, x = childNodes.getLength(); j < x-counter;j++) {
                if (childNodes.item(j).getNodeType() == Node.TEXT_NODE) {
                    nNode.removeChild(childNodes.item(j));
                    counter++;
                }
            }

        }
    }
}
