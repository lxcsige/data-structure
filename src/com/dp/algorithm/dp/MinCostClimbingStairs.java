package com.dp.algorithm.dp;

/**
 * 746_使用最小花费爬楼梯
 * 难以理解题意
 *
 * @author liuxucheng
 * @since 2021/6/12
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {

    }

    /**
     * dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[cost.length];
    }
}
