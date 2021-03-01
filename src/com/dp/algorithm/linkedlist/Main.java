package com.dp.algorithm.linkedlist;

/**
 * @author xucheng.liu
 * @date 2021/2/28
 */
public class Main {

    public static void main(String[] args) {

    }

    /**
     * 相交链表，双指针
     *
     * @param headA
     * @param headB
     * @return
     */
    public static Node getIntersectionNode(Node headA, Node headB) {
        Node p = headA;
        Node q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
