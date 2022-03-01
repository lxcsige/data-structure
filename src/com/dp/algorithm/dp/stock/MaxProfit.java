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
     * 今日卖出可以获得的利润 = 昨日卖出可以获得的利润 + 价格差
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

    public int maxProfit3(int[] prices) {
        int res = 0;
        // 前一天卖出的最大利润
        int pre = 0;
        for (int i = 1; i < prices.length; i++) {
            // 如果今日卖会亏损，则干脆不买了
            pre = Math.max(pre + prices[i] - prices[i-1], 0);
            res = Math.max(res, pre);
        }
        return res;
    }
}
