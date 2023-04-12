package com.dp.algorithm.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_560_和为K的子数组_中等
 */
public class SubarraySum {

    /**
     * 前缀和+哈希表
     * sum(i..j) = sum(0..j) - sum(0..i-1) = preSum(j) - preSum(i - 1) = k
     * preSum(i - 1) = preSum(j) - k
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        // key - 前缀和
        // value - 对应的次数
        Map<Integer, Integer> preSum2CntMap = new HashMap<>();
        // 为了解决preSum(i) == k的情况，此时需要找到前缀和为0的次数，如果没有这一步，那么结果就有问题了
        preSum2CntMap.put(0, 1);
        int count = 0;
        int preSum = 0;
        for (int num : nums) {
            preSum += num;
            if (preSum2CntMap.containsKey(preSum - k)) {
                count += preSum2CntMap.get(preSum - k);
            }
            preSum2CntMap.put(preSum, preSum2CntMap.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
