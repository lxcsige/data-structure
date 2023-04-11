package com.dp.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author xucheng.liu
 * @date 2021/3/25
 */
public class HeapSort {

    public static void main(String[] args) {
        HeapSort solution = new HeapSort();
        int[] nums = new int[]{5,2,3,1};
        solution.heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 堆排序，时间复杂度O(nlogn)，不稳定排序，如果在相同key值位于两个子树中，可能会调整顺序
     * 建堆 --> 堆顶与最后一个元素交换 --> 调整堆
     *
     * @param nums
     */
    private void heapSort(int[] nums) {
        // 建堆
        heapify(nums);

        // 交换 & 调整
        for (int i = nums.length - 1; i > 0; i--) {
            // 和堆顶元素交换
            swap(nums, 0, i);
            // 向下调整
            siftDown(nums, 0, i);
        }
    }

    /**
     * 建堆，自下而上调整，从最后一个非叶节点开始
     * 正序则建立大根堆，倒序则建立小根堆
     * 时间复杂度O(n)
     *
     * @param nums
     */
    private void buildHeap(int[] nums) {
        // 最后一个非叶子节点的index == nums.length / 2 - 1
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            siftDown(nums, i, nums.length);
        }
    }

    /**
     * 建堆，从第二个节点开始，依次向上调整
     *
     * @param nums
     */
    private void heapify(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            siftUp(nums, i);
        }
    }

    /**
     * 自底向上调整
     *
     * @param nums
     * @param i
     */
    private void siftUp(int[] nums, int i) {
        int temp = nums[i];
        for (int j = (i - 1) / 2; j >= 0; j = (j - 1) / 2) {
            if (temp > nums[j]) {
                nums[i] = nums[j];
                i = j;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }

    /**
     * 调整堆，i和length限定需要调整的堆在数组中的位置
     * 大顶堆的特点：nums[i] >= nums[2 * i + 1] && nums[i] >= nums[2 * i + 2]，即根结点大于左右子节点
     * 时间复杂度O(logn)，和深度有关
     *
     * @param nums
     * @param i 需要要调整的根结点
     * @param length
     */
    private void siftDown(int[] nums, int i, int length) {
        // 待调整堆的root节点，自上而下调整
        int temp = nums[i];
        // k是i的左子节点，一直遍历到叶节点或超过调整范围
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 找到最大的子节点
            if (k + 1 < length && nums[k] < nums[k + 1]) {
                k++;
            }
            // 最大的子节点比根结点大，交换
            // 有可能本次交换导致以k为根结点的子树不满足堆的要求，继续向下调整
            if (temp < nums[k]) {
                nums[i] = nums[k];
                i = k;
            } else {
                // 最大的子节点都比根节点小，直接跳出
                break;
            }
        }
        // 给最后一个赋值给父节点的子节点赋值
        nums[i] = temp;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
