package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_22_括号生成_中等
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder combination = new StringBuilder();
        dfs(combination, n, n, res);
        return res;
    }

    private void dfs(StringBuilder combination, int left, int right, List<String> res) {
        // base case
        if (left == 0 && right == 0) {
            res.add(combination.toString());
            return;
        }
        // 剪枝，剩余的左括号数量大于右括号数量，当前括号组合一定不合法
        if (left > right) {
            return;
        }
        // 添加左括号
        if (left > 0) {
            dfs(combination.append("("), left - 1, right, res);
            combination.deleteCharAt(combination.length() - 1);
        }
        // 添加右括号
        if (left <= right) {
            dfs(combination.append(")"), left, right - 1, res);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
