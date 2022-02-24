package com.dp.algorithm.dp.knapsack.knapsackNP;

/**
 * 279_完全平方数，即零钱兑换1
 *
 * @author xucheng.liu
 * @since 2022/2/14
 */
public class NumSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(2));
    }

    /**
     * 完全背包+二维数组
     *
     * @param n 目标正整数
     * @return
     */
    public static int numSquares(int n) {
        // 完全平方数数组的最大长度
        int length = (int) Math.sqrt(n);
        // 动态规划数组
        int[][] dp = new int[length+1][n+1];
        // 初始化边界
        for (int i = 1; i <= n; i++) {
            // 表示凑不出来
            dp[0][i] = n + 1;
        }
        // 遍历数组
        for (int i = 1; i <= length; i++) {
            // 遍历目标
            for (int j = 1; j <= n; j++) {
                int numSquare = (int) Math.pow(i, 2);
                // 不放
                if (j < numSquare) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 决策
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-numSquare]+1);
                }
            }
        }

        return dp[length][n] == n + 1 ? -1 : dp[length][n];
    }

    /**
     * 完全背包+一维数组
     *
     * @param n
     * return
     */
    public static int numSquares2(int n) {
        // 动态规划数组
        int[] dp = new int[n+1];
        // 遍历目标
        for (int i = 1; i <= n; i++) {
            // 初始化边界
            dp[i] = n + 1;
            // 遍历完全平方数
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n] == n + 1 ? -1 : dp[n];
    }
}
