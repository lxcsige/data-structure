package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyStack {

    private Queue<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }

    /**
     * 时间复杂度O(n)
     *
     * @param x
     */
    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        // 先进后出
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
