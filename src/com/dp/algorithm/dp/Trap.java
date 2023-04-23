package com.dp.algorithm.dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_42_接雨水_困难
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class Trap {

    /**
     * 动态规划
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int res = 0;
        int len = height.length;
        // maxLeft[i]保存的是i左边最大高度
        int[] maxLeft = new int[len];
        // maxRight[i]保存的是i右边最大高度
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

    /**
     * 双指针
     * 计算某个位置能接多少雨水：min(iLeftMax, iRightMax) - height[i]
     * 对于2个位置i和j，i < j，必然有iLeftMax <= jLeftMax，iRightMax >= jRightMax
     * 如果iLeftMax <= jRightMax，则iRightMax >= iLeftMax，此时可以计算i位置可以接多少雨水
     * 如果iLeftMax > jRightMax，则jLeftMax > jRightMax，此时可以计算j位置可以接多少雨水
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int left = 1, right = height.length - 2;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while (left <= right) {
            leftMax = Math.max(height[left - 1], leftMax);
            rightMax = Math.max(height[right + 1], rightMax);
            if (leftMax <= rightMax) {
                if (leftMax > height[left]) {
                    res += leftMax - height[left];
                }
                left++;
            } else {
                if (rightMax > height[right]) {
                    res += rightMax - height[right];
                }
                right--;
            }
        }
        return res;
    }

    /**
     * 单调栈，从栈底到栈顶按照高度单调递减
     * 对于某一个位置i，找到左右两边第一个比它高的位置
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            // 判断栈顶是否小于height[i]，是则出栈，直到栈顶大于等于height[i]，保证单调性
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int cur = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                // 计算(left, i)区间
                int curWidth = i - left - 1;
                int curHeight = Math.min(height[left], height[i]) - height[cur];
                res += curWidth * curHeight;
            }
            stack.push(i);
        }
        return res;
    }
}
