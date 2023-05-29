package com.dp.algorithm.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode_438_找到字符串中所有字母异位词_中等
 *
 * @author liuxucheng
 * @since 2023/5/25
 */
public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return res;
        }
        Map<Character, Integer> pMap = new HashMap<>();
        for (int i = 0; i < pLen; i++) {
            pMap.put(p.charAt(i), pMap.getOrDefault(p.charAt(i), 0) + 1);
        }
        Map<Character, Integer> sMap = new HashMap<>();
        for (int i = 0, j = 0; j < sLen; j++) {
            char ch = s.charAt(j);
            // 其实也可以加进去，后续会比较该字符在两边出现的频率
            if (!pMap.containsKey(ch)) {
                i = j + 1;
                sMap.clear();
            } else {
                sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
                // 窗口左边界向右滑动
                while (sMap.get(ch) > pMap.get(ch)) {
                    char left = s.charAt(i++);
                    if (sMap.get(left) == 1) {
                        sMap.remove(left);
                    } else {
                        sMap.put(left, sMap.get(left) - 1);
                    }
                }
                // 当前窗口字符串刚好是p的异位词
                if (j - i + 1 == pLen) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    /**
     * 因为都是小写字母，因此可以用数组代替哈希表，效率更高
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return res;
        }
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        for (int i = 0; i < pLen; i++) {
            pArr[p.charAt(i) - 'a']++;
        }
        for (int i = 0, j = 0; j < sLen; j++) {
            char ch = s.charAt(j);
            sArr[ch - 'a']++;
            // 滑动左边界，直到字符ch在两边出现的频率相同
            while (sArr[ch - 'a'] > pArr[ch - 'a']) {
                char left = s.charAt(i++);
                sArr[left - 'a']--;
            }
            // 窗口大小等于p的长度
            if (j - i + 1 == pLen) {
                res.add(i);
            }
        }
        return res;
    }
}
