package com.dp.algorithm.array;

import java.util.Arrays;

/**
 * leetcode_628_三个数的最大乘积_简单
 */
public class MaximumProduct {

    /**
     * 排序
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[n - 3] * nums[n - 2] * nums[n - 1], nums[n - 1] * nums[0] * nums[1]);
    }

    /**
     * 不排序
     *
     * @param nums
     * @return
     */
    public int maximumProduct2(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
