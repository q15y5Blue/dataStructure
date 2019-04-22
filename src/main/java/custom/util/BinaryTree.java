package custom.util;
import custom.util.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class BinaryTree{
    public Node rootNode;

    /**
     * 构造空二叉树T
     * @return
     */
    public BinaryTree() {

    }

    public BinaryTree(Node headNode){
        this.rootNode = headNode;
    }

    /**
     * 销毁二叉树
     * @param rootNode
     */
    public void destoryBinaryTree(Node rootNode){
        if(this.rootNode!=null){
            rootNode = null;
        }
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "rootNode=" + rootNode +
                '}';
    }

    /**
     * 先序遍历
     * 递归
     */
    public void preOrderIter(Node node){
        if(node==null)
            return;
        System.out.print(node.nodeName);
        if(node.leftNode!=null)
            this.preOrderIter(node.leftNode);
        if(node.rightNode!=null)
            this.preOrderIter(node.rightNode);
    }

    /**
     *中序遍历
     * 递归
     */
    public void inOrderIter(Node node){
        if(node==null)
            return;
        if(node.leftNode!=null)
            this.inOrderIter(node.leftNode);
        System.out.print(node.nodeName);
        if(node.rightNode!=null)
            this.inOrderIter(node.rightNode);
    }

    /**
     * 后序遍历
     * 递归
     */
    public void postOrderIter(Node node){
        if(node==null)
            return;
        if(node.leftNode!=null)
            this.postOrderIter(node.leftNode);
        if(node.rightNode!=null)
            this.postOrderIter(node.rightNode);
        System.out.print(node.nodeName);
    }

    /**
     * 先序遍历非递归
     */
    public void preOrderTraversal(){
        Stack<Node> nodeStack = new Stack<Node>();
        nodeStack.push(this.rootNode);
        while (!nodeStack.empty()){
            Node thisNode = nodeStack.pop();
            System.out.print(thisNode.nodeName);
            if(thisNode.rightNode!=null){
                nodeStack.push(thisNode.rightNode);
            }
            if(thisNode.leftNode!=null){
                nodeStack.push(thisNode.leftNode);
            }
        }
    }

    /**
     * 遍历
     */
    public void inOrderTraversal(){
        Stack<Node> nodeStack = new Stack<Node>();
        Node node = this.rootNode;
        nodeStack.push(new Node(""));//触发条件
        while (!nodeStack.empty()){
            while (node!=null){
                nodeStack.push(node);
                node = node.leftNode;
            }
            Node newNode = nodeStack.pop();
            System.out.print(newNode.nodeName);
            if(newNode.rightNode!=null){
                node=newNode.rightNode;
            }

        }
    }
    //左右跟
    public void afterOrderTraversal(){

    }


}
