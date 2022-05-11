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
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    /**
     * 滑动窗口
     * 右边界一直向右移动，只有等到当前窗口包含所有字符串t中的所有字符时才会尝试右移左边界
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        // 字符串t中的字符和出现次数的映射
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        // 遍历左指针
        int left = 0;
        // 结果长度
        int len = Integer.MAX_VALUE;
        // 结果左边界
        int resLeft = -1;
        // 结果右边界
        int resRight = -1;
        char sc, slc;
        // 字符串s中的字符和出现次数的映射
        Map<Character, Integer> sMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            sc = s.charAt(right);
            if (tMap.containsKey(sc)) {
                sMap.put(sc, sMap.getOrDefault(sc, 0) + 1);

                // 检查此时left和right之间的子串是否完全包含字符串t中的所有字符，是则尝试左移left指针
                while(containsAll(sMap, tMap) && left <= right) {
                    if (right - left + 1 < len) {
                        // 更新结果
                        len = right - left + 1;
                        resLeft = left;
                        resRight = right;
                    }

                    // left右移，同时更新sMap
                    slc = s.charAt(left);
                    if (tMap.containsKey(slc)) {
                        sMap.put(slc, sMap.get(slc) - 1);
                    }
                    left++;
                }
            }
        }

        return resLeft == -1 ? "" : s.substring(resLeft, resRight + 1);
    }

    private static boolean containsAll(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        Character c;
        int count;
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            c = entry.getKey();
            count = entry.getValue();
            if (sMap.getOrDefault(c, 0) < count) {
                return false;
            }
        }
        return true;
    }
}
