package com.dp.algorithm.divide;

/**
 * leetcode_剑指offer 51.数组中的逆序对_困难
 */
public class ReversePairs {

    /**
     * 归并排序的思路
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int[] tmp = new int[n];
        return mergeSort(nums, 0, n - 1, tmp);
    }

    private int mergeSort(int[] nums, int left, int right, int[] tmp) {
        // base case
        if (left >= right) {
            return 0;
        }
        // 切分子数组
        int mid = left + (right - left) / 2;
        // 左右子数组递归
        int res = mergeSort(nums, left, mid, tmp) + mergeSort(nums, mid + 1, right, tmp);
        // 左右子数组归并
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                // nums[i] > nums[j]，说明左子数组中i及其右边的所有元素都可以和j构成逆序对
                res += mid - i + 1;
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        // 修改原数组
        for (int p = left; p <= right; p++) {
            nums[p] = tmp[p];
        }
        return res;
    }
}
