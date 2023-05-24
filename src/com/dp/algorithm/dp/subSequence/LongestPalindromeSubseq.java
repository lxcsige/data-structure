package com.dp.algorithm.dp.subSequence;

/**
 * leetcode_516_最长回文子序列_中等
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2021/5/23
 */
public class LongestPalindromeSubseq {

    /**
     * 动态规划，dp[i][j]表示s[i...j]的最长回文子序列长度
     * 注意和回文子串的区别
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
