package com.dp.algorithm.array;

/**
 * leetcode_31_下一个排列_中等
 */
public class NextPermutation {

    /**
     * 归纳总结，没什么特别的算法
     *
     * 推导：
     * 1. 将后面的「大数」与前面的「小数」交换
     * 2. 增加的幅度尽可能小：
     *      1. 在尽可能靠右的低位进行交换，需要从后向前查找
     *      2. 将一个尽可能小的「大数」与前面的「小数」交换
     *      3. 将「大数」换到前面后，需要将「大数」后面的所有数重置为升序
     *
     * 算法过程
     * 1. 从后向前找到第一对相邻升序的元素，nums[i] < nums[i + 1]，[i + 1, n)区间内降序
     * 2. 从[i+1, n）区间内找到第一个大于nums[i]的元素，并进行交换
     * 3. [i + 1, j)区间逆序，确保结果最小
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        // 1. 从后向前找到第一对相邻升序元素
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 2. 从(i, n)区间中找到第一个大于nums[i]的元素并交换
        if (i >= 0) {
            int j = n - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 3. 倒序
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int l = start, r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }
}
