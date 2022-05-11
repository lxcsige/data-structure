package com.dp.algorithm.linkedlist;

/**
 * leetcode_92_反转链表II_中等
 *
 * @author xucheng.liu
 * @since 2022/5/7
 */
public class ReverseBetween {

    public static void main(String[] args) {
        ListNode n1 =  new ListNode(1);
        ListNode n2 =  new ListNode(2);
        ListNode n3 =  new ListNode(3);
        ListNode n4 =  new ListNode(4);
        ListNode n5 =  new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        reverseBetween(n1, 2, 4);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy, cur, next;
        // prev指针始终指向left前一个节点
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        cur = prev.next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            // 这里需要注意，next节点需要放到prev之后，其next不一定是cur节点
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }
}
