package com.dp.algorithm.design;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_460_LFU缓存_困难
 */
public class LFUCache {

    private Map<Integer, Node> cacheMap = new HashMap<>();

    private Map<Integer, LinkedList> freqMap = new HashMap<>();

    private int capacity;

    private int size;

    private int minFreq = 1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cacheMap.get(key);
        if (node == null) {
            return -1;
        }
        // 访问次数递增
        incrFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cacheMap.get(key);
        if (node == null) {
            // 淘汰
            if (size == capacity) {
                // 淘汰最小访问次数链表中的头节点
                LinkedList list = freqMap.get(minFreq);
                Node tmp = list.remove();
                cacheMap.remove(tmp.key);
                size--;
            }
            // 插入
            addNode(new Node(key, value));
        } else {
            // 更新
            node.value = value;
            // 访问次数递增
            incrFreq(node);
        }
    }

    private void incrFreq(Node node) {
        // 从原访问次数链表中删除
        LinkedList list = freqMap.get(node.freq);
        list.remove(node);
        // 更新最小访问次数
        if (minFreq == node.freq && list.isEmpty()) {
            minFreq = node.freq + 1;
        }
        // 递增访问次数
        node.freq++;
        // 添加到新访问次数链表
        list = freqMap.get(node.freq);
        if (list == null) {
            list = new LinkedList();
            freqMap.put(node.freq, list);
        }
        list.add(node);
    }

    private void addNode(Node node) {
        cacheMap.put(node.key, node);
        LinkedList list = freqMap.get(node.freq);
        if (list == null) {
            list = new LinkedList();
            freqMap.put(node.freq, list);
        }
        list.add(node);
        size++;
        // 新增节点之后，最小访问次数一定是1
        minFreq = 1;
    }

    static class LinkedList {

        Node head;

        Node tail;

        public LinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public void add(Node node) {
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
        }

        public Node remove() {
            if (isEmpty()) {
                return null;
            }
            Node node = head.next;
            remove(node);
            return node;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public boolean isEmpty() {
            return head.next == tail;
        }
    }

    static class Node {

        int key;

        int value;

        int freq = 1;

        Node prev;

        Node next;

        public Node() {

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
