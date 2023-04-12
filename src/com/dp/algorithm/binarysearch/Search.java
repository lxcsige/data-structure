package com.dp.algorithm.binarysearch;

/**
 * leetcode_704_二分查找_简单
 *
 * @author liuxucheng
 * @since 2022/7/31
 */
public class Search {

    private int search(int[] nums, int target) {
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
        return -1;
    }

    private int search2(int[] nums, int target) {
        int left = 0, right = nums.length, mid;
        // [2, 2)区间非法，因此是left < right，不需要left <= right
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                // [left, mid)
                right = mid;
            } else if (nums[mid] < target) {
                // [mid + 1, right)
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
