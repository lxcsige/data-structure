package com.dp.algorithm.linkedlist;

/**
 * leetcode_147_对链表进行插入排序_中等
 *
 * @author xucheng.liu
 * @since 2022/5/11
 */
public class InsertionSortList {

    public static void main(String[] args) {

    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        // 有序部分的尾节点
        ListNode p = head;
        // 遍历指针
        ListNode cur = head.next;
        while (cur != null) {
            // 当前遍历的节点值 >= 有序部分的最大值，尾节点右移
            if (cur.val >= p.val) {
                p = p.next;
            } else {
                // 否则将该节点插入到前面的有序部分
                ListNode prev = dummy;
                while (prev.next.val <= cur.val) {
                    prev = prev.next;
                }
                // 将cur从链表中移除
                p.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = p.next;
        }

        return dummy.next;
    }
}
