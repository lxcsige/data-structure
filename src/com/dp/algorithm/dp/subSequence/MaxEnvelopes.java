package com.dp.algorithm.dp.subSequence;

import java.util.Arrays;

/**
 * 354_俄罗斯套娃信封
 *
 * @author liuxucheng
 * @since 2021/6/2
 */
public class MaxEnvelopes {

    public static void main(String[] args) {
        MaxEnvelopes test = new MaxEnvelopes();
        int[][] envelopes = new int[][]{{46,89},{50,53},{52,68},{72,45},{77,81}};
        test.maxEnvelopes(envelopes);
    }

    /**
     * LIS变种，先按宽度排序，然后求最长递增子序列
     * 注意base case: Arrays.fill(dp, 1)
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        // 按e[0]升序，e[1]降序，防止出现宽度相等但高度递增情况下得到错误结果
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e2[1] - e1[1];
            }
            return e1[0] - e2[0];
        });

        // 最长递增子序列
        int res = 1;
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
