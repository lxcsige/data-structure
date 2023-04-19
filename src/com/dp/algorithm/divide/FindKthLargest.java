package com.dp.algorithm.divide;

import java.util.Random;

/**
 * leetcode_215_数组中的第K个最大元素_中等
 *
 * @author liuxucheng
 * @since 2022/8/10
 */
public class FindKthLargest {

    Random random = new Random();

    public static void main(String[] args) {
        FindKthLargest solution = new FindKthLargest();
        System.out.println(solution.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    /**
     * 快速选择算法
     * 整体思路类似快排，每次划分可以确定一个元素的位置
     * 时间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k - 1, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int k, int start, int end) {
        int pivotIndex = randomPartition(nums, start, end);
        if (pivotIndex == k) {
            return nums[pivotIndex];
        }
        return pivotIndex > k ? quickSelect(nums, k, start, pivotIndex - 1)
                : quickSelect(nums, k, pivotIndex + 1, end);
    }

    private int randomPartition(int[] nums, int start, int end) {
        int pivotIndex = random.nextInt(end - start + 1) + start;
        swap(nums, start, pivotIndex);
        return partition(nums, start, end);
    }

    private int partition(int[] nums, int start, int end) {
        int left = start, right = end, pivot = nums[start];
        // 左边大于等于pivot，右边小于pivot
        while (left < right) {
            while (left < right && nums[right] < pivot) {
                right--;
            }
            while (left < right && nums[left] >= pivot) {
                left++;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        nums[start] = nums[left];
        nums[left] = pivot;
        return left;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 堆排序，建堆的时间复杂度O(n)，调整堆的时间复杂度是O(klogn)，总的时间复杂度是O(n+klogn)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        // 建堆
        buildHeap(nums);
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            // 头尾元素交换
            swap(nums, 0, i);
            // 调整堆中剩余元素的位置
            adjustHeap(nums, 0, i - 1);
        }
        return nums[0];
    }

    /**
     * 建大根堆
     *
     * @param nums
     */
    private void buildHeap(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length - 1);
        }
    }

    /**
     * 调整大根堆
     *
     * @param nums
     * @param start
     * @param end
     */
    private void adjustHeap(int[] nums, int start, int end) {
        int i = start;
        int temp = nums[start];
        for (int j = 2 * i + 1; j <= end; j = 2 * j + 1) {
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] > temp) {
                nums[i] = nums[j];
                i = j;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }

    /**
     * 堆排序，建小根堆的时间复杂度是O(k)，调整堆的时间复杂度是O((n-k)*logk)，因此总的时间复杂度是O(nlogk)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest3(int[] nums, int k) {
        // 建堆
        buildMinHeap(nums, k);
        for (int i = k; i < nums.length; i++) {
            if (nums[0] < nums[i]) {
                swap(nums, 0, i);
                adjustMinHeap(nums, 0, k - 1);
            }
        }
        return nums[0];
    }

    private void buildMinHeap(int[] nums, int k) {
        for (int i = k / 2 - 1; i >= 0; i--) {
            adjustMinHeap(nums, i, k - 1);
        }
    }

    private void adjustMinHeap(int[] nums, int start, int end) {
        int temp = nums[start];
        int i = start;
        for (int j = 2 * i + 1; j <= end; j = 2 * j + 1) {
            if (j + 1 <= end && nums[j + 1] < nums[j]) {
                j++;
            }
            if (nums[j] < temp) {
                nums[i] = nums[j];
                i = j;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }
}
