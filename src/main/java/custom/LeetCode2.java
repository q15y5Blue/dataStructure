package custom;

import custom.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeetCode2 {


    /**
     * 连续和最小
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            max = Math.max(sum, max);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }


    public int minDistance(String word1, String word2) {
        int[][] distance = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            for (int j = 0; j < word2.length() + 1; j++) {
                if (i == 0)
                    distance[i][j] = j;
                if (j == 0)
                    distance[i][j] = i;
                if (i > 0 && j > 0 && word1.charAt(i - 1) == word2.charAt(j - 1))
                    distance[i][j] = distance[i - 1][j - 1];
                else if (i > 0 && j > 0) {
                    int min = Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1);
                    distance[i][j] = Math.min(distance[i - 1][j - 1] + 1, min);
                }
            }
        }

        return distance[word1.length()][word2.length()];
    }

    /**
     * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
     * 输出的顺序不重要, [9,0] 也是有效答案。
     *
     * @param s     =   "barfoothefoobarman",
     * @param words = ["foo","bar"]
     * @return 输出：[0,9]
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List ans = new ArrayList<String>();
        this.getStrCollection(words, 0, ans);
        return ans;
    }

    /**
     * 全排列字符串
     *
     * @return
     */
    public void getStrCollection(String[] words, int starIndex, List<String> ans) {
        if (starIndex == words.length - 1)
            ans.add(words[starIndex]);
        else {
            for (int j = starIndex; j < words.length; j++) {
                swap(starIndex, j, words);
                getStrCollection(words, starIndex + 1, ans);
                swap(starIndex, j, words);
            }
        }
    }

    private void swap(int i, int j, String[] strings) {
        String tmep = strings[i];
        strings[i] = strings[j];
        strings[j] = tmep;
    }

    /**
     * 33 题
     * 折半查找的应用
     * 0,1,2,4,5,6,7   ---->>>> 4,5,6,7,0,1,2
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int index = midSearch(nums, 0, nums.length - 1, target);
        return index;
    }

    private int midSearch(int[] nums, int starIndex, int endIndex, int target) {
        int mid = (starIndex + endIndex) / 2;
        if (starIndex + 1 == endIndex) {
            if (nums[starIndex] == target)
                return starIndex;
            if (nums[endIndex] == target)
                return endIndex;
            else return -1;
        }
        if (nums[mid] == target) {
            return mid;
        }
        //前半为顺序
        if (nums[mid] > nums[starIndex]) {
            if (nums[starIndex] <= target && nums[mid] >= target) {
                return midSearch(nums, starIndex, mid - 1, target);
            } else
                return midSearch(nums, mid + 1, endIndex, target);
        } else if (nums[mid] < nums[endIndex]) {
            if (nums[mid] <= target && target <= nums[endIndex])
                return midSearch(nums, mid + 1, endIndex, target);
            else
                return midSearch(nums, starIndex, mid - 1, target);
        }
        return -1;
    }

    /**
     * 字符串相乘
     * strData:
     * 超时,需要找到另外的方法
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map.put(String.valueOf(i) + j, i * j);
            }
        }
        int count = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int bitNumber1 = (int) Math.pow(10, num1.length() - 1 - i);
            for (int j = num2.length() - 1; j >= 0; j--) {
                int bitNumber2 = (int) Math.pow(10, num2.length() - 1 - j);
                String valueStr = String.valueOf(num1.charAt(i)) + num2.charAt(j);
//                System.out.println(count);
                count += map.get(valueStr) * bitNumber1 * bitNumber2;
            }
        }
        return String.valueOf(count);
    }

    public void Mirror(TreeNode root) {
        this.moveNode(root);
        if (root.left != null) {
            this.Mirror(root.left);
        }
        if (root.right != null) {
            this.Mirror(root.right);
        }
    }

    private void moveNode(TreeNode thisNode) {
        if (thisNode.left != null) {
            if (thisNode.right == null) {
                thisNode.right = thisNode.left;
            } else {
                TreeNode node = thisNode.left;
                thisNode.left = thisNode.right;
                thisNode.right = node;
            }
        } else if (thisNode.right != null) {
            thisNode.left = thisNode.right;
        } else return;

    }

    public String replaceSpace(StringBuffer str) {
        String str1 = str.toString();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            if (str.charAt(i) == (' ')) {
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public int Fibonacci(int n) {
        if (n <= 1)
            return n;
        int numb[] = new int[]{0, 1};
        int ans = 0;
        int i = 1;
        while (i++ < n) {
            ans = numb[0] + numb[1];
            if (i % 2 == 0) {
                numb[0] = ans;
            } else {
                numb[1] = ans;
            }
        }
        return ans;
    }

    public double Power(double base, int exponent) {
        int tempNo = exponent;
        double rs = base;
        if (exponent == 1)
            return base;
        else if (exponent > 1) {
            while (tempNo-- != 0) {
                if (rs * base > Double.MAX_VALUE) {

                }
                rs = rs * base;
            }
            return rs;
        } else if (exponent > 0 && exponent < 1) {
            tempNo = -exponent;
            while (tempNo-- != 0) {
                if (rs * base < Double.MIN_VALUE) {

                }
                rs = tempNo * (-base);
            }
            return 1 / rs;
        } else {
            return 1;
        }
    }

    public int JumpFloorII(int target) {
        if (target == 0)
            return 0;
        if (target == 1)
            return 1;
        if (target == 2)
            return 2;
        else {
            int sum = 0;
            for (int i = 1; i <= target; i++) {
                sum += JumpFloorII(i - 1);
            }
            return sum + 1;
        }
    }


}
