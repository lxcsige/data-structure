package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode_491_递增子序列_中等
 */
public class FindSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> subsequence = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, subsequence, res);
        return res;
    }

    private void backtrack(int[] nums, int begin, List<Integer> subsequence, List<List<Integer>> res) {
        if (subsequence.size() >= 2) {
            res.add(new ArrayList<>(subsequence));
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = begin; i < nums.length; i++) {
            if (!subsequence.isEmpty() && nums[i] < subsequence.get(subsequence.size() - 1)
                    || !visited.add(nums[i])) {
                continue;
            }
            subsequence.add(nums[i]);
            backtrack(nums, i + 1, subsequence, res);
            subsequence.remove(subsequence.size() - 1);
        }
    }
}
