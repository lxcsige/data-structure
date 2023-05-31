package com.dp.algorithm.binarysearch.rotate;

/**
 * leetcode_154_寻找旋转排序数组中的最小值II_困难
 *
 * reviewed at 2023.05.29
 *
 * @author liuxucheng
 * @since 2022/8/3
 */
public class FindMin2 {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
    }

    /**
     * 注意：可能存在重复元素
     * 旋转数组按旋转点可以分为左右两个有序的部分，左有序数组的所有元素大于等于右有序数组中的所有元素
     * 特例：[0,1,2,3,4]，其中0是旋转点，不存在左有序数组
     * 对于末尾元素r，左有序数组>=nums[r]，右有序数组<=nums[r]
     * 假设旋转点index为x
     * 1. 当nums[mid] > nums[r]，nums[mid]一定在左有序数组，mid < x，此时需要向右继续查找，l = mid + 1
     * 2. 当nums[mid] < nums[r]，nums[mid]一定在右有序数组，mid >= x，此时需要向左继续查找，r = mid
     * 3. 当nums[mid] == nums[r]，nums[mid]可能在左有序数组，也可能在右有序数组，此时更新r = r - 1，相当于线性遍历
     *
     * 只能和nums[r]比较，否则无法处理整体有序的情况
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }
        int l = 0, r = nums.length - 1, mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            // mid在左有序数组，即旋转点的左边
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] < nums[r]) {
                // mid在有有序数组，即旋转点的右边（包括旋转点）
                r = mid;
            } else {
                // mid可能在旋转点的左边也可能在右边，考虑num[mid]==num[r]，可以更新r=r-1，相当于线性遍历（最坏情况）
                r--;
            }
        }
        return nums[l];
    }
}
