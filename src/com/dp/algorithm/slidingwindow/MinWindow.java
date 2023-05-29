package com.dp.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_76_最小覆盖子串_困难
 *
 * @author xucheng.liu
 * @since 2022/4/29
 */
public class MinWindow {

    public static void main(String[] args) {
    }

    /**
     * 滑动窗口
     * 窗口收缩的时机：
     * 1. 左边界字符不在字符串t中
     * 2. 左边界字符在字符串t中，但其在窗口内的数量大于t中该字符的数量，可以移除
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        // 字符串s中的字符和出现次数的映射
        Map<Character, Integer> sMap = new HashMap<>();
        // 字符串t中的字符和出现次数的映射
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 结果长度
        int len = Integer.MAX_VALUE;
        // 结果左边界
        int resLeft = -1;
        int cnt = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (!tMap.containsKey(ch)) {
                continue;
            }
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
            if (sMap.get(ch) <= tMap.get(ch)) {
                cnt++;
            }
            // 窗口左边界右移
            while (i < j) {
                ch = s.charAt(i);
                if (!sMap.containsKey(ch)) {
                    i++;
                } else if (sMap.get(ch) > tMap.get(ch)) {
                    sMap.put(ch, sMap.get(ch) - 1);
                    i++;
                } else {
                    break;
                }
            }
            if (cnt == t.length() && len > j - i + 1) {
                len = j - i + 1;
                resLeft = i;
            }
        }

        return resLeft == -1 ? "" : s.substring(resLeft, resLeft + len);
    }

    /**
     * 窗口收缩时机：窗口覆盖字符串t
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        // 字符串s中的字符和出现次数的映射
        Map<Character, Integer> sMap = new HashMap<>();
        // 字符串t中的字符和出现次数的映射
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 结果长度
        int len = Integer.MAX_VALUE;
        // 结果左边界
        int resLeft = -1;
        int valid = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (!tMap.containsKey(ch)) {
                continue;
            }
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
            if (sMap.get(ch).equals(tMap.get(ch))) {
                valid++;
            }
            // 窗口收缩时机：窗口覆盖字符串t
            while (valid == tMap.size()) {
                // 更新len
                if (j - i + 1 < len) {
                    len = j - i + 1;
                    resLeft = i;
                }
                // 左边界右移
                char left = s.charAt(i++);
                if (sMap.containsKey(left)) {
                    int leftCnt = sMap.get(left);
                    sMap.put(left, leftCnt - 1);
                    if (leftCnt == tMap.get(left)) {
                        valid--;
                    }
                }
            }
        }

        return resLeft == -1 ? "" : s.substring(resLeft, resLeft + len);
    }
}
