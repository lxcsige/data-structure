package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_131_分割回文串_中等
 *
 * reviewed at 2023.04.23
 *
 * @author liuxucheng
 * @since 2022/6/17
 */
public class Partition {

    public List<List<String>> partition(String s) {
        List<String> combination = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        backtrack(s, 0, combination, res);
        return res;
    }

    /**
     * 判断回文时存在重复计算
     *
     * @param s
     * @param beginIndex
     * @param combination
     * @param res
     */
    private void backtrack(String s, int beginIndex,
                           List<String> combination, List<List<String>> res) {
        if (beginIndex == s.length()) {
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = beginIndex; i < s.length(); i++) {
            if (!isPalindrome(s, beginIndex, i)) {
                continue;
            }
            combination.add(s.substring(beginIndex, i + 1));
            backtrack(s, i + 1, combination, res);
            combination.remove(combination.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int begin, int end) {
        while (begin <= end) {
            if (s.charAt(begin) != s.charAt(end)) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }

    /**
     * 动态规划预处理，防止判断回文串时的重复计算
     *
     * @param s
     * @return
     */
    public List<List<String>> partition2(String s) {
        List<String> combination = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        // 动态规划预处理
        boolean[][] dp = isPalindrome(s);
        backtrack2(s, 0, dp, combination, res);
        return res;
    }

    private boolean[][] isPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        // 初始化边界
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        // 注意遍历顺序
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp;
    }

    private void backtrack2(String s, int beginIndex, boolean[][] dp,
                           List<String> combination, List<List<String>> res) {
        if (beginIndex == s.length()) {
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = beginIndex; i < s.length(); i++) {
            if (!dp[beginIndex][i]) {
                continue;
            }
            combination.add(s.substring(beginIndex, i + 1));
            backtrack2(s, i + 1, dp, combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}
