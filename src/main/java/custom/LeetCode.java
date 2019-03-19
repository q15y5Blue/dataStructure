package custom;

import custom.util.ListNode;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class LeetCode {

    public static void main(String[] args) {
//        int numbs[] = {2,7,11,15};
        LeetCode leetCode = new LeetCode();
        leetCode.lengthOfLongestSubstring("abcabcbb");
    }

    //twoSum
    public int[] twoSum(int[] nums, int target) {
        for (int i =0;i<=nums.length-2;i++){
            for (int j=i+1;j<=nums.length-1;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("ddd");
    }

    //
    public int reverse(int x){
        boolean flag = false;
        if (x<0){
            flag=true;
            x = -x;
        }
        int result = 0;
        while(x>0){
            int lastNumber = x - x/10*10;
            if (result*10+lastNumber > (Integer.MAX_VALUE))
                return 0;
            result = result*10+lastNumber;
            x/=10;
        }
        return flag?-(int)result:(int)result;
    }


//    输入：(1 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode node=null;
        if(l1!=null||l2!=null){
            if(l1==null)l1=new ListNode(0);
            if(l2==null)l2=new ListNode(0);
            if (l1.val+l2.val>=10){
                node = new ListNode(l1.val+l2.val-10);
                if (l1.next!=null)
                    l1.next.val++;
                else if (l2.next!=null)
                    l2.next.val++;
                else if(l1.next==null&&l2.next==null)
                    l1.next = new ListNode(1);
                node.next = addTwoNumbers(l1.next,l2.next);
            }else{
                node = new ListNode(l1.val+l2.val);
                node.next = addTwoNumbers(l1.next,l2.next);
            }
//            System.out.println(node);
        }
        return node;
    }

    /**
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * @param s
     * @return
     */
    //无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        for(char ss:s.toCharArray()){
            System.out.println(ss);
        }
        return 0;
    }

}
