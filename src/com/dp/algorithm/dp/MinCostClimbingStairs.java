package com.dp.algorithm.dp;

/**
 * leetcode_746_使用最小花费爬楼梯_简单
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2021/6/12
 */
public class MinCostClimbingStairs {

    /**
     * dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        // 0和1没有意义，直接从2开始
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
