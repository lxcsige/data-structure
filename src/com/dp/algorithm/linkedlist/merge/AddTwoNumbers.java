package com.dp.algorithm.linkedlist.merge;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_2_两数相加_中等
 *
 * @author xucheng.liu
 * @date 2021/3/4
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int p = l1 == null ? 0 : l1.val;
            int q = l2 == null ? 0 : l2.val;
            int sum = p + q + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
