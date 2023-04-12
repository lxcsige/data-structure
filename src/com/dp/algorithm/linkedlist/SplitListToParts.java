package com.dp.algorithm.linkedlist;

/**
 * leetcode_725_分隔链表_中等
 *
 * @author liuxucheng
 * @since 2023/2/8
 */
public class SplitListToParts {

    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        // 可能为0
        int subLen = len / k;
        // 余数
        int remainder = len % k;

        ListNode[] arr = new ListNode[k];
        cur = head;
        ListNode newHead = head;
        for (int i = 0; i < k; i++) {
            if (cur == null) {
                break;
            }
            // 计算子链表长度
            int realLen = subLen + (i < remainder ? 1 : 0);
            for (int j = 1; j < realLen; j++) {
                cur = cur.next;
            }
            arr[i] = newHead;
            // 更新为下一个子链表的头
            newHead = cur.next;
            // 断开next指针
            cur.next = null;
            cur = newHead;
        }
        return arr;
    }
}
