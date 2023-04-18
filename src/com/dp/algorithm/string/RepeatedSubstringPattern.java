package com.dp.algorithm.string;

/**
 * leetcode_459_重复的子字符串
 */
public class RepeatedSubstringPattern {

    /**
     * 方法一：拼接两个s，检查是否可以从中找到另外一个s
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }
}
