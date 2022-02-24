package com.dp.algorithm.dp.subArray;

/**
 * 647_回文子串，求回文子串的数量
 *
 * @author xucheng.liu
 * @since 2022/2/14
 */
public class CountSubStrings {

    public static void main(String[] args) {
        System.out.println(countSubstrings("aaaaa"));
    }

    /**
     * dp[i][j] = dp[i+1][j-1], s[i] == s[j]
     * 若j-i <= 2且s[i] == s[j]，则dp[i][j]一定是回文字符串
     *
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
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


    public static int countSubstrings2(String s) {
        int n = s.length();
        if (0 == n) return 0;

        int ans = 0;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans++;
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j-i <3 || dp[i+1][j-1]);
                if (dp[i][j]) { ans++; }
            }
        }

        return ans;
    }
}
