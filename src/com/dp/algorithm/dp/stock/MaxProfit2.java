package com.dp.algorithm.dp.stock;

/**
 * leetcode_122_买卖股票的最佳时机2_中等
 * 不限制交易次数，但不能同时进行多笔交易
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2022/3/4
 */
public class MaxProfit2 {

    /**
     * DP，由于第i天的决策依赖于第i-1天，所以把约束条件--「是否持股」加入到状态参数中
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[prices.length-1][0];
    }

    /**
     * 状态压缩
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }

        return dp0;
    }

    /**
     * 贪心算法
     * sum(max(0, prices[i] - prices[i-1]))
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(0, prices[i] - prices[i-1]);
        }
        return res;
    }
}
