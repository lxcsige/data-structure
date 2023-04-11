package com.dp.algorithm.linkedlist.sort;

import com.dp.algorithm.linkedlist.ListNode;

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
        // 边界
        if (head == null || head.next == null) {
            return head;
        }
        // 链表长度
        int len = 0;
        ListNode dummy = new ListNode(-1, head), cur = head, prev;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        // 自底向上迭代，两两合并
        for (int subLen = 1; subLen < len; subLen = subLen * 2) {
            // 重置指针
            prev = dummy;
            // 注意，这里不能重置为head，经过一轮排序之后head可能已经不是首节点了
            cur = dummy.next;
            while (cur != null) {
                // 第一个子链表的head
                ListNode l1 = cur;
                // 遍历到第一个子链表的最后一个节点
                // 注意边界，最后一个子链表可能不足subLen个节点
                for (int i = 1; i < subLen && cur.next != null; i++) {
                    cur = cur.next;
                }
                // 第二个子链表的head，可能为null
                ListNode l2 = cur.next;
                // 断开2个子链表之间的连接
                cur.next = null;
                cur = l2;
                // cur可能为null，因此要多加一步判断
                for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }
                // 下一个分组的head
                ListNode next = null;
                // 如果第二个子链表不为空
                if (cur != null) {
                    next = cur.next;
                    // 断开与后继节点的连接
                    cur.next = null;
                }
                cur = next;
                // 合并两个子链表并恢复连接
                prev.next = mergeTwoLists(l1, l2);
                // 找到下一组子链表的prev节点
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
        return dummy.next;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
}
