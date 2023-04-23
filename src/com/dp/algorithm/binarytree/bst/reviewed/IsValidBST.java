package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode_98_验证二叉搜索树_中等
 *
 * 左子树所有节点小于中间节点，右子树所有节点大于中间节点
 *
 * reviewed at 2023.04.20
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


    public boolean isValidBST1(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 前序遍历，上下界
     * 自上而下
     *
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValidBST(TreeNode root, long min, long max) {
        // base case
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    /**
     * 中序遍历升序
     *
     * 推荐
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValidBST2(root.left)) {
            return false;
        }

        // 中序升序
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
}
