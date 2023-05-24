package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_1047_删除字符串中的所有相邻重复项_简单
 *
 * @author liuxucheng
 * @since 2023/4/18
 */
public class RemoveDuplicates {

    public String removeDuplicates(String s) {
        if (s.length() < 2) {
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        if (stack.isEmpty()) {
            return "";
        }
        char[] res = new char[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return new String(res);
    }

    public String removeDuplicates2(String s) {
        StringBuilder stack = new StringBuilder();
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            // 相等，出栈
            if (stack.length() > 0 && stack.charAt(top) == s.charAt(i)) {
                stack.deleteCharAt(top);
                top--;
            } else {
                // 不等，进栈
                stack.append(s.charAt(i));
                top++;
            }
        }
        return stack.toString();
    }
}
