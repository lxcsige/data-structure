package com.dp.algorithm.binarytree.bsttree;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode_98_验证二叉搜索树_中等
 *
 * @author liuxucheng
 * @since 2021/4/20
 */
public class IsValidBST {

    /**
     * 节点最小值可能是-2^31
     */
    private long pre = Long.MIN_VALUE;

    public static void main(String[] args) {

    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 中序遍历升序
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = isValidBST2(root.left);
        if (!left) {
            return false;
        }

        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        return isValidBST2(root.right);
    }

    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null ||!stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (cur.val <= pre) {
                return false;
            }
            pre = cur.val;
            cur = cur.right;
        }
        return true;
    }

    /**
     * 前序遍历，自上而下传递边界值
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
