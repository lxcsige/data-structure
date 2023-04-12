package com.dp.algorithm.prefixsum;

/**
 * leetcode_1248_统计优美数组_中等
 */
public class NumberOfSubArrays {

    /**
     * prefix[i]表示nums[0..i]之间的奇数个数
     * nums[i..j]之间的奇数个数 = prefix[i] - prefix[j-1]
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubArrays(int[] nums, int k) {
        int n = nums.length;
        // 下标表示前缀和，即奇数的个数，元素表示该前缀和出现的次数
        int[] cnt = new int[n + 1];
        cnt[0] = 1;
        int pre = 0;
        int res = 0;
        for (int num : nums) {
            pre += num & 1;
            res += pre > k ? cnt[pre - k] : 0;
            cnt[pre]++;
        }
        return res;
    }
}
