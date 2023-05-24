package com.dp.algorithm.dp.subArray;

/**
 * leetcode_5_最长回文子串_中等
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class LongestPalindrome {

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
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
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    beginIndex = i;
                }
            }
        }

        return s.substring(beginIndex, beginIndex + maxLength);
    }

    /**
     * 双指针
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int beginIndex = 0;
        for (int i = 0; i < len; i++) {
            // 1个中心点
            int res1 = extend(s, i, i);
            // 2个中心点
            int res2 = extend(s, i, i + 1);
            int tmp = Math.max(res1, res2);
            if (tmp > maxLen) {
                maxLen = tmp;
                beginIndex = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(beginIndex, beginIndex + maxLen);
    }

    private int extend(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
