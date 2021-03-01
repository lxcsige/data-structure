package com.dp.algorithm.linkedlist;

/**
 * 删除链表元素
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class RemoveElements {

    public static void main(String[] args) {

    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                cur = cur.next;
            } else {
                prev = prev.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
