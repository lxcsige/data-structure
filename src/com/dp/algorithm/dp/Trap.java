package com.dp.algorithm.dp;

/**
 * leetcode_42_接雨水_困难
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class Trap {

    public int trap(int[] height) {
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int left = 0;
            for (int j = 0; j < i; j++) {
                if (left < height[j]) {
                    left = height[j];
                }
            }
            int right = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (right < height[j]) {
                    right = height[j];
                }
            }
            int min = Math.min(left, right);
            if (min > height[i]) {
                res += min - height[i];
            }
        }
        return res;
    }

    public int trap2(int[] height) {
        int res = 0;
        int len = height.length;
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        for (int i = 1; i < len - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i-1]);
        }
        for (int i = len - 2; i > 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i+1]);
        }
        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i]) {
                res += min - height[i];
            }
        }
        return res;
    }
}
