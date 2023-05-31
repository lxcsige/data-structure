package com.dp.algorithm.dfs;

/**
 * leetcode_200_岛屿数量_中等
 */
public class NumIslands {

    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length) {
            return;
        }
        if (c < 0 || c >= grid[0].length) {
            return;
        }
        if (grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '2';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}
