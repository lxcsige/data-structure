package com.dp.algorithm.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * leetcode_480_滑动窗口中位数_困难
 */
public class MedianSlidingWindow {

    public static void main(String[] args) {
        MedianSlidingWindow test = new MedianSlidingWindow();
        test.medianSlidingWindow(new int[]{-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648}, 3);
    }

    private PriorityQueue<Integer> l = new PriorityQueue<>((a, b) -> b.compareTo(a));

    private PriorityQueue<Integer> r = new PriorityQueue<>((a, b) -> a.compareTo(b));

    private Map<Integer, Integer> delayed = new HashMap<>();

    /**
     * 剔除待删除元素后的左堆大小
     */
    private int lSize;

    /**
     * 剔除删除元素后的右堆大小
     */
    private int rSize;

    /**
     * 难点在于删除元素，思路是延迟删除，即等到成为堆顶时再删
     * 直接删除虽然也可以得到正确答案，但是会超时
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n - k + 1];
        // 第1个窗口
        for (int i = 0; i < k; i++) {
            addNum(nums[i]);
        }
        res[0] = getMedian();
        for (int i = k; i < n; i++) {
            addNum(nums[i]);
            deleteNum(nums[i - k]);
            res[i - k + 1] = getMedian();
        }
        return res;
    }

    private void addNum(int num) {
        if (l.isEmpty() || num <= l.peek()) {
            l.offer(num);
            lSize++;
        } else {
            r.offer(num);
            rSize++;
        }
        balance();
    }

    private void deleteNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= l.peek()) {
            lSize--;
            // 如果元素就在堆顶，直接删除
            if (num == l.peek()) {
                prune(l);
            }
        } else {
            rSize--;
            // 如果元素就在堆顶，直接删除
            if (num == r.peek()) {
                prune(r);
            }
        }
        balance();
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            if (!delayed.containsKey(heap.peek())) {
                break;
            }
            int peek = heap.poll();
            int cnt = delayed.get(peek);
            delayed.put(peek, --cnt);
            if (cnt == 0) {
                delayed.remove(peek);
            }
        }
    }

    private void balance() {
        if (lSize > rSize + 1) {
            r.offer(l.poll());
            lSize--;
            rSize++;
            // 确保l的新堆顶元素不需要被删除
            prune(l);
        } else if (lSize < rSize) {
            l.offer(r.poll());
            lSize++;
            rSize--;
            // 确保r的新堆顶元素不需要被删除
            prune(r);
        }
    }

    private double getMedian() {
        if (lSize == rSize) {
            return ((double) l.peek() + r.peek()) / 2;
        }
        return l.peek();
    }
}
