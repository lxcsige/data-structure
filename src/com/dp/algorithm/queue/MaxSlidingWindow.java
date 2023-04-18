package com.dp.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * leetcode_239_滑动窗口最大值_困难
 *
 * @author liuxucheng
 * @since 2023/4/18
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        MaxSlidingWindow test = new MaxSlidingWindow();
        test.maxSlidingWindow(new int[]{9,10,9,-7,-4,-8,2,-6}, 5);
    }

    /**
     * 大根堆
     * 时间复杂度O(nlogn)
     * 在单调递增的情况下，元素会一直留在堆中
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        int n = nums.length;
        // 边界：k等于数组长度，直接返回堆顶
        if (n == k) {
            return new int[]{queue.poll()[0]};
        }
        int[] res = new int[n - k + 1];
        res[0] = queue.peek()[0];
        // 逐个调整堆
        for (int i = k; i < n; i++) {
            // 进堆
            queue.offer(new int[]{nums[i], i});
            // 确保堆顶元素在窗口内
            // 注意这里必须用while
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            // 将调整后的堆顶元素加入到结果集中
            res[i - k + 1] = queue.peek()[0];
        }
        return res;
    }

    /**
     * 单调队列，存的是元素值，此时队列并非严格单调递减
     *
     * 思路：
     * 1. 如果从窗口滑出的元素等于队头，那么弹出该元素
     * 2. 如果队尾元素小于窗口滑入的元素，那么弹出队尾，直到队尾不小于滑入的元素
     *
     * i < j，i在窗口中，j就一定在窗口中
     * nums[i] < nums[j]，nums[i]一定不会是滑动窗口中的最大值，可以直接移除
     *
     * 思想：队列中仅保留对结果有用的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offer(nums[i]);
        }
        int[] res = new int[n - k + 1];
        res[0] = deque.peek();
        for (int i = k; i < n; i++) {
            if (nums[i - k] == deque.peek()) {
                deque.poll();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offer(nums[i]);
            res[i - k + 1] = deque.peek();
        }
        return res;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        int[] res = new int[n - k + 1];
        res[0] = nums[deque.peek()];
        for (int i = k; i < n; i++) {
            if (i - k == deque.peek()) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            res[i - k + 1] = nums[deque.peek()];
        }
        return res;
    }
}
