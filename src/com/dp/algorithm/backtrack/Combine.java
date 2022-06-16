package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_77_组合_中等
 *
 * @author xucheng.liu
 * @since 2022/6/6
 */
public class Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, 1, path, res);
        return res;
    }

    private void backtrack(int n, int k, int begin, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝，k-path.size()表示还需要的元素个数，n-(k-path.size())+1表示起始index的最大值
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtrack(n, k, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
