package com.dp.algorithm.doublepointer;

/**
 * leetcode_11_盛最多水的容器_中等
 *
 * @author liuxucheng
 * @since 2022/8/9
 */
public class MaxArea {

    /**
     * 双指针
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, res = 0, tmp;
        while (left < right) {
            if (height[left] <= height[right]) {
                tmp = (right - left) * height[left];
                left++;
            } else {
                tmp = (right - left) * height[right];
                right--;
            }
            res = Math.max(res, tmp);
        }
        return res;
    }
}
