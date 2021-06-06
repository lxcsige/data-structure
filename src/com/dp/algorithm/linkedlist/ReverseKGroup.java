package com.dp.algorithm.linkedlist;

/**
 * @author liuxucheng
 * @since 2021/3/9
 */
public class ReverseKGroup {

    public static void main(String[] args) {

    }

    private ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // end指的是后面一个元素
        ListNode end = head;
        for (int i = 0; i < k; i++) {
            if (end == null) {
                return head;
            }
            end = end.next;
        }
        ListNode newHead = reverse(head, end);
        head.next = reverseKGroup(end, k);
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode end) {
        ListNode pre = end;
        ListNode cur = head;
        while (cur != end) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }
}
