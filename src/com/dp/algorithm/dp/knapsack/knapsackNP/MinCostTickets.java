package com.dp.algorithm.dp.knapsack.knapsackNP;

/**
 * 983_最低票价_中等
 *
 * @author xucheng.liu
 * @since 2022/3/22
 */
public class MinCostTickets {

    public static void main(String[] args) {
        System.out.println(minCostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
    }

    /**
     * 先上车后买票
     *
     * 如果第i天不出行，dp[i] = dp[i-1]
     * 如果第i天出行，dp[i] = Math.min(dp[i-weights[j]]+costs[j])
     *
     * @param days
     * @param costs
     * @return
     */
    public static int minCostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length-1];
        int[] dp = new int[lastDay+31];

        int index = 0;
        for (int i = 31; i < dp.length; i++) {
            // 出行
            if (days[index] == i-30) {
                // dp[i-7]+cost[1]表示买一张为期7天的票钱+7天前的花费，这张票可以支持i-6...i这7天出行
                dp[i] = Math.min(Math.min(dp[i-1]+costs[0], dp[i-7]+costs[1]), dp[i-30]+costs[2]);
                index++;
            } else {
                // 不出行
                dp[i] = dp[i-1];
            }
        }

        return dp[lastDay+30];
    }
}
