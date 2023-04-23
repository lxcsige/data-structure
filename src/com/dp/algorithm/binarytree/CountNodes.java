package com.dp.algorithm.binarytree;

/**
 * leetcode_222_完全二叉树的节点个数_中等
 */
public class CountNodes {

    /**
     * 考虑完全二叉树的特性
     * 后序DFS
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        // 左子树已满，右子树可能满
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        }
        // 右子树已满，左子树可能满
        else {
            return countNodes(root.left) + (1 << right);
        }
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}
