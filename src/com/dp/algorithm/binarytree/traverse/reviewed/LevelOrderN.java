package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode_429_N叉树的层序遍历_中等
 *
 * reviewed at 2023.04.20
 *
 * @author xucheng.liu
 * @since 2022/5/25
 */
public class LevelOrderN {

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            res.add(level);
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                level.add(cur.val);
                for (Node node : cur.children) {
                    queue.offer(node);
                }
            }
        }
        return res;
    }

    /**
     * DFS，用DFS实现层次遍历的关键在于level
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }

    private void dfs(Node root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        // level层的第一个节点，需要新建该层的结果列表
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        if (root.children == null || root.children.isEmpty()) {
            return;
        }
        for (Node child : root.children) {
            dfs(child, level + 1, res);
        }
    }
}
