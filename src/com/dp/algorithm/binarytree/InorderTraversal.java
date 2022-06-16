package com.dp.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode_94_中序遍历
 *
 * @author xucheng.liu
 * @since 2022/5/12
 */
public class InorderTraversal {

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 注意循环终止条件
        while (cur != null || !stack.isEmpty()) {
            // 左节点入栈，直到节点为空
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 弹出栈顶节点
            cur = stack.pop();
            res.add(cur.val);
            // 将该节点的右子节点压入栈
            cur = cur.right;
        }
        return res;
    }

    /**
     * 莫里斯遍历，空间复杂度O(1)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
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
                // 第一次遍历到cur节点，其左子树还未遍历
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次遍历到cur节点，说明左子树已经遍历完，将right指针恢复为null，继续遍历右子树
                    prev.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }

        return res;
    }
}
