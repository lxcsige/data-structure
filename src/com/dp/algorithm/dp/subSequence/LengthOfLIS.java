package com.dp.algorithm.dp.subSequence;

/**
 * 300_最长递增子序列
 *
 * @author liuxucheng
 * @since 2021/5/20
 */
public class LengthOfLIS {

    public static void main(String[] args) {

    }

    /**
     * dp
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // dp[i]表示以nums[i]为后缀的递增子序列的长度
        int[] dp = new int[nums.length];
        // 递增子序列的最小长度为1
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * 二分查找
     *
     * @param nums
     */
    public void lengthOfLIS2(int[] nums) {

    }
}
