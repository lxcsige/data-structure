package com.dp.algorithm.design;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_146_LRU缓存_中等
 *
 * @author liuxucheng
 * @since 2021/5/11
 */
public class LRUCache {

    private Map<Integer, Node> cache = new HashMap<>();

    /**
     * 当前数量
     */
    private int size;

    /**
     * 容量
     */
    private int capacity;

    // dummy head
    private Node head = new Node();

    // dummy tail
    private Node tail = new Node();

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果存在则移动到双向链表尾部，包含remove和add2Tail两步操作
        // 节点可能在链表中间，使用双向链表效率更高，因此Node中包含prev指针
        move2Tail(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        // 不存在该key，需要插入
        if (node == null) {
            // 插入之前容量已满，删除头部节点
            if (size == capacity) {
                // 删除头节点
                Node head = removeHead();
                // 同时需要从map中删除，因此node中需要包含key
                cache.remove(head.key);
                size--;
            }
            node = new Node(key, value);
            cache.put(key, node);
            add2Tail(node);
            size++;
        } else {
            // 修改节点的value
            node.value = value;
            // 将节点移动到双向链表尾部
            move2Tail(node);
        }
    }

    private void add2Tail(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    private Node removeHead() {
        Node res = head.next;
        removeNode(res);
        return res;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void move2Tail(Node node) {
        removeNode(node);
        add2Tail(node);
    }

    static class Node {

        private int key;

        private int value;

        private Node prev;

        private Node next;

        public Node() {

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
