package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_669_修建二叉搜索树_中等
 */
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // root不在范围内，需要移除
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
