package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_700_二叉搜索树中的搜索_简单
 *
 * reviewed at 2023.04.20
 *
 * @author liuxucheng
 * @since 2021/4/21
 */
public class SearchBST {

    public static void main(String[] args) {

    }

    /**
     * 递归
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // base case
        if (root == null || root.val == val) {
            return root;
        }
        return root.val < val ? searchBST(root.right, val) : searchBST(root.left, val);
    }

    /**
     * 迭代
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = root.val > val ? root.left : root.right;
        }

        return root;
    }
}
