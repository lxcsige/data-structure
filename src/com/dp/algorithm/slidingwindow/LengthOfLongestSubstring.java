package com.dp.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leetcode_3_最长无重复子串_中等
 *
 * @author liuxucheng
 * @since 2021/3/18
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        lengthOfLongestSubString("pwwkew");
    }

    /**
     * 暴力解法，时间复杂度O(n^2)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubString(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }

        int res = 0;
        // i：左边界，j：右边界，k：遍历指针
        // 从左边界开始依次同右边界元素进行比较，如果相同，将左边界右移至遍历指针的后一位
        for (int i = 0, j = 1; j < s.length(); j++) {
            for (int k = i; k < j; k++) {
                if (s.charAt(k) == s.charAt(j)) {
                    i = k + 1;
                }
            }
            res = Math.max(res, j - i + 1);
        }

        return res;
    }

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
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

    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, res = 0;
        for (; right < s.length(); right++) {
            // 当前字符不一定在窗口内
            if (map.containsKey(s.charAt(right))) {
                // 保证left不会往左移动
                // 1. 当前字符在窗口内，以abca为例，遍历到最后一个a时，left原本是0，需要更新为1
                // 2. 当前字符不在窗口内，以abba为例，遍历到最后一个a时，map.get('a') == 0，此时不能将left更新为1
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            // 更新index
            map.put(s.charAt(right), right);
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
