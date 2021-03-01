package com.dp.algorithm.linkedlist;

/**
 * 相交链表
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class IntersectionNode {

    public static void main(String[] args) {

    }

    /**
     * 双指针，殊途同归
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
