package com.dp.algorithm.prefixsum;

/**
 * leetcode_974_和可被K整除的子数组_中等
 */
public class SubArraysDivByK {

    public int subArraysDivByK(int[] nums, int k) {
        // 下标为余数，元素为前缀和取模余数为下标的次数
        int[] cnt = new int[k];
        // 对应前缀和可以被k整除的情况
        cnt[0] = 1;
        int res = 0, preSum = 0;
        for (int num : nums) {
            preSum += num;
            // 注意，对负数取模仍然会得到负数
            int remainder = (preSum % k + k) % k;
            res += cnt[remainder];
            cnt[remainder]++;
        }
        return res;
    }
}
