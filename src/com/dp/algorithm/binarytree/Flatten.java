package com.dp.algorithm.binarytree;

/**
 * @author liuxucheng
 * @since 2021/3/19
 */
public class Flatten {

    public static void main(String[] args) {

    }

    /**
     * 将二叉树按照先序遍历拉成单链表
     *
     * @param root
     */
    private void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // 分别拉平左右子树
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }
}
