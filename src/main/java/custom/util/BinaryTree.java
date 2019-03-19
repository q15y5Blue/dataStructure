package custom.util;


public class BinaryTree<T>{
    public T rootNode;

    /**
     * 构造空二叉树T
     * @return
     */
    public BinaryTree() {

    }

    public BinaryTree(T headNode){
        this.rootNode = headNode;
    }

    /**
     * 销毁二叉树
     * @param rootNode
     */
    public void destoryBinaryTree(T rootNode){
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

}
