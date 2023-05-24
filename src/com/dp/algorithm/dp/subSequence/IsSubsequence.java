package com.dp.algorithm.dp.subSequence;

/**
 * leetcode_392_判断子序列_简单
 */
public class IsSubsequence {

    /**
     * DP
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen > tLen) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        boolean[][] dp = new boolean[sLen + 1][tLen + 1];
        // 初始状态
        for (int i = 0; i <= tLen; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = i; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[sLen][tLen];
    }

    /**
     * 双指针
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen > tLen) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        int i = 0, j = 0;
        while (i < sLen && j < tLen) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == sLen;
    }
}
