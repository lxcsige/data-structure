package com.dp.algorithm.dfs;

/**
 * leetcode_463_岛屿的周长_简单
 */
public class IslandPerimeter {

    /**
     * dfs
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int r, int c) {
        // 越界，必有一条边
        if (!inArea(grid, r, c)) {
            return 1;
        }
        // 碰到海洋节点，必有一条边
        if (grid[r][c] == 0) {
            return 1;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r - 1, c) + dfs(grid, r + 1, c) + dfs(grid, r, c - 1) + dfs(grid, r, c + 1);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    /**
     * 迭代
     *
     * @param grid
     * @return
     */
    public int islandPerimeter2(int[][] grid) {
        int res = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int r = i + directions[k][0];
                        int c = j + directions[k][1];
                        if (!inArea(grid, r, c) || grid[r][c] == 0) {
                            cnt++;
                        }
                    }
                    res += cnt;
                }
            }
        }
        return res;
    }
}
