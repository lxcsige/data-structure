package com.dp.algorithm.dp;

/**
 * 377_组合总和4
 *
 * @author liuxucheng
 * @since 2021/6/13
 */
public class CombinationSum4 {

    public static void main(String[] args) {

    }

    /**
     * 70_爬楼梯的扩展
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }
}
