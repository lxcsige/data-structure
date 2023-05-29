package com.dp.algorithm.doublepointer;

/**
 * leetcode_26_删除有序数组中的重复项
 */
public class RemoveDuplicates {

    /**
     * 思路：双指针，一个遍历指针，一个插入指针，后者用于分割数组，插入指针之前的数组中不存在重复元素
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        // 指向有效数组的右边界
        int i = 0;
        for (int j = 1; j < n; j++) {
            // 和有效数组的边界元素不同，说明数组中没有，可以插入
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}
