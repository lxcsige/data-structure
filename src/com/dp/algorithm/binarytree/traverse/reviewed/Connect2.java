package com.dp.algorithm.binarytree.traverse.reviewed;

/**
 * leetcode_117_填充每个节点的下一个右侧节点指针2_中等
 *
 * 普通二叉树
 *
 * @author xucheng.liu
 * @since 2022/5/26
 */
public class Connect2 {

    /**
     * 迭代，空间复杂度O(1)
     *
     * 相对于满二叉树，当前问题在于：
     * 1. start.left不一定存在，无法确定下一层左起第一个节点 -> 引入哨兵节点，start = dummy.next
     * 2. cur.left或cur.right都不一定存在 -> 暂存前驱节点
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
            // 没有这句会导致死循环
            // 考虑start指向最后一层左起第一个节点，由于该层所有节点都没有子节点，因此遍历完之后dummy.next仍然指向start
            dummy.next = null;
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
