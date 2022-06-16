package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_216_组合总和3_中等
 *
 * @author xucheng.liu
 * @since 2022/6/5
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(k, n, 1, path, res);
        return res;
    }

    private void dfs(int k, int n, int begin, List<Integer> path, List<List<Integer>> res) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝
        if (k <= 0 || n <= 0) {
            return;
        }
        for (int i = begin; i <= 9; i++) {
            // 剪枝
            if (i > n) {
                break;
            }
            path.add(i);
            dfs(k - 1, n - i, i + 1, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
    }
}
