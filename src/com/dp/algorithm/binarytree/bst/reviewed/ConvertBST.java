package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_538_把二叉搜索树转换为累加树_中等
 *
 * reviewed at 2023.04.21
 *
 * @author liuxucheng
 * @since 2021/3/24
 */
public class ConvertBST {

    int sum;

    public static void main(String[] args) {

    }

    private TreeNode convertBST(TreeNode root) {
        inOrder(root);
        return root;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.right);

        root.val = root.val + sum;
        sum = root.val;

        inOrder(root.left);
    }
}
