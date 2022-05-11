package com.dp.algorithm.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU
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
    private Node head;

    // dummy tail
    private Node tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果存在则移动到双向链表头部，包含remove和add2Head两步操作
        // 节点可能在链表中间，使用双向链表效率更高，因此Node中包含prev指针
        move2Head(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        // 不存在该key
        if (node == null) {
            // 插入之前容量已满，删除尾部节点
            if (size == capacity) {
                // 删除尾节点
                Node tail = removeTail();
                // 同时需要从map中删除，因此node中需要包含key
                cache.remove(tail.key);
                size--;
            }
            node = new Node(key, value);
            cache.put(key, node);
            add2Head(node);
            size++;
        } else {
            // 修改节点的value
            node.value = value;
            // 将节点移动到双向链表头部
            move2Head(node);
        }
    }

    private void add2Head(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void move2Head(Node node) {
        removeNode(node);
        add2Head(node);
    }

    public static class Node {

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
