package com.dp.algorithm.linkedlist;

/**
 * @author xucheng.liu
 * @date 2021/2/28
 */
public class MyLinkedList<E> {

    private Node<E> head;

    public MyLinkedList() {

    }

    public MyLinkedList(Node head) {
        this.head = head;
    }

    public E get(int index) {
        if (head == null) {
            return null;
        }
        Node<E> p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
            if (p == null) {
                return null;
            }
        }
        return p.val;
    }

    public void addAtHead(E val) {
        head = new Node<>(val, head);
    }

    public void addAtTail(E val) {
        if (head == null) {
            head = new Node<>(val, null);
            return;
        }
        Node<E> p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node<>(val, null);
    }

    public void addAtIndex(int index, E val) {
        if (index < 0) {
            return;
        }
        Node<E> dummy = new Node<>(null, head);
        Node<E> p = dummy;
        for (int i = 0; i < index; i++) {
            p = p.next;
            if (p == null) {
                return;
            }
        }
        p.next = new Node<>(val, p.next);
        head = dummy.next;
    }

    public void deleteAtIndex(int index) {
        if (index < 0) {
            return;
        }
        Node<E> dummy = new Node<>(null, head);
        Node<E> p = dummy;
        for (int i = 0; i < index; i++) {
            p = p.next;
            if (p == null) {
                return;
            }
        }
        if (p.next == null) {
            return;
        }
        p.next = p.next.next;
        head = dummy.next;
    }

    /**
     * 环形链表
     *
     * @return
     */
    public Node hasCycle() {
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
