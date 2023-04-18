package com.dp.algorithm.doublepointer;

/**
 * leetcode_80_删除有序数组中的重复项II_中等
 */
public class RemoveDuplicatesII {

    public int removeDuplicates(int[] nums) {
        // 边界
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        // 有效数组的最后一个位置
        int j = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[j - 1] != nums[i]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }
}
