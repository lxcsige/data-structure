package com.dp.algorithm.linkedlist;

/**
 * 删除倒数第K个元素
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        ListNode q = dummy;
        while (n-- > 0) {
            q = q.next;
            if (q == null) {
                return null;
            }
        }
        while(q.next != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return dummy.next;
    }
}
