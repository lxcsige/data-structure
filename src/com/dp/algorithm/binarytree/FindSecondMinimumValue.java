package com.dp.algorithm.binarytree;

/**
 * leetcode_671_二叉树中第二小的节点_简单
 *
 * reviewed at 2023.04.21
 *
 * @author xucheng.liu
 * @since 2022/5/25
 */
public class FindSecondMinimumValue {

    private int res = -1;

    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return res;
    }

    private void dfs(TreeNode root, int val) {
        if (root == null) {
            return;
        }
        if (root.val > val) {
            res = res == -1 ? root.val : Math.min(root.val, res);
        }
        dfs(root.left, val);
        dfs(root.right, val);
    }
}
