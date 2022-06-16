package com.dp.algorithm.binarytree;

/**
 * leetcode_226_翻转二叉树_简单
 *
 * @author xucheng.liu
 * @since 2022/5/20
 */
public class InvertTree {

    /**
     * 递归，前序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
