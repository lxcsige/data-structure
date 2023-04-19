package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_155_最小栈_中等
 */
public class MinStack {

    private final Deque<Integer> stack;

    private final Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(val, minStack.peek()));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
