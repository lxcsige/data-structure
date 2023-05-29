package com.dp.algorithm.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_340_至多包含 K 个不同字符的最长子串_中等
 *
 * @author xucheng.liu
 * @since 2022/5/1
 */
public class LengthOfLongestSubstringKDistinct {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, res = 0;
        int deleteIdx;
        char c;
        for (; right < s.length(); right++) {
            c = s.charAt(right);
            // 滑动左边界
            if (!map.containsKey(c) && map.size() == k) {
                deleteIdx = Collections.min(map.values());
                map.remove(s.charAt(deleteIdx));
                left = deleteIdx + 1;
            }
            map.put(c, right);

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if (k == 0) {
            return 0;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            // 左边界右移，直到窗口内只有k个不同元素
            while (countMap.size() > k) {
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
