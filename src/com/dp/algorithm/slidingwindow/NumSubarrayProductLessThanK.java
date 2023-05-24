package com.dp.algorithm.slidingwindow;

/**
 * leetcode_乘积小于k的子数组_中等
 *
 * 连续子数组问题可以考虑使用滑动窗口或动态规划
 */
public class NumSubarrayProductLessThanK {

    /**
     * 滑动窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int product = 1, n = nums.length, res = 0;
        for (int l = 0, r = 0; r < n; r++) {
            product *= nums[r];
            // 左边界右移，直到窗口内乘积小于k
            while (product >= k) {
                product /= nums[l++];
            }
            // nums[r]
            // nums[r], nums[r - 1]
            // ...
            // nums[r], nums[r - 1], ..., nums[l]
            // 从右往左，共计r - l + 1种
            res += r - l + 1;
        }
        return res;
    }
}
