package com.dp.algorithm.dp.subArray;

/**
 * leetcode_647_回文子串_中等
 *
 * reviewed at 2023.05.05
 *
 * @author xucheng.liu
 * @since 2022/2/14
 */
public class CountSubStrings {

    /**
     * dp[i][j] = dp[i+1][j-1], s[i] == s[j]
     * 若j-i <= 2且s[i] == s[j]，则dp[i][j]一定是回文字符串
     *
     * 注意遍历顺序
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int res = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        // 初始状态，单字符串均满足回文要求
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            res++;
        }

        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * 遍历顺序：自下而上，从左到右
     *
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int len = s.length();
        int res = 0;
        // dp[i][j]表示s[i..j]是否是回文子串
        boolean[][] dp = new boolean[len][len];
        // 初始状态
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            res++;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 双指针
     *
     * @param s
     * @return
     */
    public int countSubstrings3(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // 1个中心点
            res += extend(s, i, i);
            // 2个中心点
            res += extend(s, i, i + 1);
        }
        return res;
    }

    private int extend(String s, int l, int r) {
        int res = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            res++;
            l--;
            r++;
        }
        return res;
    }
}
