package com.dp.algorithm.backtrack;

import java.util.*;

/**
 * leetcode_47_全排列II_中等
 */
public class PermuteUnique {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, visited, permutation, res);
        return res;
    }

    private void backtrack(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> res) {
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
        }
        for (int i = 0; i < nums.length; i++) {
            // 说明同一树枝上nums[i]已经被使用过
            // 树枝可以理解为一条递归路径
            if (visited[i]) {
                continue;
            }
            // 同一层存在重复节点，只保留第一个
            // visited[i] == false && visited[i - 1] == false：说明之前都没有出现过，在该层都可以选
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            backtrack(nums, visited, permutation, res);
            permutation.remove(permutation.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 同一层使用set去重，可以不用提前排序，因为全排序需要顺序
     *
     * @param nums
     * @param visited
     * @param permutation
     * @param res
     */
    private void backtrack2(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> res) {
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || !set.add(nums[i])) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            backtrack(nums, visited, permutation, res);
            permutation.remove(permutation.size() - 1);
            visited[i] = false;
        }
    }
}
