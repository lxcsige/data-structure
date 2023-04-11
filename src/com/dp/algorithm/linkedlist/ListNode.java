package com.dp.algorithm.linkedlist;

/**
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class ListNode {

    public int val;

    public ListNode next;

    ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
