package com.dp.algorithm.linkedlist;

/**
 * 反转单链表
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class ReverseList {

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
}
