package com.dp.algorithm.dp.rob;

/**
 * leetcode_213_打家劫舍2_中等
 *
 * reviewed at 2023.05.04
 *
 * @author xucheng.liu
 * @since 2022/5/15
 */
public class Rob2 {

    public static void main(String[] args) {
        rob(new int[]{1,3,1,3,100});
    }

    /**
     * 动态规划，思路和第198题基本相同，区别在于第一家和最后一家不能同时偷
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    private static int robRange(int[] nums, int start, int end) {
        int prevPrev = nums[start];
        int prev = Math.max(nums[start], nums[start + 1]);
        int cur;
        for (int i = start + 2; i <= end; i++) {
            cur = Math.max(prev, prevPrev + nums[i]);
            prevPrev = prev;
            prev = cur;
        }
        return prev;
    }
}
