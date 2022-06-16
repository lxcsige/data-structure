package com.dp.algorithm.binarytree.bsttree;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_700_二叉搜索树中的搜索_中等
 *
 * @author liuxucheng
 * @since 2021/4/21
 */
public class SearchBST {

    public static void main(String[] args) {

    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = root.val > val ? root.left : root.right;
        }

        return root;
    }
}
