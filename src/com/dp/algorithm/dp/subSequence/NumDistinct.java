package com.dp.algorithm.dp.subSequence;

/**
 * leetcode_115_不同的子序列_困难
 */
public class NumDistinct {

    /**
     * dp[i][j]表示s的前i个字符的子序列中出现t的前j个字符的次数
     * s[i-1] == t[j-1]，此时分为2种情况：
     * 1. 用s[i-1]匹配：dp[i-1][j-1]
     * 2. 不用s[i-1]匹配（即s[0..i-2]）：dp[i-1][j]
     * eg：s = "bagg"，t = "bag"，s[3] == t[2]
     * 如果只考虑情况1，那么结果为dp[3][2]，即用s的前3个字符和t的前2个字符比较，最终结果为1，而实际结果应该是2
     *
     * s[i-1] != t[j-1]，那么dp[i][j] = dp[i-1][j]，即只能用s[0..i-2]去匹配
     *
     * 重点：是否用s[i-1]匹配
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return 0;
        }
        if (s.equals(t)) {
            return 1;
        }
        int[][] dp = new int[sLen + 1][tLen + 1];
        // 初始状态
        for (int i = 0; i <= sLen; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= i; j++) {
                // 不用s[i - 1]匹配
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[sLen][tLen];
    }
}
