package custom.util;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

    public static TreeNode getATree(){
        TreeNode node =new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(4);
//        node.right.left.left = new TreeNode(9);
//        node.right.left.right = new TreeNode(10);
        node.right.right = new TreeNode(7);
        return node;
    }
}