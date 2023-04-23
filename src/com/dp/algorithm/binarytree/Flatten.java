package com.dp.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode_114_二叉树展开为链表_中等
 *
 * reviewed at 2023.04.21
 *
 * @author liuxucheng
 * @since 2021/3/19
 */
public class Flatten {

    public static void main(String[] args) {

    }

    /**
     * 递归，后序遍历
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
        // 找到最右节点
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    /**
     * 前序遍历，空间复杂度O(n)
     *
     * @param root
     */
    private void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = node;
            }
            prev = node;
            // 先push右子节点，再push左子节点
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 迭代-为右子节点找到其前驱
     *
     * 推荐
     *
     * @param root
     */
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root, prev;
        while (cur != null) {
            // 左子树不为空，则需要遍历完左子树找到其中最右边的节点，作为右子节点的前驱
            if (cur.left != null) {
                prev = cur.left;
                // 左子树中的最右节点
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    private TreeNode pre;

    /**
     * 后序DFS，解决展开时丢失右子节点的问题
     *
     * 遍历顺序：右子树->左子树->根节点
     *
     * @param root
     */
    public void flatten4(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten4(root.right);
        flatten4(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}
