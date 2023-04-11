package com.dp.algorithm.binarysearch;

/**
 * 寻找左侧边界（包含）
 *
 * @author liuxucheng
 * @since 2022/8/2
 */
public class LeftBound {

    public static void main(String[] args) {
        LeftBound test = new LeftBound();
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(test.leftBound(nums, 11));
        System.out.println(test.leftBound2(nums, 6));
        System.out.println(test.leftBound3(nums, 11));
        System.out.println(test.leftBound4(nums, 11));
    }

    /**
     * [left, right)，包含，大于等于target的最小index
     *
     * @param nums
     * @param target
     * @return [0, nums.length]
     */
    private int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        // 终止条件是left == right，因此最终返回left和right都一样
        while (left < right) { // 注意
            int mid = (left + right) / 2;
            // 往左查，而非立即返回，逼近左侧边界
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 往左查
                right = mid;
            }
        }
        return left;
    }

    /**
     * [left, right)，不包含，小于target的最大index
     *
     * @param nums
     * @param target
     * @return [-1, nums.length - 1]
     */
    private int leftBound2(int[] nums, int target) {
        int left = 0, right = nums.length;
        // 终止条件是left == right，因此最终返回left和right都一样
        while (left < right) { // 注意
            int mid = (left + right) / 2;
            // 往左查，而非立即返回，逼近左侧边界
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 往左查
                right = mid;
            }
        }
        return left - 1;
    }

    /**
     * [left, right]，包含，大于等于target的最小index
     * 推荐，统一二分法模板
     *
     * @param nums
     * @param target
     * @return [0, nums.length]
     */
    private int leftBound3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 终止条件left = right + 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 往左查
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 往左查
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * [left, right]，不包含，小于target的最大index
     * 推荐，统一二分法模板
     *
     * @param nums
     * @param target
     * @return [-1, nums.length - 1]
     */
    private int leftBound4(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        // 终止条件left = right + 1
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // nums[right]肯定小于target
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return right;
    }
}
