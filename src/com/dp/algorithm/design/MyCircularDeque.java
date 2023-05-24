package com.dp.algorithm.design;

public class MyCircularDeque {

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

    public MyCircularDeque(int k) {
        capacity = k;
        nums = new int[capacity];
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        nums[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        nums[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return nums[front];
    }

    public int getRear() {
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
