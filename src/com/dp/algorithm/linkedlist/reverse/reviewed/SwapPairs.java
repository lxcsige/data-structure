package com.dp.algorithm.linkedlist.reverse.reviewed;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_24_两两交换链表中的节点_中等
 *
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy, cur = head, next;
        while (cur != null) {
            // 只剩下一个元素
            if (cur.next == null) {
                return dummy.next;
            }
            next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;

            prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
