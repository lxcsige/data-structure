package com.dp.algorithm.dp.knapsack.knapsack01;

/**
 * 474_一和零
 *
 * @author xucheng.liu
 * @since 2022/2/9
 */
public class FindMaxForm {

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        System.out.println(findMaxForm(strs, 5, 3));
    }

    /**
     * dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j-nums[i-1]中0的个数][k-nums[i-1]中1的个数]+1)
     * 时间复杂度：O(lmn + L)，L表示所有字符串的长度
     * 空间复杂度：O(lmn)
     *
     * @param strs 字符串数组
     * @param m    0的数量
     * @param n    1的数量
     * @return 最大子集的长度
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l + 1][m + 1][n + 1];
        for (int i = 1; i <= l; i++) {
            String str = strs[i-1];
            // 该字符串中'0'和'1'的数量
            int tmpM = 0, tmpN = 0;
            for (int p = 0; p < str.length(); p++) {
                char c = str.charAt(p);
                if ('0' == c) {
                    tmpM++;
                } else if ('1' == c) {
                    tmpN++;
                }
            }

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // '0'的数量 > 目标或者'1'的数量 > 目标，无法放入
                    if (j < tmpM || k < tmpN) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        // 决策
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - tmpM][k - tmpN] + 1);
                    }
                }
            }
        }

        return dp[l][m][n];
    }

    /**
     * 压缩状态，内层循环倒序遍历，时间复杂度不变，空间复杂度缩减至O(mn)
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm2(String[] strs, int m, int n) {
        int l = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= l; i++) {
            String str = strs[i-1];
            // 该字符串中'0'和'1'的数量
            int tmpM = 0, tmpN = 0;
            for (int p = 0; p < str.length(); p++) {
                char c = str.charAt(p);
                if ('0' == c) {
                    tmpM++;
                } else if ('1' == c) {
                    tmpN++;
                }
            }

            // 内层循环倒序
            for (int j = m; j >= tmpM; j--) {
                for (int k = n; k >= tmpN; k--) {
                    // 决策
                    dp[j][k] = Math.max(dp[j][k], dp[j - tmpM][k - tmpN] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
