package com.dp.algorithm.dp;

/**
 * 53_最大子序和
 *
 * @author liuxucheng
 * @since 2021/5/26
 */
public class MaxSubArray {

    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        int sum = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = sum < 0 ? nums[i] : nums[i] + sum;
            res = Math.max(res, sum);
        }
        return res;
    }

}
