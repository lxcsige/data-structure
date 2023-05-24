package com.dp.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_1438_绝对差不超过限制的最长连续子数组_中等
 */
public class LongestSubarray {

    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length, res = 0;
        // 单调递减栈
        Deque<Integer> max = new ArrayDeque<>();
        // 单调递增栈
        Deque<Integer> min = new ArrayDeque<>();
        for (int i = 0, j = 0; j < n; j++) {
            while (!max.isEmpty() && max.peekLast() < nums[j]) {
                max.pollLast();
            }
            max.offerLast(nums[j]);
            while (!min.isEmpty() && min.peekLast() > nums[j]) {
                min.pollLast();
            }
            min.offerLast(nums[j]);
            while (max.peek() - min.peek() > limit && i <= j) {
                if (max.peek() == nums[i]) {
                    max.poll();
                }
                if (min.peek() == nums[i]) {
                    min.poll();
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
