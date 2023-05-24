package com.dp.algorithm.dp.rob;

/**
 * leetcode_198_打家劫舍_中等
 *
 * reviewed at 2023.05.04
 *
 * @author xucheng.liu
 * @since 2022/5/15
 */
public class Rob {

    /**
     * dp[n]表示偷前n间房的最大金额
     * dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i])，
     * 前者表示不偷第i间房的收益，此时dp[i] = dp[i - 1]
     * 后者表示偷第i间房的收益
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        // 初始化状态
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[nums.length-1];
    }

    /**
     * 状态压缩
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 初始化状态
        int prevPrev = nums[0];
        int prev = Math.max(nums[0], nums[1]);
        int cur;
        for (int i = 2; i < nums.length; i++) {
            cur = Math.max(prev, prevPrev + nums[i]);
            prevPrev = prev;
            prev = cur;
        }
        return prev;
    }
}
