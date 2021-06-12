package com.dp.algorithm.dp.knapsack;

/**
 * 01背包
 *
 * @author liuxucheng
 * @since 2021/6/6
 */
public class Knapsack01 {

    public static void main(String[] args) {
        Knapsack01 test = new Knapsack01();
        System.out.print(test.maxValue(5, 20, new int[]{5,6,7,8,9}, new int[]{10, 7, 9, 8, 12}));
    }

    /**
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i-1]] + c[i-1])
     * 变量：物品+容量，选择：装或者不装
     *
     * @param n
     * @param v
     * @param w
     * @param c
     * @return
     */
    public int maxValue(int n, int v, int[] w, int[] c) {
        // dp[i][j]表示前i个物品放到最大容量为j的背包里的最大价值
        int[][] dp = new int[n+1][v+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= v; j++) {
                if (j < w[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i-1]] + c[i-1]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= v; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        int[] item = new int[n];
        for (int i = n; i > 0;) {
            for (int j = v; j > 0;) {
                if (dp[i][j] == dp[i-1][j]) {
                    item[--i] = 0;
                } else if (j >= w[i-1] && dp[i][j] == dp[i-1][j-w[i-1]] + c[i-1]) {
                    j -= w[i -1];
                    item[--i] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(item[i]);
        }

        return dp[n][v];
    }

    /**
     * 从后向前遍历，状态压缩
     *
     * @param n
     * @param v
     * @param w
     * @param c
     * @return
     */
    public int maxValue2(int n, int v, int[] w, int[] c) {
        int[] dp = new int[v+1];
        for(int i = 1; i <= n; i++) {
            for (int j = v; j >= w[i-1]; j--) {
                dp[j] = Math.max(dp[j], dp[j-w[i-1]] + c[i-1]);
            }
        }

        return dp[v];
    }
}
