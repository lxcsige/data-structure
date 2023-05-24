package com.dp.algorithm.dp.subSequence;

/**
 * leetcode_673_最长递增子序列的个数_中等
 *
 * reviewed at 2023.05.05
 *
 * @author xucheng.liu
 * @since 2022/3/17
 */
public class FindNumberOfLIS {

    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{10,9,1,5,2,6,66,18}));
    }

    /**
     * DP，在寻找最长递增子序列长度的同时记录相应的数量
     *
     * @param nums
     * @return
     */
    public static int findNumberOfLIS(int[] nums) {
        int maxLength = 1, res = 1;
        // 以nums[i]为后缀的最长递增子序列的长度
        int[] dp = new int[nums.length];
        // 以num[i]为后缀的最长递增子序列的个数
        int[] count = new int[nums.length];
        // 初始状态
        dp[0] = 1;
        count[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) {
                    continue;
                }
                if (dp[j] + 1 > dp[i]) {
                    count[i] = count[j];
                    dp[i] = dp[j] + 1;
                } else if (dp[j] + 1 == dp[i]) {
                    count[i] += count[j];
                }
            }
            if (dp[i] > maxLength) {
                res = count[i];
                maxLength = dp[i];
            } else if (dp[i] == maxLength) {
                res += count[i];
            }
        }

        return res;
    }
}
