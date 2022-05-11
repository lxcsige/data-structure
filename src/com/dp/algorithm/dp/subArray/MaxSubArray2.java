package com.dp.algorithm.dp.subArray;

/**
 * lintCode_43_最大子数组III_困难
 *
 * @author xucheng.liu
 * @since 2022/3/22
 */
public class MaxSubArray2 {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-1, 4, -2, 3, -2, 3}, 2));
    }

    public static int maxSubArray(int[] nums, int k) {
        int length = nums.length;
        // dp1[i][j]表示前i个元素j个子数组的最优解
        int[][] dp1 = new int[length+1][k+1];
        // dp2[i][j]表示前i个元素j个子数组的最优解，且第i个元素必须在第j个子数组中
        int[][] dp2 = new int[length+1][k+1];

        for (int i = 1; i <= length; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                if (i == j) {
                    dp2[i][j] = dp2[i-1][j-1] + nums[i-1];
                    dp1[i][j] = dp1[i-1][j-1] + nums[i-1];
                } else {
                    // dp1[i-1][j-1]表示num[i]单独在第j组的情况
                    // dp2[i-1][j]表示nums[i]和nums[i-1]都在第j组的情况
                    dp2[i][j] = Math.max(dp1[i-1][j-1], dp2[i-1][j]) + nums[i-1];
                    // dp1[i-1][j]表示不选nums[i]的情况
                    // dp2[i][j]表示选了nums[i]的情况，nums[i]可能单独一组，也可能和前一个元素在同一组
                    dp1[i][j] = Math.max(dp1[i-1][j], dp2[i][j]);
                }
            }
        }

        return dp1[length][k];
    }
}
