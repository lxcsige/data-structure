package com.dp.algorithm.slidingwindow;

/**
 * leetcode_209_长度最小的子数组_中等
 *
 * @author xucheng.liu
 * @since 2022/5/2
 */
public class MinSubArrayLen {

    /**
     * 滑动窗口，因为正整数的前提，因此才可以在sum >= target的情况下，进行窗口左移
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, sum = 0, res = Integer.MAX_VALUE;
        for (; right < nums.length; right++) {
            sum += nums[right];
            // sum >= target，尝试移动左指针，直到sum < target
            while (sum >= target && left <= right) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
