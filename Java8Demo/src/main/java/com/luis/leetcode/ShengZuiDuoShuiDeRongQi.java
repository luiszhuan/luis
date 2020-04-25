package com.luis.leetcode;

//https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode/
public class ShengZuiDuoShuiDeRongQi {
    public static void main(String[] args) {
        int[] input = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        new ShengZuiDuoShuiDeRongQi().maxArea(input);
    }

    public int maxArea(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            max = Math.max(max, (r - l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
