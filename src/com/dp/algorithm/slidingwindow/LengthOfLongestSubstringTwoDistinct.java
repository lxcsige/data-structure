package com.dp.algorithm.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_159_至多包含两个不同字符的最长子串_中等
 *
 * @author xucheng.liu
 * @since 2022/4/30
 */
public class LengthOfLongestSubstringTwoDistinct {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct2("abac"));
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0, res = 0, delIdx;
        char c;
        Map<Character, Integer> map = new HashMap<>();
        for (; right < s.length(); right++) {
            c = s.charAt(right);
            // 当前窗口不包含c且窗口中的字符数量已经达到阈值，此时需要滑动窗口
            if (!map.containsKey(c) && map.size() == 2) {
                delIdx = Collections.min(map.values());
                map.remove(s.charAt(delIdx));
                left = delIdx + 1;
            }
            map.put(c, right);

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public static int lengthOfLongestSubstringTwoDistinct2(String s) {
        final int sLen = s.length();
        final int[] count = new int[256];
        int right = 0, left = 0;
        // left和right在同一个循环中，因此窗口大小单调递增，因此不需要每次都进行Math.max()比较
        for (int types = 0; right < sLen; right++) {
            if (count[s.charAt(right)]++ == 0) {
                ++types;
            }
            if (types > 2) {
                if (--count[s.charAt(left++)] == 0) {
                    --types;
                }
            }
        }
        return right - left;
    }
}
