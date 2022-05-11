package com.dp.algorithm.dp.stock;

/**
 * 121_买卖股票的最佳时机
 *
 * @author liuxucheng
 * @since 2022/2/27
 */
public class MaxProfit {

    public static void main(String[] args) {

    }

    /**
     * 一次遍历，寻找局部最低价，如果大于局部最低价则尝试求差值
     * 个人感觉属于贪心算法
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            // 如果价格低于最低价，更新
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // 否则判断此时卖出是否高于最大利润
                res = Math.max(res, prices[i] - minPrice);
            }
        }
        return res;
    }

    /**
     * dp[i] = max(dp[i-1] + prices[i] - prices[i-1], 0)
     * dp[i]表示第i天卖的最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int res = 0;
        // dp[i]表示第i天卖出的最大利润
        int[] dp = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            // 如果今日卖会亏损，则干脆不买了
            dp[i] = Math.max(dp[i-1] + prices[i] - prices[i-1], 0);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * Kadane's算法
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int res = 0;
        // 前一天卖出的最大利润
        int cur = 0;
        for (int i = 1; i < prices.length; i++) {
            cur = Math.max(cur + prices[i] - prices[i-1], 0);
            res = Math.max(res, cur);
        }
        return res;
    }

    /**
     * 股票问题通用解法，将无后效性的约束条件转化为状态参数
     * dp[i][j]表示前i天的最大利润，j表示当天是否持股，0-未持股，1-持股
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     * 由于只能交易一次，因此dp[i][0] = 0
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][1] + prices[i], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }

        return dp[prices.length-1][0];
    }

    /**
     * 状态压缩
     *
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        // 当天未持股的最大利润
        int profit0 = 0;
        // 当天持股的最大利润
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, -prices[i]);
        }

        return profit0;
    }
}
