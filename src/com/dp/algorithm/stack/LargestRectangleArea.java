package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_84_柱状图中最大的矩形_困难
 */
public class LargestRectangleArea {

    public static void main(String[] args) {
        LargestRectangleArea test = new LargestRectangleArea();
        test.largestRectangleArea(new int[]{3,3,4});
    }

    /**
     * 单调栈（非严格单调递增），枚举高度
     * 矩形面积 = width * height
     * 对于height[i]，到左边第一个小于height[i]的位置，该区间内能找到的矩形高度就是height[i]
     * 同理，到右边第一个小于height[i]的位置，该区间内能找到的矩形高度也是height[i]
     * 因此，只要找到左右两边第一个小于height[i]的位置，就可以确定以height[i]为高度的矩形面积
     *
     * 注意点：
     * 1. 哨兵，要不然可能出现栈为空（对应递减的情况）以及遍历后栈中仍然有元素
     * 2. 入栈条件：栈顶高度严格小于当前高度，相等时并不能判断该高度是否可以继续向右扩展
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        // 引入哨兵处理边界，heights[0] = 0，heights[n + 1] = 0
        int[] newHeights = new int[heights.length + 2];
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;

        int res = 0;
        // 栈，单调递增
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < heights.length; i++) {
            // 说明栈顶位置的宽度已经不能向右扩展了
            while (heights[stack.peek()] > heights[i]) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }
        return res;
    }
}
