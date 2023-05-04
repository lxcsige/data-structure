package com.dp.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_402_移掉K位数字_中等
 */
public class RemoveKDigits {

    /**
     * 贪心+单调栈，保证靠前的数字尽可能小
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKDigits(String num, int k) {
        int n = num.length();
        char[] chs = num.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (k > 0 && !stack.isEmpty() && chs[i] < chs[stack.peek()]) {
                chs[stack.pop()] = ' ';
                k--;
            }
            if (k == 0) {
                break;
            }
            stack.push(i);
        }
        while (k > 0) {
            chs[stack.pop()] = ' ';
            k--;
        }
        // 移除前缀0和空格符
        int start = 0;
        while (start < n && (chs[start] == ' ' || chs[start] == '0')) {
            start++;
        }
        if (start == n) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (; start < chs.length; start++) {
            if (chs[start] != ' ') {
                res.append(chs[start]);
            }
        }
        return res.toString();
    }
}
