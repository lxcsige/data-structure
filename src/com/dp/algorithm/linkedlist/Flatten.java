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
        dfs(head);
        return head;
    }

    /**
     * 深度优先遍历，返回尾节点
     *
     * @param head
     * @return
     */
    private Node dfs(Node head) {
        Node tail = head, cur = head, next, childTail;
        while (cur != null) {
            next = cur.next;
            // 没有child节点，继续向后遍历即可
            if (cur.child == null) {
                tail = cur;
            } else {
                // 有child节点，递归遍历
                childTail = dfs(cur.child);
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
                // next可能为空
                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }
                tail = childTail;
            }
            cur = next;
        }
        return tail;
    }

    /**
     * 迭代
     *
     * @param head
     * @return
     */
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
