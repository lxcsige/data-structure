package com.dp.algorithm.dp.stock;

/**
 * @author liuxucheng
 * @since 2022/3/7
 */
public class MaxProfit4 {

    public static void main(String[] args) {

    }

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }

        // 由于不允许在同一天进行买入和卖出，因此一次交易最少需要2天，这里可以对k进行优化
        k = Math.min(k, prices.length / 2);

        int[][][] dp = new int[prices.length][k+1][2];

        // 初始状态
        for (int i = 1; i <= k; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }

        return dp[prices.length-1][k][0];
    }
}
