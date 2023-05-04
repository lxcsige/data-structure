package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_78_子集_中等
 */
public class SubSets {

    /**
     * 不存在重复元素，{1,2}和{2,1}算同一种子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, path, res);
        return res;
    }

    private void backtrack(int[] nums, int begin, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
