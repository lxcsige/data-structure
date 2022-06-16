package com.dp.algorithm.binarytree;

import java.util.*;

/**
 * leetcode_N叉树的前序遍历_简单
 *
 * @author xucheng.liu
 * @since 2022/5/25
 */
public class PreorderN {

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        if (root.children == null || root.children.isEmpty()) {
            return;
        }
        for (Node node : root.children) {
            dfs(node, res);
        }
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.add(cur.val);
            if (cur.children == null || cur.children.isEmpty()) {
                continue;
            }
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                stack.push(cur.children.get(i));
            }
        }
        return res;
    }

    /**
     * 迭代-模拟递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> indexMap = new HashMap<>();
        Deque<Node> stack = new LinkedList<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                res.add(cur.val);
                if (cur.children == null || cur.children.isEmpty()) {
                    cur = null;
                } else {
                    indexMap.put(cur, 0);
                    cur = cur.children.get(0);
                }
            }
            cur = stack.peek();
            int nextIndex = indexMap.getOrDefault(cur, -1) + 1;
            // 没有子节点或子节点已经全部遍历完了
            if (cur.children == null || nextIndex >= cur.children.size()) {
                indexMap.remove(cur);
                stack.pop();
                cur = null;
            } else {
                indexMap.put(cur, nextIndex);
                cur = cur.children.get(nextIndex);
            }
        }
        return res;
    }
}
