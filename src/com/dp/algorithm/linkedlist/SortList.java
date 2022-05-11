package com.dp.algorithm.linkedlist;

/**
 * leetcode_148_排序链表_中等
 *
 * @author xucheng.liu
 * @since 2022/5/11
 */
public class SortList {

    public static void main(String[] args) {
        ListNode n1 =  new ListNode(4);
        ListNode n2 =  new ListNode(2);
        ListNode n3 =  new ListNode(1);
        ListNode n4 =  new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        SortList main = new SortList();
        main.sortList2(n1);
    }

    /**
     * 链表归并排序，时间复杂度O(nlogn)，空间复杂度O(logn)
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 没有节点或只有1个节点
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针，此时最少2个节点
        // 偶数个节点：找到中间左边的节点
        // 奇数个节点：找到中间节点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        // 注意，这里需要断开指针，否则无法判断是否只有一个节点
        slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(head2);
        return mergeLists(l1, l2);
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 获取链表长度
        int length = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            length++;
        }

        ListNode dummy = new ListNode(-1, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummy, cur = dummy.next;
            while (cur != null) {
                // 两两合并
                // 第一个分组的头节点
                ListNode head1 = cur;
                for (int i = 1; i < subLength && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode head2 = cur.next;
                // 断开分组之间的连接
                cur.next = null;
                // 此时cur可能为null
                cur = head2;
                for (int i = 1; i < subLength && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }
                // 保存下一个分组的头节点
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    cur.next = null;
                }
                cur = next;
                // 两两合并
                ListNode newHead = mergeLists(head1, head2);
                prev.next = newHead;
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
        return dummy.next;
    }
}
