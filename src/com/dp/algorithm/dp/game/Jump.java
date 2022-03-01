package com.dp.algorithm.dp.game;

/**
 * 45_跳跃游戏2_DP思路
 *
 * @author liuxucheng
 * @since 2022/2/24
 */
public class Jump {

    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n^2)，空间复杂度O(n)
     * dp[i]单调递增，可以优化
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i - j) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}
