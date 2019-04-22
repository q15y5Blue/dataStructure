package custom.data;

import custom.util.BinaryTree;
import custom.util.Node;

public class MyDataStructure {
    public static void main(String[] args) {
        Node headNode = new Node("-");
        headNode.leftNode = new Node("+");
        headNode.leftNode.leftNode = new Node("a");
        headNode.leftNode.rightNode=new Node("*");
        headNode.leftNode.rightNode.leftNode=new Node("b");
        headNode.leftNode.rightNode.rightNode=new Node("-");
        headNode.leftNode.rightNode.rightNode.leftNode=new Node("c");
        headNode.leftNode.rightNode.rightNode.rightNode=new Node("d");
        headNode.rightNode=new Node("/");
        headNode.rightNode.leftNode=new Node("e");
        headNode.rightNode.rightNode=new Node("f");
        BinaryTree tree = new BinaryTree(headNode);
        tree.preOrderIter(headNode);
        System.out.println();
        tree.inOrderIter(headNode);
        System.out.println();
        tree.postOrderIter(headNode);
        System.out.println();
        tree.preOrderTraversal();
        System.out.println();
        tree.inOrderTraversal();
        System.out.println();
        tree.afterOrderTraversal();
    }


    public static void printArr(int []arr){
        for(int a:arr){
            System.out.print(a +" ");
        }
        System.out.println();
    }

    public static void printArr(Integer []arr){
        for(Integer a:arr){
            System.out.print(a +" ");
        }
        System.out.println();
    }

}
