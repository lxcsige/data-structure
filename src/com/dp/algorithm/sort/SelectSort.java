package com.dp.algorithm.sort;

/**
 * 直接选择排序
 */
public class SelectSort {

    /**
     * 思路：每一轮选取未排定区间中最小的元素交换到未排定区间的开头
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 优点：交换次数少
     * 缺点：不稳定
     *
     * @param nums
     */
    public void selectSort(int[] nums) {
        // 循环不变量：[0, i) 有序，且该区间里所有元素就是最终排定的样子
        // 未排序区间：[i, n)
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            // 找到未排序部分中最小元素的index
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            // 将最小元素交换到未排序区间的开头
            swap(nums, i, minIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
