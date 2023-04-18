package com.dp.algorithm.string;

/**
 * leetcode_8_字符串转换整数_中等
 */
public class MyAtoi {

    public static void main(String[] args) {
        MyAtoi test = new MyAtoi();

        System.out.println(test.myAtoi(Integer.MIN_VALUE + ""));
    }

    /**
     * 思路：
     * 1. 考虑各种情况
     * 2. 溢出
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        int len = str.length();
        char[] chars = str.toCharArray();
        int index = 0;

        // 1. 去除前缀空格
        while (index < len && chars[index] == ' ') {
            index++;
        }
        // 都是空格，直接返回
        if (index == len) {
            return 0;
        }

        // 2. 记录正负号
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 3. 遍历后续字符
        int res = 0;
        for (; index < len; index++) {
            char ch = chars[index];
            // 非数字字符
            if (ch < '0' || ch > '9') {
                break;
            }
            int digit = ch - '0';
            // 判断是否溢出
            // 对于Integer.MIN_VALUE，会在这里直接返回
            if (res > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + digit;
        }

        return res * sign;
    }
}
