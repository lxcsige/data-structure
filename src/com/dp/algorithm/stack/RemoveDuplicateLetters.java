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
        int[] counts = new int[26];
        int n = s.length();
        // 统计每个字母出现的次数
        for (int i = 0; i < n; i++) {
            counts[s.charAt(i) - 'a']++;
        }
        // 是否在栈中
        boolean[] visits = new boolean[26];
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 栈中不存在才能入栈
            if (!visits[ch - 'a']) {
                // 当前字符小于栈顶字符，如果栈顶字符后面还可能出现，那么可以出栈
                while (stack.length() > 0 && ch < stack.charAt(stack.length() - 1)
                        && counts[stack.charAt(stack.length() - 1) - 'a'] > 0) {
                    visits[stack.charAt(stack.length() - 1) - 'a'] = false;
                    stack.deleteCharAt(stack.length() - 1);
                }
                // 入栈
                stack.append(ch);
                visits[ch - 'a'] = true;
            }
            // 剩余出现次数-1
            // 这一步所在的位置很关键，无论栈中有没有chs[i]，都需要对其次数进行递减
            counts[ch - 'a']--;
        }
        return stack.toString();
    }
}
