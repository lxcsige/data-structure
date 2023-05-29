package com.dp.algorithm.binarysearch;

/**
 * @author liuxucheng
 * @since 2022/8/2
 */
public class RightBound {

    public static void main(String[] args) {
        RightBound test = new RightBound();
        int[] nums = new int[]{5,7,7,8,8};
        System.out.println(test.rightBound1(nums, 4));
//        System.out.println(test.rightBound2(nums, 4));
        System.out.println(test.rightBound3(nums, 4));
//        System.out.println(test.rightBound4(nums, 4));
    }

    /**
     * [left, right)，小于等于target的最大index
     *
     * @param nums
     * @param target
     * @return [-1, nums.length - 1]
     */
    public int rightBound1(int[] nums, int target) {
        int left = 0, right = nums.length;
        // 终止条件left == right
        while (left < right) {
            int mid = (right + left) / 2;
            // 相等时继续向右找
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        // left被更新为mid+1，因此nums[left]肯定不等于target
        return left - 1;
    }

    /**
     * [left, right)，大于target的最小index
     * @param nums
     * @param target
     * @return [0, num.length]
     */
    public int rightBound2(int[] nums, int target) {
        int left = 0, right = nums.length, mid;
        // 终止条件left == right
        while (left < right) {
            mid = (right + left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        // left被更新为mid+1，因此nums[left]肯定不等于target
        return left;
    }

    /**
     * [left, right]，包含，小于等于target的最大index
     *
     * @param nums
     * @param target
     * @return [-1, num.length - 1]
     */
    public int rightBound3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 终止条件left = right + 1
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // nums[left]一定大于target
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * [left, right]，不包含，大于target的最小index
     *
     * @param nums
     * @param target
     * @return [0, nums.length]
     */
    public int rightBound4(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        // 终止条件left == right + 1
        while (left <= right) {
            mid = (right + left) / 2;
            if (nums[mid] == target) {
                // nums[left]一定大于target
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }
}
