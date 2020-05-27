package com.luis.leetcode;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class ContainerMostWater {
    /**
     * 1、找到左右两个点。计算面积，从最小的开始。
     * 2、从左边计算一次面积并向右移动。一直到比右边的高。然后转到3
     * 3、从右边向左移动，如果升序则再计算一次面积，转到2。否则向左边移动。
     * 4、如果左=右则停止。
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        boolean processLeft = height[left] <= height[right];
        int curMaxHeight = processLeft ? height[right] : height[left];
        int maxArea = 0;
        while (left < right) {
            while (processLeft && left < right) {
                if (height[left] <= curMaxHeight) {
                    maxArea = Math.max(maxArea, height[left] * (right - left));
                    left++;
                } else {
                    //maxArea = curMaxHeight * (right - left);
                    curMaxHeight = height[left];
                    processLeft = false;
                }
            }
            while (!processLeft && left < right) {
                if (height[right] <= curMaxHeight) {
                    maxArea = Math.max(maxArea, height[right] * (right - left));
                    right--;
                } else {
                    //maxArea = curMaxHeight * (right - left);
                    curMaxHeight = height[right];
                    processLeft = true;
                }
            }
        }
        return maxArea;
    }
}
