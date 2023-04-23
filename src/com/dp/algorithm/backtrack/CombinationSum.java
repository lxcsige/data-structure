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
        List<List<Integer>> res = main.combinationSum(new int[]{10,1,2,7,6,1,5}, 8);
        System.out.println(res);
    }

    /**
     * 无重复元素，无限制重复选取，不同组合（注意不是排列，不同元素顺序算一种组合），以任意顺序返回
     * 同时不限制组合内的元素数量
     *
     * 回溯+排序剪枝
     * 如果 target 减去一个数得到负数，那么减去一个更大的树依然是负数，同样搜索不到结果
     * 因此，可以对输入数组进行排序，添加相关逻辑达到进一步剪枝的目的
     *
     * 注意，对于求和的组合问题，排序剪枝是一种常见的优先手段
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        // 排序优化
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, combination, res);
        return res;
    }

    private void combinationSum(int[] candidates, int target, int startIndex,
                                 List<Integer> combination, List<List<Integer>> res) {
        // 终止条件
        if(target == 0) {
            // 加入结果集
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            int candidate = candidates[i];
            // 剪枝，由于数组有序，后面的元素只可能比现在的还大，不可能满足条件
            if (candidate > target) {
                break;
            }
            combination.add(candidate);
            // 注意这里的startIndex传入的是i，表示元素可以重复选取
            combinationSum(candidates, target - candidate, i, combination, res);
            // 回溯
            combination.remove(combination.size() - 1);
        }
    }
}
