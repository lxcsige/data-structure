package com.dp.algorithm.binarytree;

/**
 * leetcode_559_N叉树的最大深度_简单
 *
 * @author xucheng.liu
 * @since 2022/5/25
 */
public class MaxDepthN {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node node : root.children) {
            max = Math.max(max, maxDepth(node));
        }
        return max + 1;
    }
}
