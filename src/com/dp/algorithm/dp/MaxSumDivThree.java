package com.dp.algorithm.dp;

/**
 * @author xucheng.liu
 * @since 2022/3/22
 */
public class MaxSumDivThree {

    public static void main(String[] args) {
        maxSumDivThree(new int[]{2,19,6,16,5,10,7,4,11,6});
    }

    public static int maxSumDivThree(int[] nums) {
        int[][] dp = new int[nums.length][3];

        // 初始状态
        dp[0][nums[0]%3] = nums[0];

        int rest;
        for (int i = 1; i < nums.length; i++) {
            rest = nums[i] % 3;
            if (rest == 0) {
                dp[i][0] = dp[i-1][0] + nums[i];
                dp[i][1] = dp[i-1][1] == 0 ? 0 : dp[i-1][1] + nums[i];
                dp[i][2] = dp[i-1][2] == 0 ? 0 : dp[i-1][2] + nums[i];
            } else if (rest == 1) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] == 0 ? 0 : dp[i-1][2] + nums[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + nums[i]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] == 0 ? 0 : dp[i-1][1] + nums[i]);
            } else {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] == 0 ? 0 : dp[i-1][1] + nums[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2] == 0 ? 0 : dp[i-1][2] + nums[i]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][0] + nums[i]);
            }
        }

        return dp[nums.length-1][0];
    }
}
