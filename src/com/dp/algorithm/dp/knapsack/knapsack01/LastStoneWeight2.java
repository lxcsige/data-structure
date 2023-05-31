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
     * 价值和成本均为stones[i - 1]
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

    /**
     * 状态压缩
     * dp[j]表示容量为j的背包，最多可以背的最大重量
     * dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i])
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        // 初始化边界
        // dp[0] = 0;
        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[target];
    }
}
