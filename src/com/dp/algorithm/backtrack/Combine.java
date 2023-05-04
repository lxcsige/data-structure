package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_77_组合_中等
 *
 * reviewed at 2023.04.21
 *
 * @author xucheng.liu
 * @since 2022/6/6
 */
public class Combine {

    /**
     * 不存在重复元素
     *
     * @param n [1,n]
     * @param k 组合中的元素数量
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, 1, path, res);
        return res;
    }

    private void backtrack(int n, int k, int begin, List<Integer> path, List<List<Integer>> res) {
        // 终止条件
        if (k == 0) {
            // 加入结果集
            res.add(new ArrayList<>(path));
            return;
        }
        // 优化：i <= n - (k - path.size())
        // 已经选择的元素个数：path.size()
        // 还需要的元素个数为: k - path.size()
        // 起始位置最多就是n - (k - path.size())
        for (int i = begin; i <= n - k + 1; i++) {
            path.add(i);
            backtrack(n, k - 1, i + 1, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
    }
}
