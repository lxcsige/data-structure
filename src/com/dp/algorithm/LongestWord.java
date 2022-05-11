package com.dp.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 720_词典中最长的单词_简单
 *
 * @author xucheng.liu
 * @since 2022/3/17
 */
public class LongestWord {

    public static void main(String[] args) {
        System.out.println(longestWord2(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    }

    /**
     * 哈希表
     * @param words
     * @return
     */
    public static String longestWord(String[] words) {
        // 排序，字符串长度正序，字典序倒序
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return b.compareTo(a);
        });

        String res = "";
        Set<String> set = new HashSet<>();
        set.add("");
        for (String word : words) {
            if (set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                res = word;
            }
        }

        return res;
    }

    public static String longestWord2(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        String longest = "";
        for (String word : words) {
            if (trie.search(word)) {
                if (word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }
        return longest;
    }

    static class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                // 注意，这里额外加了个判断条件，用于判断前缀单词是否存在
                if (node.children[index] == null || !node.children[index].isEnd) {
                    return false;
                }
                node = node.children[index];
            }
            return node != null && node.isEnd;
        }
    }
}
