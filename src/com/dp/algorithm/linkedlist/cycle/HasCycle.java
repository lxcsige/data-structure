package com.dp.algorithm.linkedlist.cycle;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_141_环形链表_简单
 *
 * @author xucheng.liu
 * @since 2022/5/11
 */
public class HasCycle {

    public static void main(String[] args) {

    }

    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head, fast = head;
        do {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        return true;
    }
}
