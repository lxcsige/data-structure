package com.dp.algorithm.dp;

/**
 * leetcode_343_整数拆分_中等
 *
 * @author liuxucheng
 * @since 2023/5/3
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        // dp[i]：正整数i拆分后的最大乘积
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // j * (i - j)：拆分为2个正整数
                // j * dp[i - j]：拆分为2个以上的正整数
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
