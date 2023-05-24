package com.dp.algorithm.dp;

/**
 * leetcode_70_爬楼梯_简单
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
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        // 初始状态
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 压缩状态
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int prevPrev = 1, prev = 2, cur = 0;
        for (int i = 3; i <= n; i++) {
            cur = prevPrev + prev;
            prevPrev = prev;
            prev = cur;
        }
        return cur;
    }
}
