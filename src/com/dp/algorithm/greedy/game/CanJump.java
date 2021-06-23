package com.dp.algorithm.greedy.game;

/**
 * 55_跳跃游戏
 *
 * @author liuxucheng
 * @since 2021/6/22
 */
public class CanJump {

    public static void main(String[] args) {

    }

    /**
     * dp，时间复杂度O(n^2)，空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
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

    /**
     * 贪心
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) {
                return true;
            }
            if (max == i) {
                return false;
            }
        }

        return false;
    }
}
