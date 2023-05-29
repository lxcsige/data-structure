package com.dp.algorithm.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode_187_重复的DNA序列_中等
 *
 * @author liuxucheng
 * @since 2023/5/25
 */
public class FindRepeatedDnaSequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> str2CntMap = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            str2CntMap.put(str, str2CntMap.getOrDefault(str, 0) + 1);
            if (str2CntMap.get(str) == 2) {
                res.add(str);
            }
        }
        return res;
    }
}
