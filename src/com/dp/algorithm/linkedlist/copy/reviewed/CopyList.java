package com.dp.algorithm.linkedlist.copy.reviewed;

import com.dp.algorithm.linkedlist.ListNode;

import java.util.HashMap;

/**
 * @author liuxucheng
 * @since 2021/3/6
 */
public class CopyList {

    HashMap<Node, Node> visitedNodeMap = new HashMap<>();

    static class Node {
        int val;
        Node next;
        Node random;

        public Node() {

        }

        public Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(2);
        ListNode forth = new ListNode(3);
        head.next = second;
        second.next = third;
        third.next = forth;
        ListNode newHead = copyList(head);
    }

    private static ListNode copyList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new ListNode(head.val, null);
        }

        ListNode newHead = new ListNode(head.val);
        newHead.next = copyList(head.next);

        return newHead;
    }

    /**
     * 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param head
     * @return
     */
    private Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (this.visitedNodeMap.containsKey(head)) {
            return visitedNodeMap.get(head);
        }

        Node newHead = new Node(head.val, null, null);
        visitedNodeMap.put(head, newHead);

        newHead.next = this.copyRandomList(head.next);
        newHead.random = this.copyRandomList(head.random);

        return newHead;
    }

    /**
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param head
     * @return
     */
    private Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        // 将拷贝节点织进原链表
        Node cur = head;
        while(cur != null) {
            Node copiedNode = new Node(cur.val, cur.next, null);
            cur.next = copiedNode;
            cur = copiedNode.next;
        }

        // 为拷贝节点的random指针赋值
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 将原链表恢复原状，并为拷贝节点的next指针赋值
        cur = head;
        Node copied = head.next;
        Node newHead = head.next;
        while (cur != null) {
            cur.next = cur.next.next;
            copied.next = copied.next == null ? null : copied.next.next;
            cur = cur.next;
            copied = copied.next;
        }

        return newHead;
    }
}
