package com.luis.leetcode;

//https://leetcode-cn.com/problems/remove-element/
public class RemoveElement {
    public static void main(String[] args) {
        int[] input = new int[]{3, 2, 3, 1, 3, 2, 3};
        int val = 3;
        new RemoveElement().removeElement(input, val);
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int currIndex = 0;
        int flag = 0;
        while (currIndex < nums.length) {
            if (nums[currIndex] == val) {
                currIndex++;
            } else {
                nums[flag++] = nums[currIndex++];
            }
        }
        return flag;
    }
}
