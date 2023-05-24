package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode_40_组合总和2_中等
 *
 * @author xucheng.liu
 * @since 2022/6/5
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        CombinationSum2 main = new CombinationSum2();
        List<List<Integer>> res = main.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
    }

    /**
     * 数组可能存在重复元素，每个数字在每个组合中只能使用一次
     * eg: candidates = [10,1,2,7,6,1,5], target = 8
     * [1,1,6]组合也符合题意，因为1在数组中出现了2次
     * 组合内允许重复，组合之间不允许重复
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        // 排序，用于剪枝
        Arrays.sort(candidates);
        dfs(candidates, target, 0, combination, res);
        return res;
    }

    private void dfs(int[] candidates, int target, int begin, List<Integer> combination, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            // 剪枝，后面的元素都大于等于candidates[i]，因此可以直接跳过后续的分支选择
            if (candidates[i] > target) {
                break;
            }
            // 剪枝，同一层相同数值的结点，从第2个开始，候选数更少，结果一定发生重复，因此可以跳过，防止最终结果出现重复组合
            if (i > begin && candidates[i] == candidates[i-1]) {
                continue;
            }
            combination.add(candidates[i]);
            System.out.println("递归之前：path = " + combination
                    + ", target = " + (target - candidates[i]) + ", begin = " + (i + 1));
            dfs(candidates, target - candidates[i], i + 1, combination, res);
            combination.remove(combination.size() - 1);
            System.out.println("递归之后：path = " + combination);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[candidates.length];
        // 排序，用于剪枝
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, used, combination, res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int beginIndex,
                           boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = beginIndex; i <= candidates.length; i++) {
            // 剪枝，后续都大于target，直接跳出循环
            if (candidates[i] > target) {
                break;
            }
            // 判断组合之间是否出现重复
            // used数组用于区分相同元素的选择是在同一分支还是不同分支
            if (i > 0 && candidates[i] == candidates[i-1] && !used[i-1]) {
                continue;
            }
            used[i] = true;
            path.add(candidates[i]);
            System.out.println("递归之前，path = " + path);
            backtrack(candidates, target - candidates[i], i + 1, used, path, res);
            // 回溯
            used[i] = false;
            path.remove(path.size() - 1);
            System.out.println("递归之后，path = " + path);
        }
    }
}
