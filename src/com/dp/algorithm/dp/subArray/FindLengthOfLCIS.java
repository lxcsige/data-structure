package com.dp.algorithm.dp.subArray;

/**
 * 674_最长连续递增子序列_简单
 *
 * @author xucheng.liu
 * @since 2022/3/17
 */
public class FindLengthOfLCIS {

    public static void main(String[] args) {

    }

    /**
     * 动态规划，dp[i]表示以nums[i]为后缀的最长连续递增序列的长度
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int res = 1, cur = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                cur++;
            } else {
                cur = 1;
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
