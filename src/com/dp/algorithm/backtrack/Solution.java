package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字1-9组成3个3位数，1个数字只能使用1次，第2个数是第1个数的2倍，第3个数是第2个数的2倍
 */
public class Solution {

    public static void main(String[] args) {
        Solution test = new Solution();
        test.combine();
    }

    public List<List<Integer>> combine() {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[10];
        backtrack(0, 0, used, res);
        return res;
    }

    private void backtrack(int index, int first, boolean[] used, List<List<Integer>> res) {
        if (index == 3) {
            // 检查是否满足条件，是则将结果加入到结果集中
            check(first, res);
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (index == 0 && i > 3) {
                break;
            }
            if (used[i]) {
                continue;
            }
            used[i] = true;
            backtrack(index + 1, first * 10 + i, used, res);
            used[i] = false;
        }
    }

    private void check(int first, List<List<Integer>> res) {
        boolean[] used = new boolean[10];
        int second = first * 2;
        int third = first * 3;
        if (check(first, used) && check(second, used) && check(third, used)) {
            List<Integer> tmp = new ArrayList<>();
            res.add(tmp);
            tmp.add(first);
            tmp.add(second);
            tmp.add(third);
        }
    }

    private boolean check(int num, boolean[] used) {
        for (int i = 0; i < 3; i++) {
            int digit = num % 10;
            // 数字已经被使用过
            if (used[digit]) {
                return false;
            }
            used[digit] = true;
            num = num / 10;
        }
        // 超过3位
        if (num > 0) {
            return false;
        }
        return true;
    }
}
