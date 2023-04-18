package com.dp.algorithm.string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_394_字符串解码_中等
 */
public class DecodeString {

    /**
     * 可能出现嵌套：3[a2[c]]
     * 涉及到括号，必须需要先进后出的特性，因此会用到栈结构
     *
     * 思路：双栈
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        // 当前字符串
        StringBuilder res = new StringBuilder();
        // 当前倍数
        int multi = 0;
        Deque<Integer> multiStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // 1. 数字
            if (Character.isDigit(c)) {
                multi = multi * 10 + c - '0';
            }
            // 2. '['
            else if (c == '[') {
                // 当前倍数压栈后重置
                multiStack.push(multi);
                multi = 0;
                // 当前字符串压栈后重置
                strStack.push(res);
                res = new StringBuilder();
            }
            // 3. ']'
            else if (c == ']') {
                int lastMulti = multiStack.pop();
                StringBuilder lastStr = strStack.pop();
                for (int i = 0; i < lastMulti; i++) {
                    lastStr.append(res);
                }
                res = lastStr;
            }
            // 4. 字母
            else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
