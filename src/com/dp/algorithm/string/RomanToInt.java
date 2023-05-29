package com.dp.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_13_罗马数字转整数_简单
 *
 * @author liuxucheng
 * @since 2023/5/29
 */
public class RomanToInt {

    Map<Character, Integer> char2IntMap = new HashMap<>();

    {
        char2IntMap.put('I', 1);
        char2IntMap.put('V', 5);
        char2IntMap.put('X', 10);
        char2IntMap.put('L', 50);
        char2IntMap.put('C', 100);
        char2IntMap.put('D', 500);
        char2IntMap.put('M', 1000);
    }

    public int romanToInt(String s) {
        int res = 0, cur, n = s.length();
        for (int i = 0; i < n; i++) {
            cur = char2IntMap.get(s.charAt(i));
            if (i < n - 1 && cur < char2IntMap.get(s.charAt(i + 1))) {
                res -= cur;
            } else {
                res += cur;
            }
        }
        return res;
    }
}
