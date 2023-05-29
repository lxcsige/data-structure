package com.dp.algorithm.slidingwindow;

import java.util.Arrays;

/**
 * leetcode_727_最小窗口子序列
 *
 * @author liuxucheng
 * @since 2023/5/25
 */
public class MinWindowSubsequence {

    public static void main(String[] args) {
        MinWindowSubsequence test = new MinWindowSubsequence();
        test.minWindow("abcdebdde", "bde");
    }

    public String minWindow(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m < n) {
            return "";
        }
        int left = -1, right = -1, minLen = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < m; i++) {
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            // 匹配完成
            if (j == n) {
                j--;
                right = i;
                // 逆向匹配
                while (j >= 0) {
                    if (s1.charAt(i--) == s2.charAt(j)) {
                        j--;
                    }
                }
                // 找到窗口左边界
                i++;
                // 更新结果
                if (right - i + 1 < minLen) {
                    left = i;
                    minLen = right - left + 1;
                }
                j = 0;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s1.substring(left, left + minLen);
    }

    /**
     * dp[i][j]表示s1前i个字符中包含s2前j个字符的最近起点
     * s1[i - 1] == s2[j - 1], dp[i][j] = dp[i - 1][j - 1]
     * s1[i - 1] != s2[j - 1], dp[i][j] = dp[i - 1][j]
     *
     * @param s1
     * @param s2
     * @return
     */
    public String minWindow2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始状态
        for (int i = 1; i <= n; i++) {
            dp[0][i] = -1;
        }
        // 从状态转移方程推导得到
        for (int i = 0; i <= m; i++) {
            dp[i][0] = s1.charAt(i) == s2.charAt(0) ? i : -1;
        }
        int minLen = Integer.MAX_VALUE, l = -1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            // 存在解
            if (dp[i][n] >= 0) {
                if (i - dp[i][n] < minLen) {
                    l = dp[i][n];
                    minLen = i - l;
                }
            }
        }
        return l == -1 ? "" : s1.substring(l, l + minLen);
    }
}
