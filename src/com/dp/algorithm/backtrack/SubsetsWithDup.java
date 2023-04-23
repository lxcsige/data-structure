package com.dp.algorithm.backtrack;

import java.util.*;

/**
 * leetcode_90_子集II_中等
 */
public class SubsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, subset, res);
        return res;
    }

    private void backtrack(int[] nums, int begin, List<Integer> subset, List<List<Integer>> res) {
        res.add(new ArrayList<>(subset));
        for (int i = begin; i < nums.length; i++) {
            if (i > begin && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            backtrack(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * 使用Set进行去重，同样需要提前排序
     *
     * eg：{2,1,2,2}，如果不排序可能出现{2,1}和{1,2}
     *
     * @param nums
     * @param begin
     * @param subset
     * @param res
     */
    public void backtrack2(int[] nums, int begin, List<Integer> subset, List<List<Integer>> res) {
        res.add(new ArrayList<>(subset));
        Set<Integer> visited = new HashSet<>();
        for (int i = begin; i < nums.length; i++) {
            if (!visited.add(nums[i])) {
                continue;
            }
            subset.add(nums[i]);
            backtrack(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }
}
