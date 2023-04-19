package com.dp.algorithm.linkedlist.reverse.reviewed;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_206_反转链表_简单
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class ReverseList {

    public static void main(String[] args) {

    }

    /**
     * 头插
     *
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev =cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 尾插
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        // 终止条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = new ListNode(-1, head), next;
        while (head.next != null) {
            next = head.next;
            head.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return prev.next;
    }
}
