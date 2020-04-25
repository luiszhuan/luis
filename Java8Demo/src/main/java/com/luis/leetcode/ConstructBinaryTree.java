package com.luis.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
public class ConstructBinaryTree {
    int[] pre;
    int[] post;
    Map<Integer, Integer> idxMap = new HashMap<>();
    int preIndex = 0;

    public static void main(String[] args) {
        int[] pre = new int[]{3, 4, 1, 2};
        int[] post = new int[]{1, 4, 2, 3};
        TreeNode root = new ConstructBinaryTree().constructFromPrePost(pre, post);
        System.out.println(root);
    }

    //我们令左分支有 L 个节点。我们知道左分支的头节点为 pre[1]，但它也出现在左分支的后序表示的最后。
// 所以 pre[1] = post[L-1]（因为结点的值具有唯一性），因此 L = post.indexOf(pre[1]) + 1。
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        for (int i = 0; i < post.length; i++) {
            idxMap.put(post[i], i);
        }
        return make(0, post.length);

    }

    private TreeNode make(int start, int end) {
        if (start >= end) {
            return null;
        }
        TreeNode curRoot = new TreeNode(pre[preIndex]);
//        if (start + 1 == end) {
//            return curRoot;
//        }
        preIndex++;
        if (preIndex < post.length) {
            //find left
            int indexLeft = idxMap.get(pre[preIndex]);
            if (indexLeft + 1 < end) {
                curRoot.left = make(start, indexLeft + 1);
            }
            if (indexLeft + 1 > start) {
                curRoot.right = make(indexLeft + 1, idxMap.get(curRoot.val));
            }
        }
        return curRoot;
    }
}
