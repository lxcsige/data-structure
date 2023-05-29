package com.dp.algorithm.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_632_最小区间_困难
 *
 * @author liuxucheng
 * @since 2023/5/25
 */
public class SmallestRange {

    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        List<int[]> sNums = mergeKLists(nums, 0, k - 1);
        int[] res = new int[]{-100000, 100000};
        int[] window = new int[k];
        int valid = 0;
        for (int i = 0, j = 0; j < sNums.size(); j++) {
            window[sNums.get(j)[1]]++;
            if (window[sNums.get(j)[1]] == 1) {
                valid++;
            }
            while (valid == k) {
                // 更新结果
                int[] left = sNums.get(i++);
                int[] right = sNums.get(j);
                if (right[0] - left[0] < res[1] - res[0] || (right[0] - left[0] == res[1] - res[0] && left[0] < res[0])) {
                    res[0] = left[0];
                    res[1] = right[0];
                }
                // 窗口收缩
                if (window[left[1]] == 1) {
                    valid--;
                }
                window[left[1]]--;
            }
        }
        return res;
    }

    private List<int[]> mergeKLists(List<List<Integer>> nums, int l, int r) {
        List<int[]> res = new ArrayList<>();
        if (l >= r) {
            List<Integer> slice = nums.get(r);
            for (int num : slice) {
                res.add(new int[]{num, r});
            }
            return res;
        }
        int mid = l + (r - l) / 2;
        List<int[]> left = mergeKLists(nums, l, mid);
        List<int[]> right = mergeKLists(nums, mid + 1, r);
        // 归并两个有序列表
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i)[0] < right.get(j)[0]) {
                res.add(left.get(i++));
            } else {
                res.add(right.get(j++));
            }
        }
        while (i < left.size()) {
            res.add(left.get(i++));
        }
        while (j < right.size()) {
            res.add(right.get(j++));
        }
        return res;
    }
}
