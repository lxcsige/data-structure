package com.dp.algorithm.linkedlist;

import java.util.Stack;

/**
 *  双向链表 + 先根遍历
 *
 * @author xucheng.liu
 * @date 2021/3/5
 */
public class Flatten {

    class Node {
        int val;
        Node prev;
        Node next;
        Node child;

        public Node() {}

        public Node(int val,Node prev,Node next,Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }

    public static void main(String[] args) {

    }

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node dummyHead = new Node(-1, null, head, null);
        dfsRecursively(dummyHead, head);
        dummyHead.next.prev = null;
        return dummyHead.next;
    }

    private Node dfsRecursively(Node prev, Node cur) {
        if (cur == null) {
            return prev;
        }
        cur.prev = prev;
        prev.next = cur;

        // cur.next在后面的递归中会变化
        Node temp = cur.next;
        Node tail = dfsRecursively(cur, cur.child);
        cur.child = null;

        return dfsRecursively(tail, temp);
    }

    public Node flatten2(Node head) {
        if (head == null) {
            return null;
        }

        Node dummyHead = new Node(-1, null, head, null);
        Node prev = dummyHead;
        Node cur;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            cur = stack.pop();
            cur.prev = prev;
            prev.next = cur;
            prev = prev.next;

            if (cur.next != null) {
                stack.push(cur.next);
            }
            if (cur.child != null) {
                stack.push(cur.child);
                cur.child = null;
            }
        }

        dummyHead.next.prev = null;
        return dummyHead.next;
    }
}
