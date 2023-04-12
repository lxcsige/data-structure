package com.dp.algorithm.array;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * leetcode_128_最长连续序列_中等
 * @author liuxucheng
 * @since 2022/7/22
 */
public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int res = 0;
        for (int num : nums) {
            // 如果包含num-1，则以num开始的序列肯定不是最长序列
            if (numSet.contains(num - 1)) {
                continue;
            }
            int tmp = 1;
            int nextNum = num+1;
            while (numSet.contains(nextNum)) {
                tmp++;
                nextNum++;
            }
            res = Math.max(tmp, res);
        }
        return res;
    }
}
