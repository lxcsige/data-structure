package com.dp.algorithm.dp.subSequence;

/**
 * 1143_最长公共子序列
 *
 * @author liuxucheng
 * @since 2021/5/20
 */
public class LongestCommonSubSequence {

    public static void main(String[] args) {

    }

    public int longestCommonSubSequence1(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        return helper(text1, 0, text2, 0, dp);
    }

    private int helper(String text1, int m, String text2, int n, int[][] dp) {
        if (m == text1.length() || n == text2.length()) {
            return 0;
        }
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        if (text1.charAt(m) == text2.charAt(n)) {
            dp[m][n] =  1 + helper(text1, m + 1, text2, n + 1, dp);
        } else {
            dp[m][n] = Math.max(helper(text1, m + 1, text2, n, dp), helper(text1, m, text2, n + 1, dp));
        }
        return dp[m][n];
    }

    public int longestCommonSubSequence2(String text1, String text2) {
        // dp[m][n]表示text1前m个元素和text2前n个元素的最长公共子序列长度
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
