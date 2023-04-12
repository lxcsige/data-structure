package com.dp.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_1_两数之和_简单
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> num2IndexMap = new HashMap<>();
        num2IndexMap.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (num2IndexMap.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = num2IndexMap.get(target - nums[i]);
                return res;
            }
            num2IndexMap.put(nums[i], i);
        }
        return res;
    }
}
