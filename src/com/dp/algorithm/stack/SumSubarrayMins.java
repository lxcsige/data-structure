package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_907_子数组的最小值之和_中等
 */
public class SumSubarrayMins {

    public int sumSubarrayMins(int[] arr) {
        int[] newArr = new int[arr.length + 2];
        newArr[0] = 0;
        newArr[newArr.length - 1] = 0;
        for (int i = 0; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }
        arr = newArr;
        long res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int peek = stack.pop();
                res = (res + (long) arr[peek] * (peek - stack.peek()) * (i - peek)) % 1000000007;
            }
            stack.push(i);
        }
        return (int)res;
    }

    public int sumSubarrayMins2(int[] arr) {
        int n = arr.length;
        int[] l = new int[n];
        int[] r = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // 找到左边第一个小于它的元素
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            l[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        // 找到右边第一个小于它的元素
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            r[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + (long) arr[i] * (i - l[i]) * (r[i] - i)) % 1000000007;
        }
        return (int)res;
    }
}
