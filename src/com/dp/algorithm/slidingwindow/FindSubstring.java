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
        FindSubstring test = new FindSubstring();
        test.findSubstring2("barfoothefoobarman", new String[]{"foo","bar"});
    }

    /**
     * 暴力解法
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
        // 单词数量
        int wordCnt = words.length;
        // 单词长度
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

    /**
     * 滑动窗口优化
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        int sLen = s.length();
        int wordCnt = words.length;
        int wordLen = words[0].length();
        int subLen = wordLen * wordCnt;
        Map<String, Integer> word2CntMap = new HashMap<>();
        for (String word : words) {
            word2CntMap.put(word, word2CntMap.getOrDefault(word, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        // 枚举不同的起点
        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> windowMap = new HashMap<>();
            for (int j = i, l = i; j <= sLen - wordLen; j += wordLen) {
                // 该字符可能在单词表中，也可能不在
                String word = s.substring(j, j + wordLen);
                windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                // 当前单词次数超了，需要滑动左边界
                while (windowMap.get(word) > word2CntMap.getOrDefault(word, 0)) {
                    String leftWord = s.substring(l, l + wordLen);
                    l += wordLen;
                    windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                }
                // 符合条件
                if (j - l + wordLen == subLen) {
                    res.add(l);
                }
            }
        }
        return res;
    }
}
