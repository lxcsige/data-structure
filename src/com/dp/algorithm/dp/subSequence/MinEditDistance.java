package com.dp.algorithm.dp.subSequence;

/**
 * 72_编辑距离
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
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for(int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
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
