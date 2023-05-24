package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_1475_商品折扣后的最终价格_简单
 */
public class FinalPrices {

    /**
     * 其实就是找下一个比它更小的元素
     *
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] res = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            res[i] = prices[i];
        }
        // 非严格单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n - 1; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int peek = stack.pop();
                res[peek] = prices[peek] - prices[i];
            }
            stack.push(i);
        }
        return res;
    }
}
