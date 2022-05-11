package com.dp.algorithm.dp.subSequence;

/**
 * 516_最长回文子序列_中等
 *
 * @author liuxucheng
 * @since 2021/5/23
 */
public class LongestPalindromeSubseq {

    public static void main(String[] args) {

    }

    /**
     * 动态规划，dp[i][j]表示s[i...j]的最长回文子序列长度
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // 初始状态
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1;  j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][n-1];
    }
}
