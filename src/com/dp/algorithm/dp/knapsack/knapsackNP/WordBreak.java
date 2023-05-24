package com.dp.algorithm.dp.knapsack.knapsackNP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode_139_单词拆分_中等
 */
public class WordBreak {

    /**
     * 有点像完全背包问题
     * dp[i] = dp[j] && wordDict.contains(s.substring(j, i)), j < i
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        // dp[i]表示长度i的子串是否可以用字典中出现的单词拼出来
        boolean[] dp = new boolean[s.length() + 1];
        // 初始边界
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
