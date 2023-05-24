package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_456_132模式_中等
 */
public class Find132Pattern {

    /**
     * 单调栈，从后向前遍历，维护一个单调递减栈，同时维护一个k，表示出栈的最大元素
     * 由于k保存的是出栈的元素，那么一定小于栈底元素，只要再找到一个元素i，使得它比k小，就可以得到一个132模式的子序列
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        // 出栈元素的最大值，需要保证k和栈顶元素不同
        int k = Integer.MIN_VALUE;
        // 非严格单调递减栈
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            // 只有出栈操作才能更新，而只要存在出栈，那么栈顶元素必然大于k，此时nums[i] < k，因此可以得到132模式子序列
            if (nums[i] < k) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                k = Math.max(k, stack.pop());
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
