package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_37_解数独_困难
 */
public class SolveSudoku {

    /**
     * 9 * 9
     * 1. 1-9在每一行只能出现1次
     * 2. 1-9在每一列只能出现1次
     * 3. 1-9在每一个3 * 3方格内只能出现1次
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        // rows[i][j]表示第i行存在数字j-1
        boolean[][] rows = new boolean[9][9];
        // columns[i][j]表示第i列存在数字j-1
        boolean[][] columns = new boolean[9][9];
        // blocks[i][j][k]表示第i行j列的方格内是否存在数字k-1
        boolean[][][] blocks = new boolean[3][3][9];
        List<int[]> spaces = new ArrayList<>();
        // 初始化
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 空格
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    // 数字
                    int digit = board[i][j] - '0';
                    rows[i][digit - 1] = true;
                    columns[j][digit - 1] = true;
                    blocks[i / 3][j / 3][digit - 1] = true;
                }
            }
        }
        dfs(board, spaces, 0, rows, columns, blocks);
    }

    private boolean dfs(char[][] board, List<int[]> spaces, int index,
                        boolean[][] rows, boolean[][] columns, boolean[][][] blocks) {
        if (index == spaces.size()) {
            return true;
        }
        int row = spaces.get(index)[0];
        int column = spaces.get(index)[1];
        for (int i = 0; i < 9; i++) {
            if (rows[row][i]) {
                continue;
            }
            if (columns[column][i]) {
                continue;
            }
            if (blocks[row / 3][column / 3][i]) {
                continue;
            }
            board[row][column] = (char) (i + '1');
            rows[row][i] = true;
            columns[column][i] = true;
            blocks[row / 3][column / 3][i] = true;
            if (dfs(board, spaces, index + 1, rows, columns, blocks)) {
                return true;
            }
            board[row][column] = '.';
            rows[row][i] = false;
            columns[column][i] = false;
            blocks[row / 3][column / 3][i] = false;
        }
        return false;
    }
}
