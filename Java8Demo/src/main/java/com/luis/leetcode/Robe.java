package com.luis.leetcode;

//https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-by-leetcode/
public class Robe {
    public static void main(String[] args) {

    }

    /**
     * f(1)=v1
     * f(2)=max(f(1),v2)
     * f(3)=max(f(1)+v3,f(2)
     * f(4)=max(f4-2)+v4,f(3)e
     */
    public int rob(int[] nums) {
        int cur = 0;
        int lastRob = 0;
        int lastLastRob = 0;
        for (int i = 0; i < nums.length; i++) {
            cur = lastLastRob + nums[i] > lastRob ? lastLastRob + nums[i] : lastRob;
            lastLastRob = lastRob;
            lastRob = cur;
        }
        return cur;
    }
}
