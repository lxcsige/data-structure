package com.dp.algorithm.linkedlist.merge.reviewed;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_21_合并两个有序链表_简单
 *
 * @author xucheng.liu
 * @date 2021/3/4
 */
public class MergeTwoLists {

    public static void main(String[] args) {

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
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
        return dummyHead.next;
    }
}
