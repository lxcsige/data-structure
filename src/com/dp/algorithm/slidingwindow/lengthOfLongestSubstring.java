package com.dp.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最长无重复子串
 *
 * @author liuxucheng
 * @since 2021/3/18
 */
public class lengthOfLongestSubstring {

    public static void main(String[] args) {

    }

    private static int lengthOfLongestSubstring1(String s) {
        Set<Character> set = new HashSet<>();
        // 滑动窗口右边界
        int right = -1, res = 0;
        // 滑动窗口左边界
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                // 缺点：删除的左边界第一个元素不一定是重复元素，可能需要多次循环，right到最后一位时，继续循环也毫无意义
                set.remove(s.charAt(i - 1));
            }
            for (; right < s.length() - 1; right++) {
                if (!set.add(s.charAt(right + 1))) {
                    break;
                }
            }
            res = Math.max(right - i + 1, res);
        }

        return res;
    }

    private static int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, res = 0;
        for (; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                // 保证left不会往左移动
                left = Math.max(left, map.get(s.charAt(left)) + 1);
            }
            // 更新index
            map.put(s.charAt(right), right);
            res = Math.max(res, right -  left + 1);
        }

        return res;
    }
}
