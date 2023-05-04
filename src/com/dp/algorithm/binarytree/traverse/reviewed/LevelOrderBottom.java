package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode_107_二叉树的层序遍历II_中等
 *
 * reviewed at 2023.04.20
 *
 * @author xucheng.liu
 * @since 2022/5/23
 */
public class LevelOrderBottom {

    /**
     * BFS
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            res.addFirst(list);
            TreeNode node;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}
