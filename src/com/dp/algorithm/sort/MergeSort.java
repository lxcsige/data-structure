package com.dp.algorithm.sort;

/**
 * 归并排序（重点）
 *
 * @author liuxucheng
 * @since 2022/8/12
 */
public class MergeSort {

    public static void main(String[] args) {
        MergeSort solution = new MergeSort();
        solution.sortArray(new int[]{5,2,3,1});
    }

    public int[] sortArray(int[] nums) {
        int[] res = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, res);
        return res;
    }

    /**
     * 思路：
     * 1. 借助额外空间，合并两个有序数组，得到更长的有序数组
     * 2. 递归分治
     * 时间复杂度O(nlogn)
     * 空间复杂度O(n)
     * 优点：稳定
     *
     * @param nums
     * @param left
     * @param right
     * @param res
     */
    private void mergeSort(int[] nums, int left, int right, int[] res) {
        if (left >= right) {
            return;
        }
        // 划分，取中间节点
        int mid = left + (right - left) / 2;
        // 递归调用，分别对2个子数组进行排序
        mergeSort(nums, left, mid, res);
        mergeSort(nums, mid + 1, right, res);
        // 剪枝，左右两个子区间本来就有序，不需要归并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        // 归并2个有序子数组
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                res[k++] = nums[i++];
            } else {
                res[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            res[k++] = nums[i++];
        }
        while (j <= right) {
            res[k++] = nums[j++];
        }
        // 注意，这里需要修改原数组
        for (int p = 0; p < right - left + 1; p++) {
            nums[p + left] = res[p];
        }
    }
}
