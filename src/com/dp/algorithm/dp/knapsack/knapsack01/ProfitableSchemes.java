package com.dp.algorithm.dp.knapsack.knapsack01;

/**
 * 879_盈利计划
 *
 * @author xucheng.liu
 * @since 2022/2/9
 */
public class ProfitableSchemes {

    /**
     * dp[i][j][k]表示前i份工作使用j个工人可以至少产生盈利k的计划数量
     * dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-group[i-1]][Math.max(k-profit[i-1], 0)]
     * dp[i-1][j][k]表示不干第i份工作的盈利计划数
     * dp[i-1][j-group[i-1]][Math.max(k-profit[i-1], 0)]表示干第i份工作的盈利计划数
     * 为什么是Math.max(k-profit[i-1], 0)？k - profit[i-1]可能小于0
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int) (Math.pow(10, 9) + 7);
        int m = group.length;
        int[][][] dp = new int[m + 1][n + 1][minProfit + 1];
        // 初始化边界
        for (int j = 0; j <= n; j++) {
            // 一份工作都不干产生的盈利为0，满足不小于最小盈利0的条件
            dp[0][j][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    // 人力不足，干不了第i份工作
                    if (j < group[i - 1]) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - group[i - 1]][Math.max(k - profit[i - 1], 0)]) % mod;
                    }
                }
            }
        }
        return dp[m][n][minProfit];
    }

    /**
     * 压缩状态
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int) (Math.pow(10, 9) + 7);
        int m = group.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        // 初始化边界
        for (int j = 0; j <= n; j++) {
            // 一份工作都不干产生的盈利为0，满足不小于最小盈利0的条件
            dp[j][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = n; j >= group[i]; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - group[i]][Math.max(k - profit[i], 0)]) % mod;
                }
            }
        }
        return dp[n][minProfit];
    }
}
