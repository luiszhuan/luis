package com.luis.leetcode;

//https://leetcode-cn.com/problems/trapping-rain-water/
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] input = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new TrappingRainWater().trap(input));
        System.out.println(new TrappingRainWater().trap2(input));
        System.out.println(new TrappingRainWater().trap4(input));
        System.out.println(new TrappingRainWater().trap4_1(input));
    }

    public int trap4_1(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max_left = 0;
        int max_right = 0;
        int result = 0;
        while (left < right) {
            if (height[left] < height[right]) {//last index left(right) is mex_left(max_right)
                if (max_left <= height[left]) {
                    max_left = height[left];
                } else {
                    result += max_left - height[left];
                }
                left++;
            } else {
                if (max_right <= height[right]) {//last index left(right) is mex_left(max_right)
                    max_right = height[right];
                } else {
                    result += max_right - height[right];
                }
                right--;
            }
        }
        return result;
    }

    public int trap4(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int result = 0;
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    result += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    result += rightMax - height[right];
                }
                right--;
            }
        }
        return result;
    }

    public int trap3(int[] height) {
        int result = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            leftMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return result;
    }

    public int trap2(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            // Search the left
            int leftMax = 0;
            for (int left = i; left >= 0; left--) {
                if (leftMax <= height[left]) {
                    leftMax = height[left];
                }
            }
            int rightMax = 0;
            for (int right = i; right < height.length; right++) {
                if (rightMax <= height[right]) {
                    rightMax = height[right];
                }
            }
            result += Math.min(leftMax, rightMax) - height[i];
        }
        return result;
    }

    public int trap(int[] height) {
        int maxHeight = 1;
        for (int i = 0; i < height.length; i++) {
            if (maxHeight < height[i]) {
                maxHeight = height[i];
            }
        }
        int curHeight = 1;

        int result = 0;
        //for (int i = 0; i < height.length; i++) {
        while (curHeight <= maxHeight) {
            int left = 0;
            int right = left + 1;
            while (left < height.length && right < height.length) {
                while (left < height.length && height[left] < curHeight) {
                    left++;
                }
                while (right < height.length && height[right] < curHeight) {
                    right++;
                }
                if (left < right && left < height.length && right < height.length) {
                    result += right - left - 1;
                }
                left = right;
                right++;
            }
            curHeight++;
        }
        return result;
    }
}
