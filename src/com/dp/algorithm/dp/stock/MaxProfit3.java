package com.dp.algorithm.dp.stock;

/**
 * leetcode_123_买卖股票的最佳时机3_困难
 * 最多只能交易2次，不能同时进行多笔交易
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2022/3/6
 */
public class MaxProfit3 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1}));
    }

    /**
     * 动态规划，股票买卖问题通用解法
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        // dp[i][j][k]表示第i天最多买入j次的最大收益
        // k=0表示当天未持股，k=1表示当天持股
        int[][][] dp = new int[prices.length][3][2];

        // 初始状态
        dp[0][1][1] = -prices[0];
        dp[0][2][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i-1][1][1], -prices[i]);
            dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);
        }

        return dp[prices.length - 1][2][0];
    }

    /**
     * 仅适用于本题，将当天是否持股和买进次数2个状态进行了合并
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][4];
        // 初始边界
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return dp[n - 1][3];
    }

    /**
     * 状态压缩，难理解，不推荐
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        // 最多进行1次买入的最大利润
        int buy1 = -prices[0];
        // 最多进行1次买入和1次卖出的最大利润
        int sell1 = 0;
        // 最多进行2次买入和1次卖出的最大利润
        int buy2 = -prices[0];
        // 最多进行2次买入和2次卖出的最大利润
        int sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy1 = Math.max(buy1, -prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
        }

        return sell2;
    }
}
