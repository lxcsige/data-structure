package com.dp.algorithm.dp;

/**
 * leetcode_70_爬楼梯，斐波那契数列_简单
 *
 * reviewed at 2023.05.03
 *
 * @author liuxucheng
 * @since 2021/6/12
 */
public class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs test = new ClimbStairs();
        System.out.println(test.climbStairs(5));
        System.out.println(test.climbStairs2(5));
    }

    /**
     * dp[i] = sum(dp[i-stair])
     * dp[i]表示爬i阶楼梯的不同排列数，对于满足i-stair的一种台阶排列，再加上stair就可以得到满足i的一种台阶排列
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i >= 1) {
                dp[i] += dp[i-1];
            }
            if (i >= 2) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    /**
     * 压缩状态
     * 注意n=1的情况
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int p = 1, q = 1, r;
        for (int i = 2; i <= n; i++) {
            r = p + q;
            p = q;
            q = r;
        }
        return q;
    }
}
