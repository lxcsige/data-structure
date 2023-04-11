package com.dp.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxucheng
 * @since 2022/7/22
 */
public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int preSum = 0;
        Map<Integer, Integer> preSum2CountMap = new HashMap<>();
        for (int num : nums) {
            preSum += num;
            // 当前的前缀和刚好等于k
            if (preSum == k) {
                res++;
            }
            if (preSum2CountMap.containsKey(preSum - k)) {
                res += preSum2CountMap.get(preSum - k);
            }
            preSum2CountMap.put(preSum, preSum2CountMap.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }
}
