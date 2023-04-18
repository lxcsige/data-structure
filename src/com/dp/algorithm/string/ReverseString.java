package com.dp.algorithm.string;

/**
 * leetcode_344_反转字符串_简单
 */
public class ReverseString {

    /**
     * 双指针，O(1)
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
