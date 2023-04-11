package com.dp.algorithm.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * leetcode_重构字符串_767_中等
 *
 * @author liuxucheng
 * @since 2022/8/17
 */
public class ReorganizeString {

    public static void main(String[] args) {
        ReorganizeString solution = new ReorganizeString();
        solution.reorganizeString("eqpspvbppp");
    }


    public String reorganizeString(String s) {
        if (s.length() < 2) {
            return s;
        }
        // 分别对应26个小写字母
        int[] counts = new int[26];
        // 假设元素总数为n：
        // n是偶数，如果某个元素的出现次数超过n/2，则必然存在相邻
        // n是奇数，如果某个元素的出现次数超过(n+1)/2，则必然存在相邻
        // 可以统一为(n+1)/2
        for (char ch : s.toCharArray()) {
            counts[ch - 'a']++;
        }
        // 出现次数最多的字符的index
        int maxIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        if (counts[maxIndex] > (s.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[s.length()];
        int index = 0;
        char ch = (char)(maxIndex + 'a');
        // 将出现次数最多的元素放到偶数位上
        while (counts[maxIndex]-- > 0) {
            res[index] = ch;
            index += 2;
        }
        // 处理其他元素，优先放到偶数位，偶数位到头了则放到奇数位
        for (int i = 0; i < counts.length; i++) {
            if (i == maxIndex || counts[i] == 0) {
                continue;
            }
            ch = (char)(i + 'a');
            while (counts[i]-- > 0) {
                // 从奇数位开始放
                if (index >= s.length()) {
                    index = 1;
                }
                res[index] = ch;
                index += 2;
            }
        }
        return new String(res);
    }
}
