package com.dp.algorithm.linkedlist;

/**
 * leetcode_83_删除排序链表中的重复元素_简单
 *
 * @author liuxucheng
 * @since 2023/2/7
 */
public class DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            // 相同
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                //  不同
                cur = cur.next;
            }
        }

        return head;
    }
}
