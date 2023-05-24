package com.dp.algorithm.design;

/**
 * leetcode_622_设计循环队列_中等
 *
 * 考虑：
 * 1. 如何区分队列已满和队列为空 -> 1.浪费一个位置的空间；2.维护元素个数size
 * 2. 注意index越界，需要进行取模运算
 */
public class MyCircularQueue {

    private int[] nums;

    private int capacity;

    /**
     * 头节点index
     */
    private int front;

    /**
     * 尾节点下一个位置的index，即下一个插入位置
     */
    private int rear;

    private int size;

    public MyCircularQueue(int k) {
        capacity = k;
        nums = new int[capacity];
    }

    /**
     * 尾进
     *
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        nums[rear] = value;
        size++;
        rear = (rear + 1) % capacity;
        return true;
    }

    /**
     * 头出
     *
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return nums[front];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return nums[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
