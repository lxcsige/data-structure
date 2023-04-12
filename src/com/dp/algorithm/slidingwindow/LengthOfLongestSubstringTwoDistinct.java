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
        LengthOfLongestSubstringTwoDistinct solution = new LengthOfLongestSubstringTwoDistinct();
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct2("ccaabbb"));
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
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

    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        int left = 0, right = 0, res = 0;
        for (; right < s.length(); right++) {
            char ch = s.charAt(right);
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            while (countMap.size() > 2) {
                int count = countMap.get(s.charAt(left++));
                if (count == 1) {
                    countMap.remove(ch);
                } else {
                    countMap.put(ch, count - 1);
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
