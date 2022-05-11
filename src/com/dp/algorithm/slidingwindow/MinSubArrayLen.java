package com.dp.algorithm.slidingwindow;

/**
 * leetcode_209_长度最小的子数组_中等
 *
 * @author xucheng.liu
 * @since 2022/5/2
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(4, new int[]{1,4,4}));
    }

    public static int minSubArrayLen(int target, int[] nums) {
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
