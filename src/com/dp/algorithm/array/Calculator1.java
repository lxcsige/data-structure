package com.dp.algorithm.array;

import java.util.*;

/**
 * leetcode_224_基本计算器_困难
 *
 * 碰到括号匹配问题就要想到递归或栈
 *
 * @author liuxucheng
 * @since 2023/1/29
 */
public class Calculator1 {

    private int start;

    public static void main(String[] args) {
        Calculator1 calculator1 = new Calculator1();
        System.out.println(calculator1.calculate6("(1+(4+5*2/3-10)-3)+(6+8)"));
    }

    /**
     * 递归，通用方案
     * 3 * (2 + 4 * 3) + 2
     * = 3 * calculate(2 + 4 * 3) + 2
     * = 3 * 24 + 2
     * = 74
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int len = s.length();
        int num = 0;
        char preSign = '+';
        char ch;
        boolean isDigit;
        for (; start < len; start++) {
            ch = s.charAt(start);
            isDigit = Character.isDigit(ch);
            if (isDigit) {
                num = num * 10 + (ch - '0');
            }
            if ('(' == ch) {
                // 递归，从(下一个字符开始遍历，将递归返回的值作为操作数
                start++;
                num = calculate(s);
            }
            // 碰到运算符('('、')'、'+'、'-'、'*'、'/')或者遍历到最后一个字符，可以认为遍历到当前操作数的末尾了
            if (!isDigit && ch != ' ' || start == len - 1) {
                if ('+' == preSign) {
                    stack.push(num);
                } else if ('-' == preSign) {
                    stack.push(-num);
                } else if ('*' == preSign) {
                    stack.push(stack.pop() * num);
                } else if ('/' == preSign) {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                preSign = ch;
            }
            // 遇到右括号，退出循环，然后计算结果，返回上一层递归
            if (')' == preSign) {
                break;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    /**
     * 括号展开+栈
     *
     * 1-(2-(3+4)+5)
     * 第一个括号前的符号是-1，入栈
     * 第二个括号前的符号是-(-1)=+1，入栈
     * 1之前的符号是+1，2之前的符号是-1，3之前的符号是+1，4之前的符号是+1，5之前的符号是-1
     *
     * 如果展开表达式中所有的括号，则得到的新表达式中，数字本身不会发生变化，只是每个数字前面的符号可能会发生变化
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
        int len = s.length();
        int res = 0;
        // 当前位置的符号，即正负号，初始为正号
        int sign = 1;
        // 记录括号之前的符号
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int i = 0;
        while (i < len) {
            if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else if (s.charAt(i) == ' ') {
                i++;
            } else {
                long num = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                res += sign * num;
            }
        }
        return res;
    }

    /**
     * 递归，仅适用于加减法计算器
     *
     * @param s
     * @return
     */
    public int calculate3(String s) {
        int res = 0;
        int len = s.length();
        // 符号，1表示正号，-1表示负号
        int preSign = 1;
        for (; start < len; start++) {
            // 数字
            if (Character.isDigit(s.charAt(start))) {
                int num = s.charAt(start) - '0';
                while (start < len - 1 && Character.isDigit(s.charAt(start + 1))) {
                    num = num * 10 + (s.charAt(start + 1) - '0');
                    start++;
                }
                res += preSign * num;
            } else if ('+' == s.charAt(start)) {
                preSign = 1;
            } else if ('-' == s.charAt(start)) {
                preSign = -1;
            } else if ('(' == s.charAt(start)) {
                // 进括号，递归，从'('下一位开始
                start++;
                res += preSign * calculate3(s);
            } else if (')' == s.charAt(start)) {
                break;
            }
        }
        return res;
    }

    /**
     * 解法3的非递归版本
     *
     * @param s
     * @return
     */
    public int calculate4(String s) {
        int res = 0;
        // 符号
        int preSign = 1;
        // 碰到右括号时，将计算结果入栈
        Deque<Integer> stack = new LinkedList<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            // 碰到数字
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i < len - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                res += preSign * num;
            } else if (ch == '+') {
                preSign = 1;
            } else if (ch == '-') {
                preSign = -1;
            } else if (ch == '(') {
                // 将左侧结果压栈
                stack.push(res);
                // 将运算符压栈
                stack.push(preSign);
                // 重置res，用于计算括号中的表达式
                res = 0;
                // 重置preSign
                preSign = 1;
            } else if (ch == ')') {
                res *= stack.pop();
                res += stack.pop();
            }
        }
        return res;
    }

    /**
     * 转逆波兰表达式后求值，注意需要额外处理负数
     *
     * @param s
     * @return
     */
    public int calculate5(String s) {
        List<String> tokens = parse2RPN(s);
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
                }
            }
        }
        return stack.pop();
    }

    private List<String> parse2RPN(String s) {
        List<String> res = new ArrayList<>();
        Deque<Character> stack = new LinkedList<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (' ' == ch) {
                continue;
            }
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i < len - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                res.add(num + "");
            } else if ('(' == ch) {
                stack.push(ch);
            } else if (')' == ch) {
                while (!stack.isEmpty() && '(' != stack.peek()) {
                    res.add(stack.pop() + "");
                }
                // '('出栈
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                if (!stack.isEmpty() && '(' != stack.peek()) {
                    res.add(stack.pop() + "");
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop() + "");
        }
        return res;
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token));
    }

    Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
    }};

    /**
     * 双栈
     *
     * @param s
     * @return
     */
    public int calculate6(String s) {
        Deque<Integer> nums = new LinkedList<>();
        nums.push(0);
        Deque<Character> ops = new LinkedList<>();
        // 清除所有空格
        s = s.replaceAll(" ", "");
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i < len - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                while (!ops.isEmpty()) {
                    if (ops.peek() != '(') {
                        calculate(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else {
                if (i > 0 && (s.charAt(i - 1) == '(' || s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-')) {
                    nums.push(0);
                }
                // 有一个新操作要入栈时，先把栈内可以算的都算了
                // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                while (!ops.isEmpty() && ops.peek() != '(') {
                    char prev = ops.peek();
                    if (map.get(prev) >= map.get(ch)) {
                        calculate(nums, ops);
                    } else {
                        break;
                    }
                }
                ops.push(ch);
            }
        }
        while (!ops.isEmpty()) {
            calculate(nums, ops);
        }
        return nums.pop();
    }

    private void calculate(Deque<Integer> nums, Deque<Character> ops) {
        int num2 = nums.pop();
        int num1 = nums.pop();
        char op = ops.pop();
        if ('+' == op) {
            nums.push(num1 + num2);
        } else if ('-' == op) {
            nums.push(num1 - num2);
        }
    }
}
