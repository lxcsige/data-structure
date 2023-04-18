package com.dp.algorithm.linkedlist.copy.reviewed;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_138_复制带随机指针的链表_中等
 *
 * @author xucheng.liu
 * @date 2021/3/5
 */
public class CopyRandomList {

    public static void main(String[] args) {

    }

    private Map<Node, Node> map = new HashMap<>();

    /**
     * 递归+备忘录
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (!map.containsKey(head)) {
            Node newHead = new Node(head.val);
            map.put(head, newHead);
            newHead.next = copyRandomList(head.next);
            newHead.random = copyRandomList(head.random);
        }

        return map.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            Node newCur = new Node(cur.val);
            map.put(cur, newCur);
            cur = cur.next;
        }

        // 设置next和random
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
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
