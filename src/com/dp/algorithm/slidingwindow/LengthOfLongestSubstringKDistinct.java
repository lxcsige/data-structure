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
}
