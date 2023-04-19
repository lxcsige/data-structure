package com.dp.algorithm.linkedlist;

/**
 * leetcode_160_相交链表_简单
 *
 * reviewed at 2023.04.14
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class IntersectionNode {

    /**
     * 双指针，殊途同归
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            // 考虑两个链表根本不相交的场景，这里不能用p.next == null代替p == null
            // 不相交的终止条件：p == null，q == null
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
