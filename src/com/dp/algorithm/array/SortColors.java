package com.dp.algorithm.array;

/**
 * leetcode_75_颜色分类_中等
 */
public class SortColors {

    /**
     * 双指针，p指向0的边界的下一个元素，q指向2的边界的上一个元素
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        // 0的区间：[0, p)
        // 2的区间：(q, n - 1]
        int p = 0, q = n - 1;
        for (int i = 0; i <= q; i++) {
            // 0
            if (nums[i] == 0) {
                nums[i] = nums[p];
                nums[p] = 0;
                p++;
            }
            // 2
            else if (nums[i] == 2) {
                nums[i] = nums[q];
                nums[q] = 2;
                q--;
                // 如果换了个0或2到i位置，那么下次循环还需要再次处理该位置
                if (nums[i] != 1) {
                    i--;
                }
            }
        }
    }
}
