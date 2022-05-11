package com.dp.algorithm.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode_30_串联所有单词的子串_困难
 *
 * @author xucheng.liu
 * @since 2022/5/2
 */
public class FindSubstring {

    public static void main(String[] args) {

    }

    /**
     *
     * @param s 字符串
     * @param words 单词数组
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> word2CountMap = new HashMap<>();
        for (String word : words) {
            word2CountMap.put(word, word2CountMap.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int wordCnt = words.length;
        int wordLen = words[0].length();
        // 遍历字符串，i表示左边界，wordCnt * wordLen个连续字符为一组
        for (int i = 0; i <= sLen - wordCnt * wordLen; i++) {
            Map<String, Integer> subWord2CountMap = new HashMap<>();
            int idx = 0;
            while (idx < wordCnt) {
                String word = s.substring(i + idx * wordLen, i + (idx + 1) * wordLen);
                if (!word2CountMap.containsKey(word)) {
                    break;
                }
                subWord2CountMap.put(word, subWord2CountMap.getOrDefault(word, 0) + 1);
                if (subWord2CountMap.get(word) > word2CountMap.get(word)) {
                    break;
                }

                idx++;
            }
            if (idx == wordCnt) {
                res.add(i);
            }
        }

        return res;
    }
}
