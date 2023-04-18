package com.dp.algorithm.doublepointer;

public class RemoveElement {

    /**
     * 双指针一：l指向头，r指向尾
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            // 找到左边下一个等于val的元素
            while (l <= r && nums[l] != val) {
                l++;
            }
            // 找到右边下一个不等于val的元素
            while (l <= r && nums[r] == val) {
                r--;
            }
            if (l < r) {
                // 交换l和r
                nums[l++] = nums[r--];
            }
        }
        return l;
    }

    /**
     * 通过j指针将数组分割为等于val和不等于val两部分
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
