package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * leetcode_503_下一个更大元素II_中等
 */
public class NextGreaterElementII {

    /**
     * 单调栈 + 循环数组
     * 处理循环：[2,3,1] -> [2,3,1,2,3,1]
     * 优化：通过取模代替
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        // 非严格单调递减栈
        Deque<Integer> stack = new ArrayDeque<>();
        // 处理循环数组
        for (int i = 0; i < 2 * n - 1; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }
}
