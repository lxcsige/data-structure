package com.dp.algorithm.dp.pathPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 08.02.迷路的机器人
 *
 * @author liuxucheng
 * @since 2022/2/27
 */
public class PathWithObstacles {

    public static void main(String[] args) {

    }

    /**
     * DP判断是否可行，倒序遍历找出路径
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 是否可以到达obstacleGrid[m][n]
        boolean[][] dp = new boolean[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            // 可达
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = true;
            } else {
                // 不可达
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            // 可达
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = true;
            } else {
                // 不可达
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        }

        // 终点不可达
        if (!dp[m-1][n-1]) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        int i = m-1;
        int j = n-1;
        while (i > 0 || j > 0) {
            res.add(0, Arrays.asList(i, j));
            // 优先向上
            if (i > 0 && dp[i-1][j]) {
                i--;
            } else if (j > 0 && dp[i][j-1]) {
                j--;
            }
        }
        res.add(0, Arrays.asList(0, 0));

        return res;
    }
}
