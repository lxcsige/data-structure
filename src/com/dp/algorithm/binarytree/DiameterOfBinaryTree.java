package com.dp.algorithm.binarytree;

/**
 * leetcode_543_二叉树的最大直径_简单
 *
 * reviewed at 2023.04.21
 *
 * @author xucheng.liu
 * @since 2022/5/14
 */
public class DiameterOfBinaryTree {

    private int res = 0;
    /**
     * 假设最长路径经过根结点，则必然是左子树的高度+右子树的高度
     *
     * 后序DFS，遍历所有节点的左子树高度+右子树高度，找到其中最大的
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return res;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        res = Math.max(res, rightDepth + leftDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
