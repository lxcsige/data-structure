package com.dp.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode_101_对称二叉树_简单
 *
 * reviewed at 2023.04.20
 *
 * @author xucheng.liu
 * @since 2022/5/12
 */
public class IsSymmetric {

    /**
     * 后序DFS
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        // 1. 左右节点均为空
        if (left == null && right == null) {
            return true;
        }
        // 2. 左或右节点为空
        if (left == null || right == null) {
            return false;
        }
        // 3. 左右节点都不为空
        // 3.1 左右节点值不同
        if (left.val != right.val) {
            return false;
        }
        // 3.2 比较左节点的左子节点和右节点的右子节点（外侧）
        // 3.3 比较左节点的右子节点和右节点的左子节点（内侧）
        return dfs(left.right, right.left) && dfs(left.left, right.right);
    }

    /**
     * 迭代，BFS
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 队列中可能包含null
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }
}
