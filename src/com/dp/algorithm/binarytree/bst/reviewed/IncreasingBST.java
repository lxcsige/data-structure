package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode_897_递增顺序搜索树_简单
 *
 * reviewed at 2023.04.21
 *
 * @author xucheng.liu
 * @since 2022/5/25
 */
public class IncreasingBST {

    private TreeNode prev;

    /**
     * 中序DFS
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        prev = dummy;
        dfs(root);
        return dummy.right;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        prev.right = root;
        prev = root;
        root.left = null;
        dfs(root.right);
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST2(TreeNode root) {
        TreeNode newRoot = null, cur = root, prev = null;
        Deque<TreeNode> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (newRoot == null) {
                newRoot = cur;
            }
            if (prev != null) {
                prev.right = cur;
            }
            prev = cur;
            cur.left = null;
            cur = cur.right;
        }
        return newRoot;
    }

    /**
     * 迭代-莫里斯遍历
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST3(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        // prev-cur节点左子树的最右节点
        // tail-链表的尾节点
        TreeNode cur = root, prev, tail = dummy;
        while (cur != null) {
            // 左子树为空，可以直接将cur节点加到链表中
            if (cur.left == null) {
                tail.right = cur;
                tail = cur;
                cur = cur.right;
            } else {
                prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                // 第一次遍历，左子树尚未被访问，此时不能将cur节点加入到链表中
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次遍历，左子节点已经被访问，此时tail==cur.left
                    cur.left = null;
                    tail.right = cur;
                    tail = cur;
                    cur = cur.right;
                }
            }
        }
        return dummy.right;
    }
}
