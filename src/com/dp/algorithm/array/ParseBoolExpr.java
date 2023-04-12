package com.dp.algorithm.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author liuxucheng
 * @since 2023/1/30
 */
public class ParseBoolExpr {

    /**
     * 栈
     *
     * @param expression
     * @return
     */
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == ',') {
                continue;
            }
            if (ch != ')') {
                stack.push(ch);
            } else {
                int t = 0, f = 0;
                // 右括号，出栈直到碰到左括号
                while (stack.peek() != '(') {
                    char val = stack.pop();
                    if (val == 't') {
                        t++;
                    } else if (val == 'f') {
                        f++;
                    }
                }
                // 左括号出栈
                stack.pop();
                // 运算符出栈
                char op = stack.pop();
                if (op == '!') {
                    stack.push(f == 1 ? 't' : 'f');
                } else if (op == '|') {
                    stack.push(t > 0 ? 't' : 'f');
                } else if (op == '&') {
                    stack.push(f > 0 ? 'f' : 't');
                }
            }
        }
        return stack.pop() == 't';
    }
}
