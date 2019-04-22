package custom;

import custom.util.ListNode;
import custom.util.RandomListNode;
import custom.util.TreeNode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SolutionX  {
    public static void main(String[] args) {
        SolutionX sx = new SolutionX();
        int[] numb = new int[]{2,3,1,0,2,5,3};
        int[] rsArr = new int[1];
        Object rs = sx.duplicate(numb,numb.length,rsArr);
        System.out.println(rs);
    }

    /**
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<length;i++){
            if(!set.contains(numbers[i])){
                set.add(numbers[i]);
            }else{
                duplication[0]=numbers[i];
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        Pattern strTern = Pattern.compile("[a-zA-Z]+");
        Matcher matcherStr = strTern.matcher(str);
        if(matcherStr.find()){
            return 0;
        }
        boolean flag = false;
        if(str.startsWith("+")){
            str = str.substring(1);
        }
        if(str.startsWith("-")){
            str = str.substring(1);
            flag = true;
        }

        Pattern pattern = Pattern.compile("([0-9]+)");
        Matcher matcher = pattern.matcher(str);

        int count =0;
        if(matcher.find()&&matcher.groupCount()==1){
            String string = matcher.group(0);
            for(int i=0;i<string.length();i++){
                int bitNumber = string.length()-i-1;
                if((string.charAt(i)-'0')*Math.pow(10,bitNumber)+count>Integer.MAX_VALUE)
                    return 0;
                if(flag){
                    count -= (string.charAt(i)-'0')*Math.pow(10,bitNumber);
                }
                    else{
                    count += (string.charAt(i)-'0')*Math.pow(10,bitNumber);
                }

            }
        }
        return count;
    }

    public int LastRemaining_Solution(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<n;i++){
            list.add(i);
        }
        int index =0;
        for(;index<list.size();){

        }
        System.out.println(list);
        return list.get(0);
    }
    /**
     * 是否连续数
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if(numbers.length==0)
            return false;
        Arrays.sort(numbers);
        int countZero = 0;
        for(int i=0;i<numbers.length-1;i++){
            if(numbers[i]!=0&&numbers[i]==numbers[i+1]){
                return false;
            }
            if(numbers[i]==0){
                countZero++;
            }
            else {
                if(numbers[i]+1!=numbers[i+1]){
                    countZero = countZero-(numbers[i+1]-numbers[i]-1);
                }

            }
        }
        return countZero>=0;
    }

    /**
     *
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if(str==" ")
            return str;
        String[] strs= str.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=strs.length-1;i>=0;i--){
            sb.append(strs[i]);
            if(i!=0)
                sb.append(" ");
        }
        return sb.toString();
    }

    /**
     *
     * @param str abcXYZdef
     * @param n XYZdefabc
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if(n>str.length())
            return str;
        String fString = str.substring(0,n);
        String newString = str.substring(n);
        return newString+fString;
    }
    /**
     * 显然还有更好的方法,如果遇到递增序列,可以左右夹逼,第一个遇到的数对,就是乘积最小的!!
     * 和为S的两个数字
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int count =Integer.MAX_VALUE;
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]+array[j]==sum){
                    if(count>array[i]*array[j]){
                        count = array[i]*array[j];
                        list.clear();
                        list.add(Math.min(array[i],array[j]));
                        list.add(Math.max(array[i],array[j]));
                    }
                }
            }
        }
        return list;
    }
    /**
     *
     * 和为S的连续正数序列
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> rsList = new ArrayList<>();
        ArrayList<Integer> list = null;
        int n =(int)(Math.sqrt(8*sum+1)-1)/2;
        for(int i=n;i>=2;i--){
            int mid = sum/i;
            int subSum =0;
            list = new ArrayList<>();
            if(i%2==0){
                for(int j=0;j<i;j++){
                    int startNumber = mid - i/2+1 +j;
                    subSum +=startNumber;
                    list.add(startNumber);
                }
                if(subSum==sum){
                    rsList.add(list);
                }
            }else{
                for(int j=0;j<i;j++){
                    int startNumber = mid -i/2 +j;
                    subSum +=startNumber;
                    list.add(startNumber);
                }
                if(subSum==sum){
                    rsList.add(list);
                }
            }
        }
//        System.out.println(rsList);
        return rsList;
    }

    /**
     * 找出两个只出现一次的数字
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<array.length;i++){
            if(map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else{
                map.put(array[i],1);
            }
        }
        int flag =0;
        for(Integer in:map.keySet()){
           if(map.get(in)==1){
               if(flag==0){
                   num1[0] =  in;
                   flag=1;
               }else num2[0] = in;
           }
        }
    }

    /**
     * 是不是平衡二叉树
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root==null)
            return false;
        if(Math.abs(this.TreeDepth(root.left)-this.TreeDepth(root.right))<=1)
            return true;
        return false;
    }
    /**
     * 求树的深度
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root==null)
            return 0;
        int depth =1;
        int leftDepth=0;
        int rightDepth=0;
        if(root.left!=null){
            leftDepth++;
            leftDepth+=this.TreeDepth(root.left);
        }if(root.right!=null){
            rightDepth++;
            rightDepth+=this.TreeDepth(root.right);
        }
        return Math.max(leftDepth,Math.max(rightDepth,depth));
    }

    /**
     * 统计一个数字在排序数组中出现的次数
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {
        if(array.length==0||k<array[0])
            return 0;
        int number = 0;
        for(int i=0;i<array.length;i++){
            if(k==array[i]){
                number++;
            }if(k<array[i])
                break;
        }
        return number;
    }
    /**
     * 两个链表 找出第一个公共结点
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p = pHead1;
        ListNode q = pHead2;
        while (p!=null){
            while(q!=null){
                if(q.val==p.val)
                    return q;
                q=q.next;
            }
            p=p.next;
        }
        return null;
    }

    public String PrintMinNumber(int [] numbers) {

        this.getAllNumber(numbers,0,numbers.length-1);

        return String.valueOf(min);

    }


    public int GetUglyNumber_Solution(int index) {
        int[] rs = new int[index+1];
        int numb2=1;
        int numb3=1;
        int numb5=1;
        int temp =1;
        rs[1]=1;
        int tp=0;
        while(temp<index){
            tp = this.getMin(rs[numb2]*2,rs[numb3]*3,rs[numb5]*5);
            if(tp==rs[numb2]*2) numb2++;
            if(tp==rs[numb3]*3) numb3++;
            if(tp==rs[numb5]*5) numb5++;
            rs[++temp] =tp;
        }
//        this.printArr(rs);
        return rs[index];
    }

    private int getMin(int a,int b,int c){
        return Math.min(Math.min(a,b),c);
    }

    public int FirstNotRepeatingChar(String str) {
        for(int i=0;i<str.length();i++){
            for(int j=0;j<str.length();j++){
                if(j!=i&&str.charAt(i)==str.charAt(j)){
                    break;
                }
                if(j==str.length()-1)
                    return i;
            }
        }
        return -1;
    }
    /**
     * 丑数
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
     * 例如6、8都是丑数，但14不是，因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     * @param index
     * @return
     */
    @Deprecated
    public int GetUglyNumber_Solution1(int index) {
        if(index <7)
            return index;
        ArrayList<Integer> list235 = new ArrayList<>();
        ArrayList<Integer> nonelist = new ArrayList<>();
        ArrayList<Integer> ugly = new ArrayList<>();
        ugly.add(1);
        list235.add(2);
        list235.add(3);
        list235.add(5);
        nonelist.addAll(list235);
        nonelist.add(7);
        for(int i=2;i<=100000;i++){
            if(this.isZs(i,nonelist)){
                if(!nonelist.contains(i))
                    nonelist.add(i);
            }

        }
        for(int i=2;i<=100000;i++){
            if(this.isUgly(i,nonelist))
                ugly.add(i);
        }
        System.out.println(ugly);
        return ugly.get(index-1);
    }

    private boolean isUgly(int number,ArrayList<Integer> list){
        if(number%2==0||number%3==0||number%5==0){
            for(Integer i:list){
                if(i!=2&&i!=3&&i!=5&&number%i==0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean isZs(int number,ArrayList<Integer> list){
        for(Integer i:list){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    long min=Long.MAX_VALUE;
    private void getAllNumber(int[]arr,int start,int end){
        if(start==end){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<arr.length;i++){
                sb.append(arr[i]);
            }
            min = Math.min(Long.valueOf(sb.toString()),min);
        }
        else {
            for(int i=start;i<=end;i++){
                swap(arr,i,start);
                this.getAllNumber(arr,start+1,end);
                swap(arr,i,start);
            }
        }

    }



    public int NumberOf1Between1AndN_Solution(int n) {
        int count =0;
        for(int i=1;i<=n;i++){
            String str = String.valueOf(i);
            for(int j=0;j<str.length();j++){
                if(str.charAt(j)=='1')
                    count++;
            }
        }
        return count;
    }


    private void heapheap(int[]arr){
        for(int i=arr.length/2;i>1;i--){
            this.buildHeap(arr,i,arr.length-1);
        }
    }


    private void buildHeap(int[] arr,int i,int length){



    }


    /**
     * 作者：cm问前程
     * 链接：https://www.nowcoder.com/questionTerminal/e8a1b01a2df14cb2b228b30ee6a92163
     * 来源：牛客网
     *
     * 采用阵地攻守的思想：
     * 第一个数字作为第一个士兵，守阵地；count = 1；
     * 遇到相同元素，count++;
     * 遇到不相同元素，即为敌人，同归于尽,count--；当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
     * 再加一次循环，记录这个士兵的个数看是否大于数组一般即可。
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        int times = 1;
        int numb = array[0];
        for(int i=1;i<array.length;i++){
            if(numb == array[i]){
                times++;
            }else{
                times--;
                if(times==0){
                    times = 1;
                    numb = array[i];
                }
            }
        }

        times =0 ;
        for(int i=0;i<array.length;i++){
            if(array[i]==numb){
                times ++;
            }
        }
        return (array.length/2)<times?numb:0;
    }
    public int MoreThanHalfNum_Solution1(int [] array) {
        if(array==null){
            return 0;
        }
        java.util.Arrays.sort(array);
        int count=1;
        int i=1;
        while(i<array.length-1){
            if(array[i]==array[i-1]){
                count++;
                if(count>array.length/2)
                    return array[i];
            }else {
                count=1;
            }
            i++;
        }
        return 0;
    }


    private ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if(str.length()==0){
            return list;
        }else if(str.length()==1){
            list.add(str);
            return list;
        }else{
            char [] chart = str.toCharArray();
            this.getStirngList(list,chart,0,str.length()-1);
        }
        for(String strp:list){

            System.out.println(strp);
        }
        return list;
    }

    /**
     * Key Error
     * @param list
     * @param chars
     * @param start
     * @param end
     */
    private void getStirngList(ArrayList<String> list,char[] chars, int start,int end){
        if(start==end){
            if(!list.contains(new String(chars)))
            list.add(new String(chars));
        }else{
            for(int i=start;i<=end;i++){
                this.swapStr(chars,i,start);
                this.getStirngList(list,chars,start+1,end);
                this.swapStr(chars,i,start);
            }
        }
    }

    /**
     * 别人写的
     * @param list
     * @param s
     * @param from
     * @param to
     */
    public  void permutation(ArrayList<String> list,char[] s,int from,int to) {
        if(from == to) {
            list.add(new String(s));
        } else {
            for(int i=from; i<=to; i++) {
                swapStr(s,i,from); //交换前缀，使其产生下一个前缀
                permutation(list,s, from+1, to);
                swapStr(s,from,i); //将前缀换回，继续做上一个前缀的排列
            }
        }
    }
    /**
     * 交换i,j位置
     * @param str
     * @param i
     * @return
     */
    public void swapStr(char []str,int i,int j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
    private void sortHashMap(HashMap<Integer, ListNode> hashMap){
        List<Map.Entry<Integer,ListNode>> mapList = new ArrayList<>(hashMap.entrySet());
    }


        ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    /**
     * 非常难得
     * @param root
     * @param list
     */
    public void FindPath(TreeNode root, ArrayList<Integer> list) {
        list.add(root.val);
        if(root.left == null && root.right == null){
            ArrayList<Integer> newArray = new ArrayList<>(list);
            listAll.add(newArray);
        }
        if(root.left!=null)
            FindPath(root.left,list);
        if(root.right!=null)
            FindPath(root.right,list);
        list.remove(list.size()-1);//Important!!!!!!!!
    }


    public TreeNode Convert(TreeNode pRootOfTree) {
        java.util.ArrayList<TreeNode > list = new ArrayList<>();
        this.getList(pRootOfTree,list);
        for(int i=1;i<list.size();i++){
            list.get(i-1).right=list.get(i);
            list.get(i).left = list.get(i-1);
        }
        return list.get(0);
    }

    public void getList(TreeNode node,ArrayList<TreeNode> list){
        if(node.left!=null)
            this.getList(node.left,list);
        list.add(node);
        if(node.right!=null)
            this.getList(node.right,list);
    }

    public RandomListNode Clone(RandomListNode pHead)
    {
        System.out.println(pHead);
        if(pHead==null)
            return null;
        RandomListNode head = pHead;
        while(head!=null){
            RandomListNode node = new RandomListNode(head.label);
            node.next = head.next;
            head.next = node;
            head = head.next.next;
        }
        head = pHead;
        while(head!=null){
            if(head.random!=null)
                head.next.random=head.random.next;
            head=head.next.next;
        }
        head = pHead;
        RandomListNode rsNode = head.next;
        RandomListNode temp = rsNode;
        while(temp!=null){
            head.next=temp.next;
            temp.next=head.next==null?null:head.next.next;
            head=head.next;
            temp=temp.next;
        }
        return rsNode;
    }
    public RandomListNode Clone1(RandomListNode pHead)
    {
        RandomListNode head = pHead;
        RandomListNode qHead = null;
        while(pHead!=null){
            RandomListNode node = new RandomListNode(pHead.label);
            if(qHead==null){
                qHead=node;
            }
            node.next = pHead.next;
            pHead = pHead.next;
        }
        RandomListNode rsNode = qHead;
        while(head!=null){
            head.random = qHead.next;
            head=head.next;
            qHead=qHead.next;
        }
        return rsNode;
    }

    private void heapSort(int[] arr){
        int length = arr.length-1;
        for(int i=length/2;i>0;i--){
            this.getHeap(arr,i,length);
        }
        for(int i=length;i>1;i--){
            this.swap(arr,1,i);
            this.getHeap(arr,1,i-1);
        }
    }



    private void getHeap(int[] arr,int start,int length){
        int temp = arr[start];
        for(int i=2*start;i<=length;i*=2){
            if(i<length && arr[i+1]>arr[i]){
                i++;
            }if(temp>=arr[i]){
                break;
            }
            arr[start] = arr[i];
            start = i;
        }
        arr[start] = temp;
    }



    private void swap(int arr[],int start,int end){
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }




    public void quickSort(int []arr){
        int l=0;
        int h=arr.length-1;
        if(l<h){
            qsort(arr,l,h);
        }
    }

    public void qsort(int []A,int left,int right){
        if(left<right){
            int key = A[left];
            int low = left, high = right;
            while (low < high) {
                while(low < high && A[high] >= key) {
                    high--;
                }
                A[low] = A[high];
                while(low < high && A[low] <= key) {
                    low++;
                }
                A[high] = A[low];
            }
            A[low] = key;
            qsort(A,left,low-1);
            qsort(A,low+1,right);
        }

    }

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int width = matrix.length;
        if (width==1){
            list.add(matrix[0][0]);
            return list;
        }
        int startIndexI = 0;
        int i=0;
        int j=0;
        boolean defaults = true;
        list.add(matrix[0][0]);
        while(width>0){
            if(defaults){
                if(j<width-1){
                    list.add(matrix[i][j++]);
                }else if(j==width){//j==width
                    list.add(matrix[i++][j-1]);
                }else if(j==width&&i==width){
                    list.add(matrix[i-1][j--]);
                    defaults = false;
                }
            }else{
                if(j>0){
                    list.add(matrix[i][--j]);
                }
                else if(i>startIndexI+1){//j==0
                    list.add(matrix[--i][j]);
                }else if(i==startIndexI+1){
                    list.add(matrix[i][j++]);
                    startIndexI++;
                    width-=2;
                    defaults = true;
                }
            }
        }
        return list;
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        java.util.ArrayList<Integer> list1= new ArrayList<>();
        java.util.ArrayList<Integer> list2= new ArrayList<>();
        this.iterTree(list1,root1);
        this.iterTree(list2,root2);
        for(Integer i:list1){
            if(list2.contains(i)){
                return true;
            }
        }
        return false;
    }

    private void iterTree(java.util.ArrayList<Integer> list,TreeNode node){
        if(!list.contains(node.val)){
            list.add(node.val);
        }
        if(node.left!=null)
            iterTree(list,node.left);
        if(node.right!=null)
            iterTree(list,node.right);
    }
    public void reOrderArray(int [] array) {
        Integer firstNb = null;
        int i=0;
        while(firstNb==null||firstNb!=array[i]){
            if(firstNb!=null&&array[i]==firstNb)
                break;
            if(array[i]%2==0){
                if(firstNb==null){
                    firstNb = array[i];
                }
                for(int j =i;j<array.length-1;j++){
                    this.changeNumb(array,j);

                }
                i=0;
                continue;
            }
            i++;
        }
    }
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode p=list1;
        ListNode q=list2;
        ListNode temp=new ListNode(-1);
        ListNode rsNode=temp.next;
        while(p!=null||q!=null){
            if(p==null){
                temp.next=q;
            }
            else if(q==null){
                temp.next=p;
            }
            else if(p.val<=q.val){
                temp.next=p;
                p=p.next;
                temp=temp.next;
            }else{
                temp.next=q;
                q=q.next;
                temp=temp.next;
            }
        }
        return rsNode;
    }
    private void changeNumb(int []array,int i){
        int temp = array[i];
        array[i] = array[i+1];
        array[i+1] = temp;
    }


    /**
     * 递归建树
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length==0) return null;
        if (pre.length==1) return new TreeNode(pre[0]);
        TreeNode headNode  = new TreeNode(pre[0]);
        int index = this.getIndexOfNode(headNode,in);
        if(index ==0 && pre.length==2){
            headNode.left = null;
            headNode.right = new TreeNode(in[1]);///待解决
            return headNode;
        }if(index ==1 && pre.length==2){
            headNode.left = new TreeNode(in[0]);
            headNode.right = null;
            return headNode;
        }if(index>=2){
            int leftInArr[]=this.rebuildArr(0,index-1,in);
            int rightInArr[]=this.rebuildArr(index+1,in.length-1,in);
            int leftPreArr[]=this.rebuildArr(1,leftInArr.length,pre);
            int rightPreArr[]=this.rebuildArr(leftInArr.length+1,pre.length-1,pre);
            headNode.left = this.reConstructBinaryTree(leftPreArr,leftInArr);
            headNode.right = this.reConstructBinaryTree(rightPreArr,rightInArr);
        }
        return headNode;
    }

    private int[] rebuildArr(int start,int end,int []arr){
        int newArr[] = new int[end-start+1];
        for(int i=0;i<newArr.length;i++){
            newArr[i]=arr[i+start];
        }
        return newArr;
    }

    private int getIndexOfNode(TreeNode headNode,int []in){
        for(int i=0;i<in.length;i++){
            if(headNode.val==in[i])
                return i;
        }
        return -1;
    }

}