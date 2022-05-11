package com.dp.algorithm.dp.subSequence;

import java.util.ArrayList;
import java.util.List;

/**
 * 300_最长递增子序列_中等
 *
 * @author liuxucheng
 * @since 2021/5/20
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        LengthOfLIS test =new LengthOfLIS();
    }

    /**
     * dp
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // dp[i]表示以nums[i]为后缀的递增子序列的长度
        int[] dp = new int[nums.length];
        // 递增子序列的最小长度为1
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * 贪心+二分查找
     *
     * @param nums
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // res[j]表示前i个元素中长度为j的递增子序列的末尾元素的最小值
        List<Integer> res = new ArrayList<>();
        // base case，第一个元素只能组成长度为1的递增子序列，此时末尾元素为nums[0]
        res.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > res.get(res.size() - 1)) {
                res.add(nums[i]);
            } else {
                res.set(binarySearch(res, nums[i]), nums[i]);
            }
        }

        return res.size();
    }

    /**
     * 二分查找，找到第一个不小于target的元素
     *
     * @param list
     * @param target
     * @return
     */
    private int binarySearch(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int mid;
        while (low < high) {
            mid = (high + low) / 2;
            if (target > list.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
