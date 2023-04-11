package com.dp.algorithm.linkedlist.cycle;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_142_环形链表II_中等
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class DetectCycle {

    public static void main(String[] args) {
        ListNode n1 =  new ListNode(3);
        ListNode n2 =  new ListNode(2);
        ListNode n3 =  new ListNode(0);
        ListNode n4 =  new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
        DetectCycle main = new DetectCycle();
        main.detectCycle(n1);
    }

    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        fast = head;
        while (slow != fast) {
           slow = slow.next;
           fast = fast.next;
        }
        return slow;
    }
}
