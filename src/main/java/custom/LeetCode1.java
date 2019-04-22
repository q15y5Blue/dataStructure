package custom;

import custom.util.ListNode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeetCode1 {


    //twoSum
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i <= nums.length - 2; i++) {
            for (int j = i + 1; j <= nums.length - 1; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("ddd");
    }

    //
    public int reverse(int x) {
        boolean flag = false;
        if (x < 0) {
            flag = true;
            x = -x;
        }
        int result = 0;
        while (x > 0) {
            int lastNumber = x - x / 10 * 10;
            if (result * 10 + lastNumber > (Integer.MAX_VALUE))
                return 0;
            result = result * 10 + lastNumber;
            x /= 10;
        }
        return flag ? -result : result;
    }


    //    输入：(1 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = null;
        if (l1 != null || l2 != null) {
            if (l1 == null) l1 = new ListNode(0);
            if (l2 == null) l2 = new ListNode(0);
            if (l1.val + l2.val >= 10) {
                node = new ListNode(l1.val + l2.val - 10);
                if (l1.next != null)
                    l1.next.val++;
                else if (l2.next != null)
                    l2.next.val++;
                else if (l1.next == null && l2.next == null)
                    l1.next = new ListNode(1);
                node.next = addTwoNumbers(l1.next, l2.next);
            } else {
                node = new ListNode(l1.val + l2.val);
                node.next = addTwoNumbers(l1.next, l2.next);
            }
//            System.out.println(node);
        }
        return node;
    }

    /**
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * @param s
     * @return
     */
    //
    public int lengthOfLongestSubstring3(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("i:" + i);
            for (int j = i; j < s.length(); j++) {
                if (!checkStrRepeat(s, i, j)) {
                    max = Math.max(max, j - i + 1);
                } else break;
            }
        }
        return max;
    }

    /**
     * 判断一个字符串中是否有重复字符
     *
     * @return
     */
    private boolean checkStrRepeat(String s, int i, int j) {
        Set<Character> charSet = new HashSet<Character>();
        for (int index = i; index <= j; index++) {
            if (!charSet.contains(s.charAt(index))) {
                charSet.add(s.charAt(index));
            } else
                return true;

        }
        return false;
    }

    /**
     * 滑动窗口解决
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int i = 0;
        int j = 0;
        int max = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }

    /**
     * 找出最长回文子串
     * ERROR
     * 时间复杂度过高,超时
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1)
            return s;
        StringBuilder subStr = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            subStr.append(s.charAt(i));
        }
        if (s.length() > 1 && s.length()<4){
            return this.getReviewStr(s);
        } else {
            String str = this.getCommenStr(s, subStr.toString());
            if(this.checkReviewStr(str))
                return str;
            else return this.getReviewStr(str);
        }
    }

    /**
     * 暴力法
     * @param s
     * @return
     */
    public String getReviewStr(String s){
        String rsStr="";
        for(int i =0;i<s.length();i++){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.charAt(i));
            for(int j=i+1;j<s.length();j++){
                stringBuilder.append(s.charAt(j));
                if (this.checkReviewStr(stringBuilder.toString())&&stringBuilder.toString().length()>rsStr.length()){
                    rsStr = stringBuilder.toString();
                }else if(rsStr.length()==0){
                    rsStr = String.valueOf(s.charAt(0));
                }

            }
        }
        return rsStr;
    }
    /**
     * 验证回文
     * @param string
     * @return
     */
    public boolean checkReviewStr(String string){
        if(string.length()==0)
            return true;
        int i=0;
        int j=string.length()-1;
        while (i!=j){
            if(i+1==j){
                if(string.charAt(i)==string.charAt(j))
                    return true;
            }
            if (string.charAt(i++)==string.charAt(j--)&&i==j){
                return true;
            }
            else
                return false;
        }
        return false;
    }


    /**
     * 两个字符串最长公共子串
     * acbcbcef
     * abcbced
     * return bcbce
     */
    public String getCommenStr(String str1,String str2){
        int chars[][]= new int[str1.length()][str2.length()];
        int maxLen=0;
        int index =0;
        for(int i=0;i<str1.length();i++){
            for(int j=0;j<str2.length();j++){
                if(str1.toCharArray()[i]==str2.toCharArray()[j]){
                    if(i>0&&j>0&&chars[i-1][j-1]!=0){
                        chars[i][j]=chars[i-1][j-1]+1;
                        if(chars[i][j]>maxLen){
                            maxLen = chars[i][j];
                            index=i;
                        }
                    }else {
                        chars[i][j]=1;
                    }
                }
            }
        }
        return str1.substring(index-maxLen+1,index+1);
    }

    /**
     * Z字形变换
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(s.length()<3||numRows==1){
            return s;
        }
        char [][] chars = new char[numRows][s.length()];
        int y=-1,x=1;
        for(int index=0;index<s.toCharArray().length;index++){
            if(index%(numRows*2-2)<numRows){
                if(index%(numRows*2-2)==0){
                    y++;
                }
            }else {
                y++;
            }
            if(y%(numRows-1)==0&&x!=numRows-1&&(index%(2*numRows-2)!=0)){
                x++;
            }
            else {
                x--;
            }
//            System.out.println("x: "+x+"  y:"+y);
            chars[x][y]=s.charAt(index);
        }
        StringBuilder sb = new StringBuilder();
        for(int p=0;p<numRows;p++){
            for(int j=0;j<chars[p].length;j++){
                if(chars[p][j]!='\u0000')
                sb.append(chars[p][j]);
            }
        }
        return sb.toString();
    }

    public boolean isPalindrome(int x) {
        int temp= x;
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reVertNumber = 0;
        while (x!=0){
            reVertNumber = reVertNumber*10 + x%10;
            x/=10;
        }
        return reVertNumber==temp;
    }

    /**
     *
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int max =0;
        for(int i=0;i<height.length;i++){
            for(int j=i;j<height.length;j++){
                max = Math.max(max,(j-i)*Math.min(height[j],height[i]));
            }
        }
        return max;
    }

    public int maxArea(int [] height){
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    /**
     * 转换为罗马数字
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        Map<Integer,String> map = new TreeMap<Integer,String>();
        map.put(1,"I");
        map.put(4,"IV");//4
        map.put(5,"V");
        map.put(9,"IX");//9
        map.put(10,"X");
        map.put(40,"XL");//40
        map.put(50,"L");
        map.put(90,"XC");
        map.put(100,"C");
        map.put(400,"CD");
        map.put(500,"D");
        map.put(900,"CM");
        map.put(1000,"M");
        if(map.keySet().contains(num))
            return map.get(num);
        ArrayList<Integer> list = new ArrayList<Integer>();
        map.keySet().forEach(item-> {
            list.add(item);
        });

        int [] tempJ = new int[list.size()];
        int tempNum = num;
        for(int i=list.size()-1;i>=0;i--){
            int j=0;
            while (tempNum>=list.get(i)){
                tempNum=tempNum-list.get(i);
                tempJ[i]=++j;
            }

        }
        //list是正的
        StringBuilder sb =new StringBuilder();
//        System.out.println();
        for(int i=list.size()-1;i>=0;i--){
            while (tempJ[i]!=0){
                tempJ[i]--;
                sb.append(map.get(list.get(i)));
            }
        }


        return sb.toString();
    }

    /**
     * 罗马数To整数
     * @param s
     * @return
     */
    public int romanToInt(String s) {
         String str[] = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int values []=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int countValue = 0;
        for(int i=0;i<str.length;i++){
            while (s.startsWith(str[i])){
                System.out.println(s);
                s=s.substring(str[i].length());
                countValue +=values[i];
            }
        }
        return countValue;
    }

    /**
     * 最长公共前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0){
            return new String();
        }
        String str="";
        for(int i =1;i<strs.length;i++){
            int index=0;
            for(int j =0;j<strs[i-1].length()&&j<strs[i].length();j++){
                if(strs[i-1].charAt(j)!=strs[i].charAt(j)){
                    break;
                }
                else
                    index++;
            }
            if(index==0){
                return "";
            }
            strs[i]=strs[i-1].substring(0,index);
        }
       return strs[strs.length-1];
    }

    /**
     * 超时
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum0(int[] nums) {
        if(nums.length<3)
            return new ArrayList<>();
        List<List<Integer>> rsList = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j =i+1;j<nums.length;j++){
                for(int k =j+1;k<nums.length;k++){
                    if (nums[i]+nums[j]+nums[k]==0){
                        ArrayList<Integer> childList = new ArrayList<>();
                        childList.add(nums[i]);
                        childList.add(nums[j]);
                        childList.add(nums[k]);
                        Collections.sort(childList);
                        if(!rsList.contains(childList))
                            rsList.add(childList);
                    }
                }
            }
        }
        return rsList;
    }

    /**
     * 又超时
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        if(nums.length<3)
            return new ArrayList<>();
        int arr1 [][]=  new int[nums.length][nums.length];
        int []newArr=new int[nums.length*nums.length];
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr1[i].length;j++){
                if(i!=j){
                    arr1[i][j]=nums[i]+nums[j];//去除对角线的计算
                    newArr[i*nums.length+j]=arr1[i][j];
                }
            }
        }
        List<List<Integer>> rsList = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<newArr.length;j++){
                if(nums[i]+newArr[j]==0){
                    int a,b=0;
                    a = j/nums.length;
                    b = j%nums.length;
                    if(a!=b&&a!=i&&b!=i){
                        ArrayList<Integer> childList = new ArrayList<>();
                        childList.add(nums[i]);
                        childList.add(nums[a]);
                        childList.add(nums[b]);
                        Collections.sort(childList);
                        if(!rsList.contains(childList))
                            rsList.add(childList);
                    }
                }
            }
        }
        return rsList;
    }

    public int threeSumClosest(int[] nums, int target) {
        int max =Integer.MAX_VALUE;
        int rs=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    int min= 0;
                    if(nums[i]+nums[k]+nums[j]>=target){
                        min = nums[i]+nums[k]+nums[j]-target;
                    }
                    else if(nums[i]+nums[k]+nums[j]<target){
                        min = -(nums[i]+nums[k]+nums[j]-target);
                    }
                    if (max>min){
                        max =min;
                        rs = nums[i]+nums[k]+nums[j];
                    }
                }
            }
        }
        return rs;
    }

    /**
     * 电话号码的字母组合
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> rsList = new ArrayList<>();
        String num2[] = new String[]{"a","b","c"};
        String num3[] = new String[]{"d","e","f"};
        String num4[] = new String[]{"g","h","i"};
        String num5[] = new String[]{"j","k","l"};
        String num6[] = new String[]{"m","n","o"};
        String num7[] = new String[]{"p","q","r","s"};
        String num8[] = new String[]{"t","u","v"};
        String num9[] = new String[]{"w","x","y","z"};
        Map<String,String[]> map= new HashMap<String,String[]>();
        map.put("2",num2);
        map.put("3",num3);
        map.put("4",num4);
        map.put("5",num5);
        map.put("6",num6);
        map.put("7",num7);
        map.put("8",num8);
        map.put("9",num9);
        if(digits.length()==0)
            return rsList;
        if(digits.length()==1){
            for(String s:map.get(digits)){
                rsList.add(s);
            }
            return rsList;
        }
        //2 345
        String preChar = "";
        String temp[] = new String[]{};
        for(int i=1;i<digits.length();i++){
           if(i==1){
               preChar = digits.substring(i-1,i);
               temp = map.get(preChar);
           }
           String nowChar = digits.substring(i,i+1);

           String newArr [] = map.get(nowChar);
           List<String> childList = new ArrayList<String>();
           for(int j=0;j<temp.length;j++){
                for(int k =0;k<newArr.length;k++){
                    childList.add(temp[j]+newArr[k]);
                }
           }
            temp = childList.toArray(temp);
        }
        for(int i=0;i<temp.length;i++){
            rsList.add(temp[i]);
        }
        return rsList;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> rsList = new ArrayList<>();
        int length=nums.length;
        int numbArr []=nums;
        int arr1[][]=new int[length][length];
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                arr1[i][j]=numbArr[i]+numbArr[j];
            }
        }

        int long1[]=new int[length*length];
        for(int i=0;i<length*length;i++){
            long1[i]=arr1[i/length][i%length];
        }
        for(int i=0;i<long1.length;i++){
            for(int j=0;j<long1.length;j++){
                if(target==long1[i]+long1[j]&&i!=j){
                    List<Integer> childList = new ArrayList<>();
                    int a = i/length;
                    int b = i%length;
                    int c = j/length;
                    int d = j%length;
                    if(a!=b&&a!=c&&a!=d&&b!=c&&b!=d&&d!=c){
                        childList.add(numbArr[a]);
                        childList.add(numbArr[b]);
                        childList.add(numbArr[c]);
                        childList.add(numbArr[d]);
                       
                        if(!rsList.contains(childList)){
                            rsList.add(childList);
                        }
                    }
                }
            }
        }
        return rsList;
    }

    /**
     * 删除链表倒数第n个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> initStack = new Stack<>();
        Stack<ListNode> stack= new Stack<>();
        ListNode headNode = head;
        while (head!=null){
            initStack.push(head);
            head = head.next;
        }
        if(n==initStack.size()){
            return headNode.next;
        }
        ListNode node =null;
        while(!initStack.empty()){
            n--;
            if(n==0&&!stack.empty()){
                initStack.pop();
                initStack.peek().next=stack.peek();
            }
            else if(n==0&&stack.empty()){
                initStack.pop();
                initStack.peek().next=null;
            }
            node = initStack.pop();
            stack.push(node);

        }
        return stack.peek();
    }

    /**
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<String> stack= new Stack();

        for(int i=0;i<s.length();i++){
            char tempS =s.charAt(i);
            labels:
            switch (tempS){
                case '(':
                    stack.push("(");
                    break;
                case ')':
                    while (!stack.empty()){
                        if(stack.peek().equals("{")||stack.peek().equals("["))
                            return false;
                        if(stack.pop().equals("(")){
                            break labels;
                        }
                    }
                    if(stack.empty())
                        return false;
                    break;
                case '[':
                    stack.push("[");
                    break;
                case ']':;
                    while (!stack.empty()){
                        if(stack.peek().equals("{")||stack.peek().equals("("))
                            return false;
                        if(stack.pop().equals("["))
                            break labels;
                    }
                    if(stack.empty())
                        return false;
                    break;
                case '{':;
                    stack.push("{");
                    break;
                case '}':

                    while (!stack.empty()){
                        if(stack.peek().equals("[")||stack.peek().equals("("))
                            return false;
                        if(stack.pop().equals("{")){
                            break labels;
                        }
                    }
                    if(stack.empty())
                        return false;
                    break;
            }
        }
        return stack.empty();
    }

    /**
     * 合并两个有序列表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode tempList = null;
        ListNode headNode = tempList;
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        while(l1!=null||l2!=null){
            if(l1==null){
                tempList.next=l2;
                break;
            }else if(l2==null){
               tempList.next=l1;
               break;
            }
            if(l1.val<=l2.val){
                if(tempList==null){
                    tempList = new ListNode(l1.val);
                    headNode = tempList;
                    l1=l1.next;
                }else{
                    tempList.next = new ListNode(l1.val);
                    tempList = tempList.next;
                    l1=l1.next;
                }
            }
            else{
                if(tempList==null){
                    tempList = new ListNode(l2.val);
                    headNode = tempList;
                    l2=l2.next;
                }else {
                    tempList.next =new ListNode(l2.val);
                    tempList = tempList.next;
                    l2=l2.next;
                }
            }
        }
        return headNode;
    }

    /**
     * 合并N个排序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode tempNode = new ListNode(0);
        ListNode ansNode = new ListNode(-1);
        ListNode headNode =ansNode;
        while(tempNode!=null){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int i=0;i<lists.length;i++){
                if(lists[i]!=null&&lists[i].val<=min){
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            if(minIndex!=-1){
                tempNode = new ListNode(lists[minIndex].val);
                ansNode.next = tempNode;
                ansNode = ansNode.next;
                lists[minIndex]=lists[minIndex].next;
            }
            else {
                break;
            }
        }
        return headNode.next;
    }

    /**
     * 交换相邻结点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode p = head;
        ListNode q = head.next;
        ListNode rsNode = null;
        ListNode tempNode = null;

        while (p!=null&&p.next!=null){
            p.next=q.next;
            q.next=p;
            if(tempNode==null){
                tempNode =q;
                rsNode = q;
            }
            else{
                tempNode.next.next=q;
                tempNode = tempNode.next.next;
            }
//            System.out.println("tempNode:"+tempNode);
            if(p.next!=null){
                p=p.next;
                q=p.next;
            }
        }
        return rsNode;
    }

    /**
     * k个为一组反转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null||k==1)
            return head;
        ListNode p=head,q=head;
        ListNode rsNode =null;
        ListNode lastNode = new ListNode(-1);
        ListNode singleNode = null;
        while(p!=null){
            int numb=k;
            ListNode temp;
            ListNode arrNode [] =new ListNode[k];
            while(--numb>0&&q.next!=null){
                q=q.next;
            }
            int index =0;
            temp = p;
            while(p!=q){
                arrNode[index++]=p;
                p=p.next;
                if(p==q)
                    arrNode[index]=p;
            }
            p=q.next;
            q=p;
            if(index==0||index+1!=k){
                arrNode[0]=temp;
            }else {
                for(int i=0;i<=index;i++){
                    arrNode[i].next=null;
                }
                for(int i=0;i<index;i++){
                    arrNode[i+1].next=arrNode[i];
                }
            }
            if(index==0||index+1!=k){
                singleNode= arrNode[0];
            }else {
                if(rsNode==null)
                    rsNode = arrNode[index];
                lastNode.next =arrNode[index];
                while (lastNode.next!=null){
                    lastNode=lastNode.next;
                }
            }

        }
        lastNode.next=singleNode;
        if(rsNode==null){
            rsNode=head;
        }
        return rsNode;
    }

    /**
     * 删除排序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length==1)
            return 1;
        int index=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[index]){
                nums[++index]=nums[i];
            }
        }
        return index+1;
    }

    public int removeElement(int[] nums, int val) {
        int index =-1;
        if(nums.length==1&&nums[0]==val){
            return 0;
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[++index]=nums[i];
            }
        }
        return index+1;
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，
     * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {

        if(needle.length()==0){
            return 0;
        }
        else if(haystack.length()==0){
            return -1;
        }
        for(int i=0;i<haystack.length();i++){
            if(haystack.charAt(i)==needle.charAt(0)){
                int numb=0;
                for(int j=0;j<needle.length()&&(i+needle.length()<=haystack.length());j++){
                    if(needle.charAt(j)==haystack.charAt(j+i)){
                        numb++;
                        if(numb==needle.length())
                            return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 超时
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        boolean flag= false;
        boolean fl=false;
        if(dividend==0)
            return 0;
        if(divisor==1)
            return dividend;
        if(dividend==Integer.MIN_VALUE&&divisor<0){
            dividend=Integer.MAX_VALUE;
            divisor = -divisor;
            fl = true;
        }else if(dividend==Integer.MIN_VALUE&&divisor>0){
            flag=true;
            dividend = Integer.MAX_VALUE;
            fl=true;
        }
        if(dividend<0&&divisor<0){
            dividend = -dividend;
            divisor = -divisor;
        }
        if(divisor==1)
            return dividend;
        if(divisor==-1)
            return -dividend;
        else if(dividend<0||divisor<0){
            dividend=dividend<0?-dividend:dividend;
            divisor=divisor<0?-divisor:divisor;
            flag=true;
        }

        long numb=dividend;
        int i=0;
        while(numb>=divisor){
            i++;
            numb = numb-divisor;
        }
        if(fl){
            i = i+1;
        }
        return flag?-i:i;
    }

    /**
     * 大小问题未解决
     * 不解决了
     * 烦
     * @param dividend
     * @param divisor
     * @return
     */
    private int divide2(int dividend, int divisor) {
        if(dividend==0){
            return 0;
        }
        if(divisor==1)
            return dividend;

        int checkNumber =-1;
        boolean flag = false;
        if(dividend==Integer.MIN_VALUE&&divisor==-1){
            return Integer.MAX_VALUE;
        }
        else if(dividend==Integer.MIN_VALUE&&divisor>0){
            dividend = Integer.MAX_VALUE;
            checkNumber = 1;
            flag = true;
        }
        else if(dividend==Integer.MIN_VALUE&&divisor<0){
            dividend = Integer.MAX_VALUE;
            divisor = -divisor;
            checkNumber = 1;
            flag = false;
        }
        //
        else if(dividend<0&&divisor>0){
            flag = true;
            dividend = -dividend;
        }
        else if(dividend>0&&divisor<0){
            flag = true;
            divisor = -divisor;
        }
        else if(dividend<0&&divisor<0){
            dividend = -dividend;
            divisor = -divisor;
        }
        if(divisor>dividend)
            return 0;
        int numb=0;
        int tempCount =0;
        int n = 0;
        while (tempCount<dividend){
            tempCount +=  divisor<<n++;
            if(tempCount  >dividend -(divisor<<n) )
                break;
        }
        int countN = 0;
        int indexN = 0;
        while (indexN!=n){
            countN+= 1<<indexN++;
        }
        tempCount = dividend - tempCount;
        while (tempCount >= divisor) {
            numb++;
            tempCount= tempCount - divisor;
        }
        return flag?-(numb+countN):(numb+countN);
    }
    /**
     * 生成括号
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        this.getStr("",0,0,n);
        return null;
    }

    private String getStr(String str,int left,int right,int max){
        System.out.println(str);
        if(max*2==str.length()){
            return str;
        }
        if(left<max){
            return this.getStr(str+"(",left+1,right,max);
        }
        if(right<left)
            return this.getStr(str+")",left,right+1,max);
        return str;
    }


    public static void main(String[] args) {
        LeetCode1 leetCode = new LeetCode1();
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next=new ListNode(4);
        node1.next.next.next.next=new ListNode(5);
//        node1.next.next.next.next.next =new ListNode(6);
//        node1.next.next.next.next.next.next=new ListNode(7);
        int ts []=new int []{3,2,2,3};

        int  listNode=leetCode.divide(-2147483648, -3);
//        for(int i=0;i<ts.length;i++){
//            System.out.println(ts[i]);
//        }
        System.out.println("rs: "+listNode);
    }

}
