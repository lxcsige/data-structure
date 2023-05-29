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
        int res = 0, delIdx;
        // 滑动窗口，维护字符和其最后一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            // 当前窗口不包含c且窗口中的字符数量已经达到阈值，此时需要滑动窗口
            if (!map.containsKey(c) && map.size() == 2) {
                // 删除左边的元素
                delIdx = Collections.min(map.values());
                map.remove(s.charAt(delIdx));
                i = delIdx + 1;
            }
            map.put(c, j);

            res = Math.max(res, j - i + 1);
        }

        return res;
    }

    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            // 左边界右移，直到窗口内只有2个不同元素
            while (countMap.size() > 2) {
                ch = s.charAt(i++);
                int count = countMap.get(ch);
                if (count == 1) {
                    countMap.remove(ch);
                } else {
                    countMap.put(ch, count - 1);
                }
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
