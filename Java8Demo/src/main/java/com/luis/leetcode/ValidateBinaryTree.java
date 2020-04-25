package com.luis.leetcode;

//https://leetcode-cn.com/problems/validate-binary-search-tree/
public class ValidateBinaryTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        if (!isValidBST(root.left, min, root.val)) {
            return false;
        }
        if (!isValidBST(root.right, root.val, max)) {
            return false;
        }
        return true;
    }
}
