package com.dp.algorithm.prefixsum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_1124_表现良好的最长时间段_中等
 */
public class LongestWPI {

    /**
     * 前缀和+单调栈
     *
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] s = new int[n + 1];
        // 单调递减栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        // 计算前缀和并入栈
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (s[i] < s[stack.peek()]) {
                stack.push(i);
            }
        }
        int res = 0;
        for (int i = n; i > 0; i--) {
            while (!stack.isEmpty() && s[i] > s[stack.peek()]) {
                res = Math.max(res, i - stack.pop());
            }
        }
        return res;
    }

    /**
     * 前缀和+哈希表
     * 1. s[i] > 0
     * 2. s[i] <= 0，由于前缀和是连续的，并且从0开始，那么左边界就是s[i] - 1首次出现的位置
     * 为什么不是s[i] - 2？从0降到s[i] - 2必然经过s[i] - 1，因此s[i] - 1必然在最左边，得到的区间一定是最长的
     *
     * @param hours
     * @return
     */
    public int longestWPI2(int[] hours) {
        Map<Integer, Integer> sum2IdxMap = new HashMap<>();
        int res = 0, prefixSum = 0, n = hours.length;
        for (int i = 1; i <= n; i++) {
            prefixSum += hours[i - 1] > 8 ? 1 : -1;
            if (prefixSum > 0) {
                res = i;
            } else if (sum2IdxMap.containsKey(prefixSum - 1)) {
                res = Math.max(res, i - sum2IdxMap.get(prefixSum - 1));
            }
            if (!sum2IdxMap.containsKey(prefixSum)) {
                sum2IdxMap.put(prefixSum, i);
            }
        }
        return res;
    }
}
