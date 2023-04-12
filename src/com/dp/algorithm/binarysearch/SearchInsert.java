package com.dp.algorithm.binarysearch;

/**
 * leetcode_35_搜索插入位置_简单
 *
 * @author liuxucheng
 * @since 2022/8/1
 */
public class SearchInsert {

    /**
     * 递增无重复数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        // [left, right]
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
