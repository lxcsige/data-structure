package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode_51_N皇后_困难
 */
public class SolveNQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        backtrack(n, 0, queens, res);
        return res;
    }

    private void backtrack(int n, int row, int[] queens, List<List<String>> res) {
        if (row == n) {
            List<String> path = new ArrayList<>();
            res.add(path);
            for (int i = 0; i < n; i++) {
                char[] chs = new char[n];
                Arrays.fill(chs, '.');
                chs[queens[i]] = 'Q';
                path.add(new String(chs));
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(queens, row, i)) {
                queens[row] = i;
                backtrack(n, row + 1, queens, res);
                queens[row] = -1;
            }
        }
    }

    private boolean isValid(int[] queens, int row, int column) {
        // 检查列
        for (int i = 0; i < row; i++) {
            if (queens[i] == column) {
                return false;
            }
        }
        // 检查自右上到左上的对角线
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (queens[i] == j) {
                return false;
            }
        }

        // 检查自左下到左上的对角线
        for (int i = row - 1, j = column + 1; i >= 0 && j < queens.length; i--, j++) {
            if (queens[i] == j) {
                return false;
            }
        }

        return true;
    }
}
