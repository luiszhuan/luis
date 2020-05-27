package com.luis.leetcode;

public class MaxEqualSubstringsWithinBudget {
    private String src;
    private String dest;

    /**
     * 获取每个字符acsii码差值的绝对值，存入diff数组
     * 对diff数组运用sliding window算法：窗口最大值条件是sum(left,right,diff[i])<=maxCost，将目前消耗存入currCost
     * 若currCost<=maxCost，right指针右移，更新result
     * 若currCost>maxCost,left指针右移，更新currCost
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int right = 0;
        src = s;
        dest = t;
        int maxLength = 0;
        int curBudget = 0;
        while (right < s.length()) {
            // int nextBudget = getDist(right);
            curBudget += getDist(right);
            right++;
            if (curBudget > maxCost) {
                while (curBudget > maxCost) {
                    curBudget -= getDist(left);
                    left++;
                }
            }
            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }

    private int getDist(int i) {
        return Math.abs(src.charAt(i) - dest.charAt(i));
    }
}
