package com.dp.algorithm;

import java.util.*;

/**
 * leetcode_20_有效的括号_简单
 *
 * @author liuxucheng
 * @since 2022/12/9
 */
public class IsValid {

    public static void main(String[] args) {
        IsValid test = new IsValid();
        test.isValid("()");
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }
        return true;
    }
}
