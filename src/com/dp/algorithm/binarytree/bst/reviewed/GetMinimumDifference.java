package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_530_二叉搜索树的最小绝对差_简单
 */
public class GetMinimumDifference {

    private int res = Integer.MAX_VALUE;

    private Integer pre;

    /**
     * 中序DFS
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return res;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        int cur = root.val;
        if (pre != null) {
            res = Math.min(res, cur - pre);
        }
        pre = cur;
        inOrder(root.right);
    }
}
