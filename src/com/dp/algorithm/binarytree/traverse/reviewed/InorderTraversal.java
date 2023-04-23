package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode_94_中序遍历
 *
 * reviewed at 2023.04.19
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
     * 推荐，统一
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // 暂存根节点
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 注意循环终止条件：可以进栈或出栈
        // cur != null：有节点，可以进栈
        // !stack.isEmpty()：栈不为空，可以出栈
        // 一个节点什么时候可以出栈：左子树不存在或已完成遍历
        // 终止条件：没有节点要进栈或出栈
        // 出栈的同时处理节点，即将节点值添加到结果列表中
        while (cur != null || !stack.isEmpty()) {
            // 指针用来访问节点
            // 左节点入栈，直到节点为空
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 弹出栈顶节点，这是要处理的节点（中间节点），其左子树已经完成遍历
            cur = stack.pop();
            // 将节点值添加到结果列表中
            res.add(cur.val);
            // 将右子节点压入栈
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
