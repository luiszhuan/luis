package com.luis.leetcode;

//https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 1, 2, 2, 3, 3, 4};
        new RemoveDuplicatesFromSortedArray().removeDuplicates(nums);
    }

    public int removeDuplicates(int[] nums) {
        int flag = 0;
        int currIndex = 1;
        while (currIndex < nums.length) {
            if (nums[currIndex] != nums[flag]) {
                nums[++flag] = nums[currIndex++];
            } else {
                currIndex++;
            }
        }
        return flag + 1;
    }
}
