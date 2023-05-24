package com.dp.algorithm.dp.stock;

/**
 * leetcode_309_最佳买卖股票时机含冷冻期_困难
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2022/3/8
 */
public class MaxProfit5 {

    /**
     * 股票买卖问题通用模板方法
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];

        // 初始状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            // 简单理解，当天持股，要么昨天持股今天休息，要么今天买入，但存在一天的冻结期，昨天卖出今天无法购买，因此跟状态dp[i-2][0]相关
            dp[i][1] = Math.max(dp[i-1][1], (i > 2 ? dp[i-2][0] : 0) - prices[i]);
        }

        return dp[prices.length-1][0];
    }

    /**
     * 状态压缩
     * 无论题目中是否允许「在同一天买入并且卖出」这一操作，最终的答案都不会受到影响，这是因为这一操作带来的收益为零
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int preProfit0 = 0;
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int newProfit0 = Math.max(profit0, profit1 + prices[i]);
            int newProfit1 = Math.max(profit1, preProfit0 - prices[i]);
            preProfit0 = profit0;
            profit0 = newProfit0;
            profit1 = newProfit1;
        }

        return profit0;
    }

    /**
     * 另一种dp的思路，dp[i][j]，i表示第几天，j表示0、1、2这三种状态
     * 0：当天结束时持股
     * 1：当天结束时不持股且后一天处于冻结期
     * 2：当天结束时不持股且后一天处于非冻结期
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int[][] dp = new int[prices.length][3];
        // 初始状态
        dp[0][0] = -prices[0];
        // 不存在这种情况，只有允许当天买入当天卖出才会出现
        dp[0][1] = 0;
        dp[0][2] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]);
        }

        return Math.max(dp[prices.length-1][1], dp[prices.length-1][2]);
    }

    /**
     * 状态压缩
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int profit0 = -prices[0];
        int profit1 = 0;
        int profit2 = 0;

        for (int i = 1; i < prices.length; i++) {
            int newProfit0 = Math.max(profit0, profit2 - prices[i]);
            int newProfit1 = profit0 + prices[i];
            int newProfit2 = Math.max(profit1, profit2);
            profit0 = newProfit0;
            profit1 = newProfit1;
            profit2 = newProfit2;
        }

        return Math.max(profit1, profit2);
    }
}
