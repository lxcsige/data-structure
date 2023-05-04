package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 股票价格跨度
 */
public class StockSpanner {

    private Deque<int[]> stack = new ArrayDeque<>();

    private int cur;

    public StockSpanner() {

    }

    /**
     * 单调栈，主要思路就是找到当前元素左边第一个大于它的位置
     * 严格单调递减
     *
     * @param price
     * @return
     */
    public int next(int price) {
        // 找到第一个大于price的位置或直到栈为空
        while (!stack.isEmpty() && price >= stack.peek()[1]) {
            stack.poll();
        }
        // 栈为空，说明价格单调递增，前面几天都比今日价格低
        int prevIndex = stack.isEmpty() ? -1 : stack.peek()[0];
        int res = cur - prevIndex;
        // 入栈
        stack.push(new int[]{cur++, price});
        return res;
    }
}
