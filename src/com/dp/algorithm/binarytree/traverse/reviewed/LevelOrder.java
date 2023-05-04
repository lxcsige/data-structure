package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode_102_二叉树的层次遍历_中等
 *
 * reviewed at 2023.04.19
 *
 * @author xucheng.liu
 * @since 2022/5/12
 */
public class LevelOrder {

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            res.add(list);
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

    /**
     * 递归，DFS
     * DFS并不是按照层次遍历，因此递归时需要带上当前节点的深度
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        // 当前level的节点列表在结果中不存在
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);

        dfs(root.left, level + 1, res);
        dfs(root.right, level + 1, res);
    }
}
