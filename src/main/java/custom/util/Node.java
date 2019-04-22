package custom.util;

import java.util.Objects;

public class Node {
    public String nodeName;
    public Integer nodeValue;
    public Node leftNode;
    public Node rightNode;
    public Node parentNode;

    public Node(String nodeName, Integer nodeValue, Node leftNode, Node rightNode, Node parentNode) {
        this.nodeName = nodeName;
        this.nodeValue = nodeValue;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.parentNode = parentNode;
    }

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(nodeName, node.nodeName) &&
                Objects.equals(nodeValue, node.nodeValue) &&
                Objects.equals(leftNode, node.leftNode) &&
                Objects.equals(rightNode, node.rightNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName, nodeValue, leftNode, rightNode);
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeName='" + nodeName + '\'' +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}