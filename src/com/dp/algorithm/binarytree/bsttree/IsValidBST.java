package com.dp.algorithm.binarytree.bsttree;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * @author liuxucheng
 * @since 2021/4/20
 */
public class IsValidBST {

    private int pre = Integer.MIN_VALUE;

    public static void main(String[] args) {

    }

    public boolean isValidBST(TreeNode root) {
//        return inOrder(root);
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 中序遍历升序
     *
     * @param root
     * @return
     */
    private boolean inOrder(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = inOrder(root.left);

        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        boolean right = inOrder(root.right);

        return left && right;
    }

    /**
     * 递归，自上而下传递边界值
     * 根节点 > 左子树 & 根节点 < 右子树
     *
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
