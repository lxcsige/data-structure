package com.dp.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_剑指offer 59_队列的最大值_中等
 *
 * @author liuxucheng
 * @since 2023/4/18
 */
public class MaxQueue {

    private Deque<Integer> queue;

    private Deque<Integer> deque;

    public MaxQueue() {
        queue = new ArrayDeque<>();
        deque = new ArrayDeque<>();
    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.offer(value);
    }

    /**
     * 每个数字只会出队一次，因此对于所有的n个数字的插入过程，对应的所有出队操作也不会大于n次，将出队的时间均摊到每个插入操作上，时间复杂度为O(1)
     */
    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int res = queue.poll();
        if (res == deque.peek()) {
            deque.poll();
        }
        return res;
    }
}
