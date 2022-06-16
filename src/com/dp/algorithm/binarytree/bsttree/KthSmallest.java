package com.dp.algorithm.binarytree.bsttree;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * leetcode_230_二叉搜索树中第k小的元素
 *
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
     * 中序遍历-递归
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
     * 中序遍历-迭代
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            cur = cur.right;
        }
        return cur.val;
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

    private Map<TreeNode, Integer> map = new HashMap<>();

    public int kthSmallest3(TreeNode root, int k) {
        pre(root);
        TreeNode cur = root;
        while (cur != null) {
            int left = map.getOrDefault(cur.left, 0);
            if (left < k - 1) {
                // 第k个小的元素在右子树中
                cur = cur.right;
                k -= (left + 1);
            } else if (left > k -1) {
                // 第k个小的元素在左子树中
                cur = cur.left;
            } else {
                break;
            }
        }
        return cur.val;
    }

    private int pre(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 1 + pre(root.left) + pre(root.right);
        map.put(root, res);
        return res;
    }
}
