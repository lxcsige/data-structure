package com.dp.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_862_和至少为k的最短子数组_困难
 */
public class ShortestSubArray {

    /**
     * 整体思路：前缀和+单调队列
     * 1. j < i，s[i] - s[j] >= k，对于i右边的元素来说，不可能得到一个比i - j更短的子数组，因此可以移除s[j]
     * 2. j < i，s[j] > s[i]，如果可以找到位置x，使得s[x] - s[j] >= k，那么s[x] - s[i]也一定大于k，同时i到x的子数组更短，因此s[j]也是可以直接移除的
     * 考虑到两端都需要移除元素，因此使用单调双端队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int shortestSubArray(int[] nums, int k) {
        // 单调递增队列，尾进头出
        Deque<Integer> deque = new ArrayDeque<>();
        int res = Integer.MAX_VALUE;
        int n = nums.length;
        // 前缀和数组
        long[] sum = new long[n + 1];
        // 计算前缀和
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        for (int i = 0; i <= n; i++) {
            // 如果队头和当前元素的前缀和之差大于等于k，那么可以移除队头元素
            while (!deque.isEmpty() && sum[i] >= sum[deque.peekFirst()] + k) {
                res = Math.min(res, i - deque.pollFirst());
            }
            // 如果队尾大于当前元素的前缀和，那么可以移除队尾元素
            while (!deque.isEmpty() && sum[i] <= sum[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return res;
    }
}
