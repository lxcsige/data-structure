package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_316_去除重复字母_中等
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        RemoveDuplicateLetters test = new RemoveDuplicateLetters();
        test.removeDuplicateLetters("bbcaac");
    }

    public String removeDuplicateLetters(String s) {
        int[] cnts = new int[26];
        int letterCnt = 0;
        char[] chs = s.toCharArray();
        int n = s.length();
        // 统计每个字母出现的次数
        for (char ch : chs) {
            if (cnts[ch - 'a'] == 0) {
                letterCnt++;
            }
            cnts[ch - 'a']++;
        }
        // 是否在栈中
        boolean[] visits = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!visits[chs[i] - 'a']) {
                // 确保stack.peek()后面仍然可能出现，只有这样才能移除
                while (!stack.isEmpty() && chs[i] < stack.peek() && cnts[stack.peek() - 'a'] > 0) {
                    char top = stack.pop();
                    visits[top - 'a'] = false;
                }
                stack.push(chs[i]);
                visits[chs[i] - 'a'] = true;
            }
            // 剩余出现次数-1
            cnts[chs[i] - 'a']--;
        }
        char[] res = new char[letterCnt];
        int index = letterCnt - 1;
        while (!stack.isEmpty()) {
            res[index--] = stack.pop();
        }
        return new String(res);
    }
}
