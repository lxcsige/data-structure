package com.dp.algorithm.binarytree.path.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_129_求根结点到叶节点数字之和_中等
 */
public class SumNumbers {

    private int res;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int num) {
        if (root == null) {
            return;
        }
        num = num * 10 + root.val;
        // 叶节点
        if (root.left == null && root.right == null) {
            res += num;
        }
        // num不需要进行回溯，因为java是值传递，dfs方法返回之后num不会发生变化
        dfs(root.left, num);
        dfs(root.right, num);
    }
}
