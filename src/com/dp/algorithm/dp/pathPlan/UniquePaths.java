package com.dp.algorithm.dp.pathPlan;

/**
 * leetcode_62_不同路径_中等
 * reviewed at 2023.05.03
 *
 * @author xucheng.liu
 * @since 2022/2/24
 */
public class UniquePaths {

    public static void main(String[] args) {

    }

    /**
     *
     * @param m 行
     * @param n 列
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    /**
     *
     * 状态压缩
     * @param m 行
     * @param n 列
     * @return
     */
    public static int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 对于第一行，dp[j]=0+dp[j-1]=1
                // 对于其他行，dp[j] = dp[i-1][j] + dp[i][j-1]
                dp[j] += dp[j-1];
            }
        }

        return dp[n-1];
    }
}
