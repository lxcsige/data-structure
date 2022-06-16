package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode_39_组合总和_中等
 *
 * @author xucheng.liu
 * @since 2022/5/26
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum main = new CombinationSum();
        List<List<Integer>> res = main.combinationSum3(new int[]{10,1,2,7,6,1,5}, 8);
        System.out.println(res);
    }

    /**
     * 无重复元素，无限制重复选取，不同组合（注意不是排列，不同元素顺序算一种组合），以任意顺序返回
     *
     * 递归-每次新建一个list保存组合
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    /**
     * target每次减去加入路径的元素值
     * index表示下一轮搜索的起点index，用于保证元素顺序
     *
     * @param candidates
     * @param target
     * @param index
     * @param combination
     * @param res
     */
    private void combinationSum(int[] candidates, int target, int index,
                                List<Integer> combination, List<List<Integer>> res) {
        if (target == 0) {
            res.add(combination);
        }
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (candidate <= target) {
                List<Integer> tmp = new ArrayList<>(combination);
                tmp.add(candidate);
                combinationSum(candidates, target - candidate, i, tmp, res);
            }
        }
    }

    /**
     * 回溯算法
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        combinationSum2(candidates, target, 0, combination, res);
        return res;
    }

    private void combinationSum2(int[] candidates, int target, int index,
                                List<Integer> combination, List<List<Integer>> res) {
        if (target < 0 || index >= candidates.length) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(combination));
        }
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (candidate <= target) {
                combination.add(candidate);
                combinationSum2(candidates, target - candidate, i, combination, res);
                combination.remove(combination.size() - 1);
            }
        }
    }

    /**
     * 回溯+剪枝
     * 如果 target 减去一个数得到负数，那么减去一个更大的树依然是负数，同样搜索不到结果
     * 因此，可以对输入数组进行排序，添加相关逻辑达到进一步剪枝的目的
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum3(candidates, target, 0, combination, res);
        return res;
    }

    private void combinationSum3(int[] candidates, int target, int index,
                                 List<Integer> combination, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (index >= candidates.length) {
            return;
        }
        if(target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            // 剪枝
            if (candidate > target) {
                break;
            }
            combination.add(candidate);
            combinationSum3(candidates, target - candidate, i, combination, res);
            // 回溯
            combination.remove(combination.size() - 1);
        }
    }
}
