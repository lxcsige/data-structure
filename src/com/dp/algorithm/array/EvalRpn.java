package com.dp.algorithm.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode_150_逆波兰表达式求值_中等
 *
 * @author liuxucheng
 * @since 2023/1/28
 */
public class EvalRpn {

    public int evalRpn(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                if ("+".equals(token)) {
                    stack.push(num1 + num2);
                } else if ("-".equals(token)) {
                    stack.push(num1 - num2);
                } else if ("*".equals(token)) {
                    stack.push(num1 * num2);
                } else {
                    stack.push(num1 / num2);
                }
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}
