package com.dp.algorithm.linkedlist.cycle.reviewed;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_61_旋转链表_中等
 *
 * @author liuxucheng
 * @since 2021/3/6
 */
public class RotateRight {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(2);
        ListNode forth = new ListNode(3);
        head.next = second;
        second.next = third;
        third.next = forth;

        rotateRight(head, 1);
    }

    private static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int len = 1;
        // 成环
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        cur.next = head;

        // head指针右移len - k % len次
        int times = len - k % len;
        while (times-- > 0) {
            head = head.next;
        }

        // 断开环
        cur = head;
        while (cur.next != head) {
            cur = cur.next;
        }
        cur.next = null;

        return head;
    }
}
