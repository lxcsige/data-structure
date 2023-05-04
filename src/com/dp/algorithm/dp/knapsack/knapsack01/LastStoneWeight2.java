package com.dp.algorithm.dp.knapsack.knapsack01;

/**
 * leetcode_1049_最后一块石头的重量II_中等
 *
 * @author liuxucheng
 * @since 2023/5/4
 */
public class LastStoneWeight2 {

    /**
     * 转化为01背包问题
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-stones[i-1]] + stones[i-1])
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int target = sum / 2;
        int n = stones.length;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (stones[i - 1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-stones[i-1]] + stones[i-1]);
                }
            }
        }
        return sum - 2 * dp[n][target];
    }

    public int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int target = sum / 2;
        int n = stones.length;
        int[] dp = new int[target + 1];
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }
}
