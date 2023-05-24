package com.dp.algorithm.stack.rectangle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_85_最大矩形_困难
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        int n = matrix[0].length;
        int res = 0;
        int[] heights = new int[n];
        for (char[] chars : matrix) {
            for (int j = 0; j < n; j++) {
                if (chars[j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }

    private int largestRectangleArea(int[] heights) {
        // 处理边界
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }
        return res;
    }
}
