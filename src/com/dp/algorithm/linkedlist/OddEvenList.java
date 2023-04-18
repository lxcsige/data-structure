package com.dp.algorithm.linkedlist;

/**
 * leetcode_328_奇偶链表_中等
 *
 * reviewed at 2023.04.14
 *
 * @author liuxucheng
 * @since 2023/2/7
 */
public class OddEvenList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        // even == null对应奇数情况
        // even.next == null对应偶数情况
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
