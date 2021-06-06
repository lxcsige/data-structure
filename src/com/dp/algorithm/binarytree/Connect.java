package com.dp.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完美二叉树，连接相邻节点
 *
 * @author liuxucheng
 * @since 2021/3/19
 */
public class Connect {

    public static void main(String[] args) {

    }

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
                Node node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                }
                pre = node;

                if (pre.next != null) {
                    queue.offer(pre.next);
                }
                if (pre.right != null) {
                    queue.offer(pre.right);
                }
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
     * 非完美二叉树，迭代，O(1)空间
     *
     * @param root
     * @return
     */
    private Node connect4(Node root) {
        if (root == null) {
            return null;
        }

        Node start = root, cur;
        while (start != null) {
            cur = start;
            Node dummy = new Node(-1);
            Node pre = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            start = dummy.next;
        }

        return root;
    }

    class Node {

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
