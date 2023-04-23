package com.dp.algorithm.array;

/**
 * leetcode_556_下一个更大元素III_中等
 */
public class NextGreaterElementIII {

    public int nextGreaterElement(int n) {
        char[] digits = Integer.valueOf(n).toString().toCharArray();
        int len = digits.length;
        int i = len - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = len - 1;
        while (j > i && digits[j] <= digits[i]) {
            j--;
        }
        swap(digits, i, j);
        reverse(digits, i + 1);
        long res = Long.parseLong(new String(digits));
        return res > Integer.MAX_VALUE ? -1 : (int) res;
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private void reverse(char[] chars, int start) {
        int l = start, r = chars.length - 1;
        while (l < r) {
            swap(chars, l, r);
            l++;
            r--;
        }
    }
}
