package com.dp.algorithm.binarytree;

/**
 * leetcode_117_填充每个节点的下一个右侧节点指针2_中等
 *
 * @author xucheng.liu
 * @since 2022/5/26
 */
public class Connect2 {

    /**
     * 迭代，空间复杂度O(1)
     *
     * @param root
     * @return
     */
    private Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node start = root, cur, dummy = new Node(-1), pre;
        while (start != null) {
            cur = start;
            pre = dummy;
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
