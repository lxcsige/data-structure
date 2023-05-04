package com.dp.algorithm.binarytree;

/**
 * leetcode_513_找树左下角的值_中等
 */
public class FindBottomLeftValue {

    private int res;

    private int maxLevel;

    public int findBottomLeftValue(TreeNode root) {
        res = root.val;
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        // 叶子节点
        if (root.left == null && root.right == null) {
            if (level > maxLevel) {
                maxLevel = level;
                res =  root.val;
            }
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}
