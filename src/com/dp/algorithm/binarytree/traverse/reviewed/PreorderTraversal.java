package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.*;

/**
 * leetcode_144_二叉树的前序遍历_简单
 *
 * reviewed at 2023.04.19
 *
 * @author xucheng.liu
 * @since 2022/5/12
 */
public class PreorderTraversal {

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }

    /**
     * 迭代，模拟DFS递归过程
     * 推荐，统一
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 左子节点入栈，直到左子节点为空
            while (cur != null) {
                stack.push(cur);
                res.add(cur.val);
                cur = cur.left;
            }
            cur = stack.pop().right;
        }

        return res;
    }

    /**
     * 迭代，利用栈后进先出的特性
     * 入栈顺序：根-右-左，出栈顺序：根-左-右
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            // 先push左子节点，再push右子节点
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }

    /**
     * 莫里斯遍历，空间复杂度O(1)，利用左子树的最右子节点来保存root节点，回溯时通过cur = cur.right即可
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root, prev;
        while (cur != null) {
            // 没有左子树
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                // 找到左子树的最右节点，其right指向null，用于暂时保存cur节点
                prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                // 第一次遍历
                if (prev.right == null) {
                    res.add(cur.val);
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次遍历到cur节点，说明左子树已经遍历完，将right指针恢复为null，继续遍历右子树
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }

        return res;
    }
}
