package com.dp.algorithm.design;

/**
 * 208_前缀树实现_中等
 * 设计思路：使用「边」来代表有无字符，使用「点」来记录是否为「单词结尾」以及「其后续字符串的字符是什么」。
 *
 *
 * @author xucheng.liu
 * @since 2022/3/17
 */
public class Trie {

    /**
     * 当前节点是否为结尾
     */
    private boolean isEnd;

    private Trie[] children;

    public Trie() {
        children = new Trie[26];
    }

    /**
     * 插入字符串
     *
     * @param word
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 字符c不存在，新增
            if (trie.children[c - 'a'] == null) {
                trie.children[c - 'a'] = new Trie();
            }
            // 子节点
            trie = trie.children[c - 'a'];
        }
        // 将最后一个节点设为结尾
        trie.isEnd = true;
    }

    /**
     * 查找字符串
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return null;
        }

        Trie trie = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (trie.children[c - 'a'] == null) {
                return null;
            }
            trie = trie.children[c - 'a'];
        }

        return trie;
    }
}
