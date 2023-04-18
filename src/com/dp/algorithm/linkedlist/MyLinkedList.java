package com.dp.algorithm.linkedlist;

/**
 * leetcode_707_设计链表_中等
 *
 * reviewed at 2023.04.14
 *
 * @author xucheng.liu
 * @date 2021/2/28
 */
public class MyLinkedList {

    /**
     * dummy头节点
     */
    private final ListNode head;

    /**
     * 元素数量
     */
    private int size;

    public MyLinkedList() {
        head = new ListNode(-1);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode p = head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
            if (p == null) {
                return -1;
            }
        }
        return p.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
        }
        ListNode newNode = new ListNode(val);
        p.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        // index == size时插入到尾节点后面
        if (index > size || index < 0) {
            return;
        }
        // 前驱节点
        ListNode p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = p.next;
        p.next = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        // index必须小于size
        if (index >= size || index < 0) {
            return;
        }
        // 前驱节点
        ListNode p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        p.next = p.next.next;
        size--;
    }

    private static class ListNode {

        private int val;

        private ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}