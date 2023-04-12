package com.dp.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode_15_三数之和_中等
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class ThreeSum {

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 从小到大排序，避免重复结果
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int a = 0; a <= n - 3; a++) {
            // 必然会出现重复组合，跳过
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            int b = a + 1;
            int c = n - 1;
            while (b < c) {
                int sum = nums[a] + nums[b] + nums[c];
                if (sum == 0) {
                    List<Integer> combination = new ArrayList<>();
                    combination.add(nums[a]);
                    combination.add(nums[b]);
                    combination.add(nums[c]);
                    res.add(combination);
                    // 去掉重复元素
                    while (b < c && nums[b] == nums[b + 1]) {
                        b++;
                    }
                    while (b < c & nums[c] == nums[c - 1]) {
                        c--;
                    }
                    b++;
                    c--;
                } else if (sum > 0) {
                    c--;
                } else {
                    b++;
                }
            }
        }

        return res;
    }

    /**
     * 回溯算法，可能存在负数，无法剪枝，相当于暴力解法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        // 排序，防止出现重复组合
        Arrays.sort(nums);
        backtrack(nums, 0, 0, combination, res);
        return res;
    }

    private void backtrack(int[] nums, int sum, int startIndex, List<Integer> combination, List<List<Integer>> res) {
        if (combination.size() == 3) {
            if (sum == 0) {
                res.add(new ArrayList<>(combination));
            }
            return;
        }
        for (int i = startIndex; i < nums.length - (3 - combination.size()); i++) {
            if (i > startIndex && nums[i] == nums[i-1]) {
                continue;
            }
            combination.add(nums[i]);
            backtrack(nums, sum - nums[i], i + 1, combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}
