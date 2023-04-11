package com.dp.algorithm.sort;

import java.util.Random;

/**
 * 快速排序
 *
 * @author xucheng.liu
 * @date 2021/3/25
 */
public class QuickSort {

    Random random = new Random();

    public static void main(String[] args) {
        QuickSort solution = new QuickSort();
        int[] nums = new int[]{2,3,1,2,4,7,6};
        solution.quickSort(nums);
    }

    /**
     * 快速排序，不稳定
     * 整体思路：
     * 1. 划分：选取基准值（边界、随机、三数取中），将数组划分为大于和不大于基准值的2个部分（双指针、单指针），每次划分都可以确定一个元素的最终位置；
     * 2. 递归：通过递归调用，分别对2个子数组进行排序；
     * 3. 合并：因为子数组都是原址排序的，所以不需要进行合并操作。
     *
     * 平均时间复杂度O(nlogn)，平均空间复杂度O(logn)
     * 在基本有序的情况下，最坏时间复杂度O(n^2)，最坏空间复杂度O(n)，退化为冒泡排序
     *
     * @param nums
     */
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = randomPartition(nums, start, end);
        quickSort(nums, start, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, end);
    }

    /**
     * 随机
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int randomPartition(int[] nums, int start, int end) {
        // 随机选取基准值
        int pivotIndex = random.nextInt(end - start + 1) + start;
        swap(nums, start, pivotIndex);
        return partition3(nums, start, end);
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 双指针（交换）
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition1(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start, right = end;
        while (left < right) {
            // 向左遍历直到第一个不大于基准的元素
            // 注意：如果选择左边界作为基准值，则必须先向右遍历，否则left==right时，无法保证nums[left]不大于pivot
            while (left < right && nums[right] > pivot) {
                right--;
            }
            // 向右遍历直到第一个大于基准的元素
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            // 交换两个元素，大于基准值的元素交换到右边，小于基准值的元素交换到左边
            if (left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, start, left);
        return left;
    }

    /**
     * 双指针（挖坑）
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition2(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start, right = end;
        while (left < right) {
            // 从右向左遍历，直到找到第一个不大于基准的元素或left==right
            while (left < right && nums[right] > pivot) {
                right--;
            }
            // 将该元素填到左边，原来的位置则作为下一个要填的坑
            if (left < right) {
                nums[left] = nums[right];
                left++;
            }
            // 从左向右遍历，直到找到第一个大于基准的元素或left==right
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            // 将该元素填到右边（之前留下的坑）
            if (left < right) {
                nums[right] = nums[left];
                right--;
            }
        }
        nums[left] = pivot;
        return left;
    }

    /**
     * 单指针
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition3(int[] nums, int start, int end) {
        int pivot = nums[start];
        // 不大于pivot的最大index，即边界
        int mark = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] <= pivot) {
                // 右移边界
                mark++;
                // 交换nums[mark+1]和nums[i]
                int temp = nums[mark];
                nums[mark] = nums[i];
                nums[i] = temp;
            }
        }
        nums[start] = nums[mark];
        nums[mark] = pivot;
        return mark;
    }
}
