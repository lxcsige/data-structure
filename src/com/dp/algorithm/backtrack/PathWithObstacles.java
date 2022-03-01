package com.dp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 08.02. 迷路的机器人
 *
 * @author liuxucheng
 * @since 2022/2/27
 */
public class PathWithObstacles {

    public static void main(String[] args) {

    }

    /**
     * DFS + 回溯
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> res = new ArrayList<>();
        int m = obstacleGrid.length;
        if (m == 0) {
            return res;
        }
        int n = obstacleGrid[0].length;

        if (obstacleGrid[m-1][n-1] == 1) {
            return res;
        }

        boolean[][] visited = new boolean[m][n];
        dfs(obstacleGrid, m, n, 0, 0, visited, res);

        return res;
    }

    private boolean dfs(int[][] obstacleGrid, int row, int column, int curRow, int curColumn,
                        boolean[][] visited, List<List<Integer>> path) {
        // base case，防止数组越界
        if (curRow >= row || curColumn >= column
                || obstacleGrid[curRow][curColumn] == 1 || visited[curRow][curColumn]) {
            return false;
        }

        // 加入路径
        path.add(Arrays.asList(curRow, curColumn));
        visited[curRow][curColumn] = true;

        // 到达终点
        if (curRow == row - 1 && curColumn == column - 1) {
            return true;
        }
        // 继续往右走或往下走
        if (dfs(obstacleGrid, row, column, curRow + 1, curColumn, visited, path)
                || dfs(obstacleGrid, row, column, curRow, curColumn + 1, visited, path)) {
            return true;
        }
        // 回溯
        path.remove(path.size() - 1);

        return false;
    }
}
