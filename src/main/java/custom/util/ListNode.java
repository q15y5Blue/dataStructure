package custom.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

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
    public static RandomListNode getTestRandomListDemo(){
        RandomListNode head = new RandomListNode(1);
        head.next=new RandomListNode(2);
        head.next.next=new RandomListNode(3);
        head.next.next.next=new RandomListNode(4);
        head.random= head.next.next;
        head.next.next.next.next=new RandomListNode(5);
        return head;
    }

}
