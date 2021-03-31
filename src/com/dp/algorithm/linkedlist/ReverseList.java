package com.dp.algorithm.linkedlist;

/**
 * 反转单链表
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class ReverseList {

    ListNode successor;

    public static void main(String[] args) {

    }

    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = prev;
            prev =cur;
            cur = temp;
        }
        return prev;
    }

    /**
     * 递归
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

    /**
     * 反转链表的[m,n]之间的节点，索引从1开始
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    private ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }

        head.next = reverseBetween(head.next, m - 1, n - 1);

        return head;
    }
}
