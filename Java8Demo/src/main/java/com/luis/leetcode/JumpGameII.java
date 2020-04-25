package com.luis.leetcode;

/**
 * https://leetcode-cn.com/problems/jump-game-ii/
 */
public class JumpGameII {

    public int jump(int[] nums) {
        /**
         * 1. Reach the End return
         * 2. Find the max jump Step
         * 3. Jump;
         */
        int jumpCount = 0;
        int curPosition = 0;
        while (curPosition < nums.length - 1) {
            int curMaxSteps = nums[curPosition];
            if (curMaxSteps + curPosition >= nums.length - 1) {
                return ++jumpCount;
            }
            int nextPosition = curPosition;
            int tmpMax = 0;
            for (int i = 1; i <= curMaxSteps; i++) {
                int curMax = nums[i + curPosition] + i;
                if (curMax > tmpMax) {
                    tmpMax = curMax;
                    nextPosition = i + curPosition;
                }
            }
            jumpCount++;
            curPosition = nextPosition;
        }
        return jumpCount;
    }

    public int jump2(int[] nums) {
        int ans = 0;
        int maxPoitision = 0;
        int lastJumpMaxPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + nums[i] > maxPoitision) {
                maxPoitision = i + nums[i];
            }
            // 如果当前节点是出发的位置，那么下一跳的位置必定在当前节点能够到达的最大节点之前。
            // 因此，在当前节点开始能够到达的最大节点处跳动次数+1
            if (lastJumpMaxPosition == i) {
                lastJumpMaxPosition = maxPoitision;
                ans++;
            }
        }
        return ans;
    }
}
