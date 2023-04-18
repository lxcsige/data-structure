package com.dp.algorithm.string;

/**
 * leetcode_541_反转字符串II_简单
 */
public class ReverseStr {

    public String reverseStr(String s, int k) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(chars, i, Math.min(n - 1, i + k - 1));
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
}
