package com.dp.algorithm.linkedlist.sort;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_143_重排链表_中等
 *
 * @author xucheng.liu
 * @since 2022/5/11
 */
public class ReOrderList {

    public static void main(String[] args) {
        ListNode n1 =  new ListNode(1);
        ListNode n2 =  new ListNode(2);
        ListNode n3 =  new ListNode(3);
        ListNode n4 =  new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        ReOrderList main = new ReOrderList();
        main.reorderList(n1);
    }

    public void reorderList(ListNode head) {
        // 快慢指针找到中间节点
        // 注意p是后半段链表的prev，而不是head
        ListNode p = head, q = head;
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }

        ListNode next = p.next;
        p.next = null;

        // 翻转后半部分链表
        ListNode newHead = reverse(next);

        // 合并前半部分和后半部分链表
        ListNode m = head, n = newHead, mNext, nNext;
        while (n != null) {
            mNext = m.next;
            m.next = n;
            nNext = n.next;
            n.next = mNext;
            m = mNext;
            n = nNext;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
