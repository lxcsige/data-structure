package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_17_电话号码的字母组合_中等
 *
 * @author xucheng.liu
 * @since 2022/6/8
 */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        String[] stringArr = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder();
        backTrack(digits, stringArr, 0, res, sb);
        return res;
    }

    private void backTrack(String digits, String[] stringArr, int index, List<String> res, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String str = stringArr[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            backTrack(digits, stringArr, index + 1, res, sb);
            // 回溯
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
