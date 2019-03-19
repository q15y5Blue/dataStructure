package custom.data;

public class Node {
    public String nodeName;
    public Integer nodeValue;
    public Node leftNode;
    public Node rightNode;
    public Node parentNode;

    @Override
    public String toString() {
        return "Node{" +
                "nodeName='" + nodeName + '\'' +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}