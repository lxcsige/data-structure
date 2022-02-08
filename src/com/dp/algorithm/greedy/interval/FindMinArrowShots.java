package com.dp.algorithm.greedy.interval;

import java.util.Arrays;

/**
 * @author liuxucheng
 * @since 2021/6/22
 */
public class FindMinArrowShots {

    public static void main(String[] args) {
        FindMinArrowShots test = new FindMinArrowShots();
        test.findMinArrowShots(new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}});
    }

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // 注意这里的坐标可能存在负值，两数相减可能存在溢出
        Arrays.sort(points, (p1, p2) -> {
            if (p1[1] < p2[1]) {
                return -1;
            } else if (p1[1] == p2[1]) {
                return 0;
            } else {
                return 1;
            }
        });

        int minEnd = points[0][1];
        int res = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > minEnd) {
                res++;
                minEnd = points[i][1];
            }
        }

        return res;
    }
}
