package com.dp.algorithm.greedy.interval;

import java.util.Arrays;

/**
 * 435_无重叠区间
 *
 * @author liuxucheng
 * @since 2021/6/19
 */
public class EraseOverlapIntervals {

    public static void main(String[] args) {

    }

    /**
     * 基本思想：贪心，前一个区间的右边界尽可能小
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 按照右边界排序
        Arrays.sort(intervals, (i1, i2) -> i1[1]- i2[1]);

        // 无重叠区间数量，至少是1
        int count = 1;
        // 无重叠区间的右边界
        int maxEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= maxEnd) {
                count++;
                maxEnd = intervals[i][1];
            }
        }

        return intervals.length - count;
    }

    /**
     * 动态规划，最长递增子序列
     *
     * @param intervals
     * @return
     */
    public  int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 按照左边界排序
        Arrays.sort(intervals, (i1, i2) -> i1[1]- i2[1]);

        int[] dp = new int[intervals.length];
        int max = 1;
        dp[0] = 1;
        // 最长递增子序列
        for (int i = 1; i < intervals.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return intervals.length - max;
    }
}
