package com.dp.algorithm.dp.knapsack.knapsack01;

/**
 * 416_分割等和子集
 *
 * @author xucheng.liu
 * @since 2021/6/9
 */
public class CanPartition {

    public static void main(String[] args) {

    }

    /**
     * DP，转化为01背包问题
     * 难点在于理解边界，需要从状态转移方程推导初始化状态
     *
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        if (nums.length == 1) {
            return false;
        }

        // 求sum/2
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int n = nums.length;
        // dp[i][j]表示前i个数中是否存在和为j的子集
        // 状态转移方程：dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]
        // 如果dp[i-1][j]为true，则dp[i][j]一定为true，在二维数组上的表现就是一列元素都是true
        boolean[][] dp = new boolean[n + 1][target + 1];
        // 边界
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
                // 剪枝
                if (dp[i][target]) {
                    return true;
                }
            }
        }

        return dp[n][target];
    }

    /**
     * 压缩状态
     * 根据状态转移方程dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]，每次计算都只会用到上一层数据，因此可以压缩到一维数组
     * dp[i][j] -> dp[j], dp[i-1][j] -> dp[j], dp[i-1][j-nums[i]] -> dp[j-nums[i]]
     * 需要注意的是遍历顺序，每次计算都会用到前面的元素，如果前面的元素已经被覆盖则会导致本次计算结果有误
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums.length == 1) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        // 边界
        dp[0] = true;
        // 对于二维数组，外层从上往下
        for (int num : nums) {
            // 内层从后往前，若j < nums[i]则无法放入，dp[j]不会更新，因此可以提前结束
            for (int j = target; j >= num; j--) {
                // 剪枝
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }
}
