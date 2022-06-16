package com.dp.algorithm.binarytree.path;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_437_路径总和3_中等
 *
 * @author xucheng.liu
 * @since 2022/5/26
 */
public class PathSum2 {

    /**
     * 递归穷举所有从所有节点出发的路径，时间复杂度O(n^2)，存在重复计算
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = dfs(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    private int dfs(TreeNode root, int targetSum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        if (root.val == targetSum) {
            res++;
        }
        res += dfs(root.left, targetSum - root.val);
        res += dfs(root.right, targetSum - root.val);
        return res;
    }
}
