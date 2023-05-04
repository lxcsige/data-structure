package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_701_二叉搜索树中的插入操作_中等
 *
 * reviewed at 2023.04.20
 *
 * @author liuxucheng
 * @since 2021/4/22
 */
public class InsertIntoBST {

    public static void main(String[] args) {

    }

    /**
     * 迭代
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode prev = null;
        TreeNode cur = root;
        while (cur != null) {
            prev = cur;
            cur = cur.val > val ? cur.left : cur.right;
        }
        if (prev.val > val) {
            prev.left = new TreeNode(val);
        } else {
            prev.right = new TreeNode(val);
        }
        return root;
    }

    /**
     * 递归
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST2(root.left, val);
        } else {
            root.right = insertIntoBST2(root.right, val);
        }

        return root;
    }
}
