package com.dp.algorithm.linkedlist;

/**
 * 环形链表
 *
 * @author xucheng.liu
 * @date 2021/3/1
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
    public static ListNode hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next  == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
           slow = slow.next;
           fast = fast.next;
        }
        return slow;
    }
}
