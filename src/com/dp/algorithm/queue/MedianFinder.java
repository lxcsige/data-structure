package com.dp.algorithm.queue;

import java.util.PriorityQueue;

/**
 * leetcode_295_数据流的中位数_困难
 */
public class MedianFinder {

    /**
     * 小于等于中位数的大顶堆
     */
    private PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a);

    /**
     * 大于中位数的小顶堆
     */
    private PriorityQueue<Integer> right = new PriorityQueue<>((a, b) -> a - b);

    public MedianFinder() {

    }

    /**
     * 元素总数为奇数时，left.size() = right.size() + 1
     * 元素总数为偶数时，left.size() = right.size()
     *
     * @param num
     */
    public void addNum(int num) {
        if (left.size() == 0 || num <= left.peek()) {
            left.offer(num);
            // 左侧元素数量过多，需要将堆顶元素弹出并添加到右侧堆中
            if (right.size() + 1 < left.size()) {
                right.offer(left.poll());
            }
        } else {
            right.offer(num);
            // 右侧元素数量过多，需要将堆顶元素弹出并添加到左侧堆中
            if (right.size() > left.size()) {
                left.offer(right.poll());
            }
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        } else {
            return (left.peek() + right.peek()) / 2.0;
        }
    }
}
