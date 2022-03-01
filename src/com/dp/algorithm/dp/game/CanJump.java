package com.dp.algorithm.dp.game;

/**
 * 55_跳跃游戏_DP思路
 *
 * @author liuxucheng
 * @since 2022/2/24
 */
public class CanJump {

    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n^2)，空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && nums[j] >= (i - j);
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[nums.length - 1];
    }
}
