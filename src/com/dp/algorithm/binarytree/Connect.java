package com.dp.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode_116_填充每个节点的下一个右侧节点指针_中等
 *
 * @author liuxucheng
 * @since 2021/3/19
 */
public class Connect {

    /**
     * 递归
     *
     * @param root
     * @return
     */
    private Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connectTwo(root.left, root.right);
        return root;
    }

    private void connectTwo(Node left, Node right) {
        if (left == null || right == null) {
            return;
        }

        left.next = right;
        connectTwo(left.left, left.right);
        connectTwo(left.right, right.left);
        connectTwo(right.left, right.right);
    }

    /**
     * BFS
     *
     * @param root
     */
    private Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelCount = queue.size();
            Node pre = null;
            for (int i = 0; i < levelCount; i++) {
                Node cur = queue.poll();
                if (pre != null) {
                    pre.next = cur;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                pre = cur;
            }
        }
        return root;
    }

    /**
     * 迭代，O(1)空间
     *
     * @param root
     * @return
     */
    private Node connect3(Node root) {
        if (root == null) {
            return null;
        }

        Node start = root, cur;
        while (start.left != null) {
            cur = start;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            start = start.left;
        }
        return root;
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public Node connect4(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
            root.right.next = root.next == null ? null : root.next.left;
            connect4(root.left);
            connect4(root.right);
        }
        return root;
    }

    private class Node {

        int val;
        Node left;
        Node right;
        Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
