package com.dp.algorithm.dp.subArray;

/**
 * leetcode_5_最长回文子串
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }

        int maxLength = 1;
        int beginIndex = 0;
        boolean[][] dp = new boolean[length][length];
        // 初始化边界
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    beginIndex = i;
                }
            }
        }

        return s.substring(beginIndex, beginIndex + maxLength);
    }

}
