package com.dp.algorithm.dp.subSequence;

/**
 * leetcode_72_编辑距离_困难
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2021/5/24
 */
public class MinEditDistance {

    public static void main(String[] args) {

    }

    /**
     * 暴力递归
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return helper(word1, word1.length(), word2, word2.length());
    }


    private int helper(String word1, int m, String word2, int n) {
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        if (word1.charAt(m) == word2.charAt(n)) {
            return helper(word1, m - 1, word2, n - 1);
        } else {
            return Math.min(helper(word1, m - 1, word2, n) + 1,
                    Math.min(helper(word1, m, word2, n - 1) + 1, helper(word1, m - 1, word2, n - 1) + 1));
        }
    }

    /**
     * dp
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // dp[i][j]表示以word1[i - 1]结尾的第一个字符串和word2[j - 1]结尾的第二个字符串的最小编辑距离
        int[][] dp = new int[m + 1][n + 1];
        // 初始状态
        for (int i = 1; i <= m; i++) {
            // 需要删除word1中i个字符
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            // 需要删除word2中j个字符
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * dp，优化空间复杂度
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance3(String word1, String word2) {
        int[] dp = new int[word2.length() + 1];
        // 初始化
        for (int i = 1; i <= word2.length(); i++) {
            dp[i] = i;
        }
        int leftUp;
        for (int i = 1; i <= word1.length(); i++) {
            leftUp = dp[0];
            dp[0] = i;
            for(int j = 1; j <= word2.length(); j++) {
                int tmp  = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = leftUp;
                } else {
                    dp[j] = Math.min(dp[j - 1] + 1, Math.min(dp[j] + 1, leftUp + 1));
                }
                leftUp = tmp;
            }
        }
        return dp[word2.length()];
    }
}
