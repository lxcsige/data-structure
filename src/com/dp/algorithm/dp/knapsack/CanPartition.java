package com.dp.algorithm.dp.knapsack;

/**
 * 416_分割等和子集，只包含正整数
 *
 * @author liuxucheng
 * @since 2021/6/9
 */
public class CanPartition {

    public static void main(String[] args) {

    }

    /**
     * 01背包问题变种
     *
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int halfSum = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][halfSum + 1];
        // 注意这里的base case，从状态转移方程dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]来看，当j = nums[i-1]时，就可以表示nums[i-1】可以被单独分为一组
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= halfSum; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[nums.length][halfSum];
    }

    /**
     * 状态压缩
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int halfSum = sum / 2;
        // dp[i]表示目标sum为i时是否可以找到数组子集
        boolean[] dp = new boolean[halfSum + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = halfSum; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j-nums[i-1]];
            }
        }

        return dp[halfSum];
    }
}
