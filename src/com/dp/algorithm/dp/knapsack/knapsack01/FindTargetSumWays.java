package com.dp.algorithm.dp.knapsack.knapsack01;

/**
 * leetcode_494_目标和_中等
 *
 * @author xucheng.liu
 * @since 2022/2/9
 */
public class FindTargetSumWays {

    public static void main(String[] args) {
        FindTargetSumWays test = new FindTargetSumWays();
        test.findTargetSumWays(new int[]{0,0,0,1}, 1);
    }

    /**
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
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= positive; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= positive; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
        return dp[n][positive];
    }

    /**
     * 压缩状态，注意初始边界
     * dp[j]表示目标和为j的方法数
     * dp[j] = dp[j] + dp[j - nums[i]]
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
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
        int positive = (target + sum) / 2;
        int[] dp = new int[positive + 1];
        // 初始化边界
        dp[0] = 1;
        for (int num : nums) {
            for (int j = positive; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[positive];
    }
}
