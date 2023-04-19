package com.dp.algorithm.sort;

/**
 * 直接插入排序
 * 希尔排序对直接插入排序进行了优化
 *
 * 将元素插入到前面的有序部分
 *
 * @author xucheng.liu
 * @since 2022/5/11
 */
public class InsertSort {

    public static void main(String[] args) {

    }

    /**
     * 思路：每次将一个数字插入一个有序的数组里，成为一个长度更长的有序数组，有限次操作以后，数组整体有序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 优点：
     * 1. 在数组几乎有序的情况下，时间复杂度可以达到O(N)
     * 2. 稳定
     *
     * @param nums
     */
    private void insertSort(int[] nums) {
        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
        for (int i = 1; i < nums.length; i++) {
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int tmp = nums[i];
            int j = i;
            while (j > 0 && nums[j - 1] > tmp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = tmp;
        }
    }
}
