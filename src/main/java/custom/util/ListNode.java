package custom.util;

/**
 *   ListNode l1=new ListNode(5);
 *         l1.next=new ListNode(8);
 *         l1.next.next = new ListNode(3);
 *         ListNode l2 = new ListNode(5);
 *         l2.next=new ListNode(6);
 *         l2.next.next=new ListNode(4);
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x){this.val =x;}

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

}
