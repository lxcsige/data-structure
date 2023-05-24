package com.dp.algorithm.dp.subSequence;

/**
 * leetcode_583_两个字符串的删除操作_中等
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2021/5/21
 */
public class MinDistance {

    public static void main(String[] args) {

    }

    /**
     * LCS变种
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return m+n-2*dp[m][n];
    }
}
