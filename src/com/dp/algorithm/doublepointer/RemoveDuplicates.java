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
        if (nums.length < 2) {
            return nums.length;
        }
        // 插入指针
        int i = 1;
        // 遍历指针
        int j = 1;
        while (j < nums.length) {
            // 和前一个元素相同，不需要插入到新位置
            if (nums[j - 1] == nums[j]) {
                j++;
            }
            // 和前一个元素不同，需要插入到新位置
            else {
                nums[i++] = nums[j++];
            }
        }
        return i;
    }
}
