package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_93_复原IP地址_中等
 */
public class RestoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> combination = new ArrayList<>();
        List<String> res = new ArrayList<>();
        backtrack(s, 0, combination, res);
        return res;
    }

    private void backtrack(String s, int begin, List<String> combination, List<String> res) {
        // 终止条件
        if (combination.size() == 4) {
            if (begin == s.length()) {
                res.add(String.join(".", combination));
            }
            return;
        }
        // 优化，最多3位
        for (int i = begin; i < s.length() && i < begin + 3; i++) {
            String tmp = s.substring(begin, i + 1);
            if (!isValid(tmp)) {
                continue;
            }
            combination.add(tmp);
            backtrack(s, i + 1, combination, res);
            combination.remove(combination.size() - 1);
        }
    }

    private boolean isValid(String s) {
        // 1位
        if (s.length() == 1) {
            return true;
        }
        // 前缀0
        if (s.charAt(0) == '0') {
            return false;
        }
        return Integer.parseInt(s) <= 255;
    }
}
