package com.dp.algorithm.binarysearch;

/**
 * leetcode_300_最长递增子序列_中等
 *
 * @author liuxucheng
 * @since 2022/8/3
 */
public class LengthOfLIS {

    /**
     * 贪心 + 二分查找
     * d[i]表示长度为i的最长递增子序列末尾元素的最小值，只有末尾元素越小，才越可能根据当前子序列得到更长的递增子序列（贪心）
     * 当i < j，则d[i] < d[j]，即d[]数组是严格单调递增的
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = 1;
        // d[i]表示长度为i的最长递增子序列末尾元素的最小值
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 将nums[i]放到数组的末尾，同时更新len++
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                // 二分查找大于等于nums[i]的最小index
                // dp[left-1] < nums[i] =< dp[left]
                int left = 1, right = len;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (dp[mid] >= nums[i]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                dp[left] = nums[i];
            }
        }
        return len;
    }
}
