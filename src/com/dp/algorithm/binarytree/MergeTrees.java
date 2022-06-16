package com.dp.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode_617_合并二叉树_简单
 *
 * @author xucheng.liu
 * @since 2022/5/12
 */
public class MergeTrees {

    /**
     * 递归，DFS
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);

        return root;
    }

    /**
     * 迭代，BFS
     * 将root2合并到root1中
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            t1.val += t2.val;
            if (t1.left != null && t2.left != null) {
                queue.offer(t1.left);
                queue.offer(t2.left);
            } else if (t1.left == null) {
                // 如果t1的左子树为空，直接将t2的左子树挂到t1的左子树位置
                t1.left = t2.left;
            }
            if(t1.right != null && t2.right != null) {
                queue.offer(t1.right);
                queue.offer(t2.right);
            } else if(t1.right == null) {
                t1.right = t2.right;
            }
        }

        return root1;
    }

    /**
     * 迭代BFS，2个队列
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees3(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        TreeNode root = new TreeNode(root1.val + root2.val);
        queue1.offer(root);
        queue2.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode t = queue1.poll();
            TreeNode t1 = queue2.poll();
            TreeNode t2 = queue2.poll();
            if (t1.left != null && t2.left != null) {
                t.left = new TreeNode(t1.left.val + t2.left.val);
                queue1.offer(t.left);
                queue2.offer(t1.left);
                queue2.offer(t2.left);
            } else if (t1.left != null) {
                t.left = t1.left;
            } else if (t2.left != null) {
                t.left = t2.left;
            }
            if (t1.right != null && t2.right != null) {
                t.right = new TreeNode(t1.right.val + t2.right.val);
                queue1.offer(t.right);
                queue2.offer(t1.right);
                queue2.offer(t2.right);
            } else if (t1.right != null) {
                t.right = t1.right;
            } else if (t2.right != null) {
                t.right = t2.right;
            }
        }

        return root;
    }
}
