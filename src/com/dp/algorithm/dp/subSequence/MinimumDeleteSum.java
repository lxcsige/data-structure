package com.dp.algorithm.dp.subSequence;

/**
 * 712_两个字符串的最小ASCII删除和
 *
 * @author liuxucheng
 * @since 2021/5/22
 */
public class MinimumDeleteSum {

    public static void main(String[] args) {

    }

    public int minimumDeleteSum1(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        return helper(s1, 0, s2, 0, dp);
    }

    private int helper(String s1, int m, String s2, int n, int[][] dp) {
        int res = 0;
        if (m == s1.length()) {
            for (; n < s2.length(); n++) {
                res += s2.charAt(n);
            }
            return res;
        }
        if (n == s2.length()) {
            for (; m < s1.length(); m++) {
                res += s1.charAt(m);
            }
            return res;
        }
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        if (s1.charAt(m) == s2.charAt(n)) {
            dp[m][n] =  helper(s1, m + 1, s2, n + 1, dp);
        } else {
            dp[m][n] = Math.min(s1.charAt(m) + helper(s1, m + 1, s2, n, dp),
                    s2.charAt(n) + helper(s1, m, s2, n + 1, dp));
        }
        return dp[m][n];
    }

    public int minimumDeleteSum2(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        // dp[m][n]表示s1的前m个字符和s2的前n个字符的最小删除ASCII和
        int[][] dp = new int[length1 + 1][length2 + 1];
        // 边界初始化，dp[0][0] = 0，其实就是base case
        for (int i = 1; i <= length1; i++) {
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        }
        for (int j = 1; j <= length2; j++) {
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (s1.charAt(i-1) == s2.charAt(j -1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1));
                }
            }
        }
        return dp[length1][length2];
    }
}
