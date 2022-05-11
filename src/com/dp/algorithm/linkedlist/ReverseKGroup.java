package com.dp.algorithm.linkedlist;

/**
 * @author liuxucheng
 * @since 2021/3/9
 */
public class ReverseKGroup {

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
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // end指的是后面一个元素
        ListNode end = head;
        for (int i = 0; i < k; i++) {
            if (end == null) {
                return head;
            }
            end = end.next;
        }
        ListNode newHead = reverse(head, end);
        head.next = reverseKGroup(end, k);
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode end) {
        ListNode pre = end;
        ListNode cur = head;
        while (cur != end) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    public static ListNode reverseGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy, cur = head, next, knext = dummy;

        while (cur != null) {
            // 判断剩余节点个数
            for (int i = 0; i < k; i++) {
                knext = knext.next;
                // 不足k个，跳出循环
                if (knext == null) {
                    return dummy.next;
                }
            }
            // 翻转k个节点
            for (int i = 0; i < k-1; i++) {
                next = cur.next;
                cur.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = cur;
            cur = cur.next;
        }

        return dummy.next;
    }
}
