package com.dp.algorithm.string;

public class ReverseWords {

    /**
     * 不用栈
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        // 1. 移除空格
        s = trimSpaces(s);

        int n = s.length();
        char[] chars = s.toCharArray();

        // 2. 翻转整个字符串
        reverse(chars, 0, n - 1);

        // 3. 逐个翻转每个单词
        int start = 0, end = 0;
        while (start < n) {
            // 找到下一个空格
            while (end < n && chars[end] != ' ') {
                end++;
            }
            // 翻转单词
            reverse(chars, start, end - 1);
            end++;
            start = end;
        }
        return new String(chars);
    }

    private String trimSpaces(String s) {
        // 1. 移除前后空格
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        while (left <= right && s.charAt(right)== ' ') {
            right--;
        }
        // 2. 移除中间多余空格
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            // 非空格
            if (c != ' ') {
                sb.append(c);
            }
            // 空格，并且前一个不是空格
            else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            ++left;
        }
        return sb.toString();
    }

    private void reverse(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }
}
