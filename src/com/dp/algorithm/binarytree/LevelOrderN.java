package com.dp.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode_429_N叉树的层序遍历_中等
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
     * 递归
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
