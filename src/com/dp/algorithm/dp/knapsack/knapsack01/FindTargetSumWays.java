package com.dp.algorithm.dp.knapsack.knapsack01;

/**
 * leetcode_494_目标和_中等
 *
 * @author xucheng.liu
 * @since 2022/2/9
 */
public class FindTargetSumWays {

    /**
     * dp[i][j]表示nums[0...i]中的表达式数量
     * dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((target + sum) < 0) {
            return 0;
        }
        if ((target + sum) % 2 == 1) {
            return 0;
        }
        int n = nums.length;
        int positive = (target + sum) / 2;
        int[][] dp = new int[n + 1][positive + 1];
        // 初始化边界
        dp[0][0] = 1;
        if (nums[0] <= positive) {
            dp[1][nums[0]] = 1;
        }
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= positive; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][positive];
    }
}
