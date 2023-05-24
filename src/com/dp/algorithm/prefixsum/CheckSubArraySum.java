package com.dp.algorithm.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_523_连续的子数组和_中等
 */
public class CheckSubArraySum {

    public boolean checkSubArraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        Map<Integer, Integer> remainder2IdxMap = new HashMap<>();
        remainder2IdxMap.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < n; i++) {
            remainder = (remainder + nums[i]) % k;
            if (remainder2IdxMap.containsKey(remainder)) {
                int prevIndex = remainder2IdxMap.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                remainder2IdxMap.put(remainder, i);
            }
        }
        return false;
    }
}
