package com.dp.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode_110_平衡二叉树_简单
 *
 * reviewed at 2023.04.20
 *
 * @author xucheng.liu
 * @date 2021/3/25
 */
public class IsBalanced {

    public static void main(String[] args) {

    }

    /**
     * 自上而下，多次调用height方法
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    private int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 1;
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (!queue.isEmpty()) {
                height++;
            }
        }

        return height;
    }

    /**
     * 递归，自底向上，后序遍历
     *
     * @param root
     * @return
     */
    public boolean isBalanced3(TreeNode root) {
        return height3(root) > 0;
    }

    /**
     * 计算树的高度，同时通过返回值反应树是否平衡
     * > 0：平衡
     * == -1：不平衡
     *
     * @param root
     * @return
     */
    private int height3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height3(root.left);
        // leftHeight == -1：左子树不平衡
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = height3(root.right);
        // rightHeight == -1：右子树不平衡
        if (rightHeight == -1) {
            return -1;
        }
        // Math.abs(leftHeight - rightHeight) > 1：当前树不平衡
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
