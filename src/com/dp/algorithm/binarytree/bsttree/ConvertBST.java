package com.dp.algorithm.binarytree.bsttree;

import com.dp.algorithm.binarytree.TreeNode;

/**
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
