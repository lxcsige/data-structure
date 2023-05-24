package com.dp.algorithm.dp.subArray;

/**
 * leetcode_53_最大子数组和_中等
 *
 * reviewed at 2023.05.05
 *
 * @author liuxucheng
 * @since 2021/5/26
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 1, -1, 6, 2, -5, 4};
        System.out.println("res1 = " + maxSubArray1(nums));
        System.out.println("res2 = " + maxSubArray2(nums));
    }

    /**
     * 动态规划_v1，时间复杂度O(n)，空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        // 动态规划数组，dp[n]表示以nums[n]结尾的子数组的最大和
        int[] dp = new int[nums.length];
        // 初始化状态
        dp[0] = nums[0];
        // 结果
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i-1] + nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 动态规划_v2，时间复杂度O(n)，空间复杂度O(1)，状态压缩
     *
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        int sum = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = sum < 0 ? nums[i] : nums[i] + sum;
            res = Math.max(res, sum);
        }
        return res;
    }

}
