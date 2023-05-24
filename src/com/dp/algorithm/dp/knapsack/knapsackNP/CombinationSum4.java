package com.dp.algorithm.dp.knapsack.knapsackNP;

/**
 * leetcode_377_组合总数IV_中等
 */
public class CombinationSum4 {

    /**
     * 注意，顺序不同算不同组合，其实就是个排列问题
     * 爬楼梯的泛化，和完全背包问题不同
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // 初始边界
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
