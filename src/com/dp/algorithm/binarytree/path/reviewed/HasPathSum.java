package com.dp.algorithm.binarytree.path.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_112_路径总和_简单
 *
 * @author xucheng.liu
 * @since 2022/5/26
 */
public class HasPathSum {

    /**
     * 递归，前序遍历
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
