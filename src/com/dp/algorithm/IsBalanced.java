package com.dp.algorithm;

import com.dp.algorithm.btree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xucheng.liu
 * @date 2021/3/25
 */
public class IsBalanced {

    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    private int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 1;
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (!queue.isEmpty()) {
                height++;
            }
        }

        return height;
    }
}
