package com.dp.algorithm.linkedlist;

/**
 * 复制随机指针链表
 *
 * @author xucheng.liu
 * @date 2021/3/5
 */
public class CopyRandomList {

    public static void main(String[] args) {

    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        return null;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
