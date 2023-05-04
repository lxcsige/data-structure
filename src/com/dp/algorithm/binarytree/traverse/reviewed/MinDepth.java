package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * leetcode_111_二叉树的最小深度_简单
 *
 * 最小深度：根节点到叶子节点的最短路径上的节点数量
 */
public class MinDepth {

    /**
     * 后序DFS
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 1. 左右子节点都为空
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 2. 左子节点为空
        else if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        // 3. 右子节点为空
        else if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        // 4. 左右子节点都不为空
        else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }

    /**
     * BFS
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 叶子节点
                if (cur.right == null && cur.left == null){
                    return res;
                }
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }
            res++;
        }
        return res;
    }
}
