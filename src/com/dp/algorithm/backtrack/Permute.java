package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_46_全排列_中等
 */
public class Permute {

    /**
     * 不包含重复数字
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, permutation, res);
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> permutation, List<List<Integer>> res) {
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            permutation.add(nums[i]);
            backtrack(nums, used, permutation, res);
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }
}
