package com.dp.algorithm.dp.subSequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * 时间复杂度-O(n^2)，空间复杂度-O(n)
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

    /**
     * 贪心 + 二分查找
     * 时间复杂度-O(nlogn)，空间复杂度-O(n)
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e2[1] -e1[1];
            }
            return e1[0] - e2[0];
        });

        // res[i]表示前j个元素中长度为i的递增子序列的末尾元素的最小值
        // res[i]严格递增，反证法
        List<Integer> res = new ArrayList<>();
        res.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++) {
            // envelopes[i][1]>当前最长递增子序列的末尾元素，则最长递增子序列长度+1，并将envelopes[i][1]设为其末尾元素
            if (envelopes[i][1] > res.get(res.size() - 1)) {
                res.add(envelopes[i][1]);
            } else {
                // 二分查找第一个不小于envelopes[i][1]的元素，并用envelopes[i][1]覆盖
                res.set(binarySearch(res, envelopes[i][1]), envelopes[i][1]);
            }
        }

        return res.size();
    }

    /**
     * 二分查找，返回第一个不小于target的元素下标
     *
     * @param list
     * @param target
     * @return
     */
    private int binarySearch(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return r;
    }
}
