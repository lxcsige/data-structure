package com.dp.algorithm.linkedlist.reverse;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_92_反转链表II_中等
 *
 * @author xucheng.liu
 * @since 2022/5/7
 */
public class ReverseBetween {

    ListNode successor;

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
        ReverseBetween test = new ReverseBetween();
        test.reverseBetween(n1, 2, 4);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy, cur, next;
        // prev指针始终指向left前一个节点
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        // cur指针始终不变
        cur = prev.next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }

    /**
     * 反转链表的[m,n]之间的节点，索引从1开始
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }

        // head节点不用反转
        head.next = reverseBetween2(head.next, m - 1, n - 1);

        return head;
    }

    /**
     * 反转链表的前n个节点
     * 递归，记录后驱节点
     *
     * @param head
     * @param n
     * @return
     */
    private ListNode reverseN(ListNode head, int n) {
        // 终止条件变了
        if (n == 1) {
            successor = head.next;
            return head;
        }

        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        // 头节点的next指向第n+1个节点
        head.next = successor;
        return newHead;
    }
}
