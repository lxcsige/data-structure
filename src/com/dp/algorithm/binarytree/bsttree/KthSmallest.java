package com.dp.algorithm.binarytree.bsttree;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * @author liuxucheng
 * @since 2021/3/23
 */
public class KthSmallest {

    public static void main(String[] args) {

    }

    int rank = 0;

    int res = 0;

    /**
     * 二叉搜索树第k小的节点
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    /**
     * 中序遍历
     *
     * @param root
     * @param k
     */
    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        traverse(root.left, k);

        rank++;
        if (k == rank) {
            res = root.val;
            return;
        }

        traverse(root.right, k);
    }

    /**
     * 第k大的节点
     *
     * @param root
     * @param k
     */
    private void traverse2(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        traverse(root.right, k);

        rank++;
        if (k == rank) {
            res = root.val;
            return;
        }

        traverse(root.left, k);
    }

    /**
     * 迭代
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.removeLast();
            if (k-- == 0) {
                return root.val;
            }

            root = root.right;
        }
    }
}
