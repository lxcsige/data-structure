package com.dp.algorithm.binarytree;

import java.util.*;

/**
 * leetcode_590_N叉树的后序遍历_简单
 *
 * @author xucheng.liu
 * @since 2022/5/20
 */
public class PostorderN {

    /**
     * 迭代，按照后序遍历递归的入栈顺序
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new LinkedList<>();
        // 记录当前遍历到node节点的哪个子节点
        Map<Node, Integer> node2childIndexMap = new HashMap<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                List<Node> children = cur.children;
                // 将左起第一个子节点入栈
                if (children != null && !children.isEmpty()) {
                    // 下一次循环会遍历到cur的children中的第1个节点
                    node2childIndexMap.put(cur, 0);
                    cur = children.get(0);
                } else {
                    cur = null;
                }
            }
            cur = stack.peek();
            // cur的children可能为空，因此map中不存在对应的kv
            int index = node2childIndexMap.getOrDefault(cur, -1) + 1;
            List<Node> children = cur.children;
            // 没有子节点或子节点已经全部遍历完
            if (children == null || index >= children.size()) {
                // cur节点出栈
                stack.pop();
                res.add(cur.val);
                // 这步可以省略
                node2childIndexMap.remove(cur);
                cur = null;
            } else {
                // 更新cur对应的index
                node2childIndexMap.put(cur, index);
                cur = cur.children.get(index);
            }
        }

        return res;
    }

    /**
     * 迭代-visited辅助集合
     *
     * @return
     */
    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new LinkedList<>();
        // 子节点已经全部遍历的节点列表
        Set<Node> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.peek();
            // 没有子节点或子节点已全部入栈
            if ((cur.children == null || cur.children.isEmpty())
                    || visited.contains(cur)) {
                stack.pop();
                res.add(cur.val);
                continue;
            }
            // 从右到左入栈，出栈的顺序可以保持为从左到右
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                stack.push(cur.children.get(i));
            }
            visited.add(cur);
        }

        return res;
    }
}
