package com.dp.algorithm.dp;

/**
 * leetcode_221_最大正方形_中等
 */
public class MaximalSquare {

    /**
     * dp[i][j]表示以matrix[i - 1][j - 1]为后下角的最大正方形的边长
     * dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        // dp[i][j]表示以matrix[i - 1][j - 1]为后下角的最大正方形的边长
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
