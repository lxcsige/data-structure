package com.dp.algorithm.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode_227_基本计算器II_中等
 *
 * @author liuxucheng
 * @since 2023/1/29
 */
public class Calculator2 {

    /**
     * 前面的运算符是加/减号则入栈，是乘除号则弹出栈顶元素计算后将结果压栈
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int len = s.length();
        int num = 0;
        boolean isDigit;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            isDigit = Character.isDigit(ch);
            // 操作数
            if (isDigit) {
                num = num * 10 + ch - '0';
            }
            // 碰到运算符或遍历到字符串末尾，则认为遍历到数字末尾
            if (!isDigit && ch != ' ' || i == len - 1) {
                if ('+' == preSign) {
                    stack.push(num);
                } else if ('-' == preSign) {
                    stack.push(-num);
                } else if ('*' == preSign) {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
                // 重置num和preSign
                num = 0;
                preSign = ch;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
