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
        // dp[i][j]表示nums[0...i]中是否存在和为j的子集
        // 状态转移方程：dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
        // 如果dp[i-1][j]为true，则dp[i][j]一定为true，在二维数组上的表现就是一列元素都是true
        // 对于这种返回boolean的问题，没必要将dp数组的行数设为nums.length+1
        boolean[][] dp = new boolean[nums.length][target + 1];
        // 边界
        for (int i = 0; i < nums.length; i++) {
            // 其实就是j = nums[i]这种特殊情况，即nums[i]刚好可以放到容积为j的背包中，从背包问题的角度来看是符合题意的
            dp[i][0] = true;
        }
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
                // 剪枝
                if (dp[i][target]) {
                    return true;
                }
            }
        }

        return dp[nums.length - 1][target];
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
        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }

        // 对于二维数组，外层从上往下
        for (int i = 1; i < nums.length; i++) {
            // 内层从后往前，若j < nums[i]则无法放入，dp[j]不会更新，因此可以提前结束
            for (int j = target; j >= nums[i]; j--) {
                // 剪枝
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }

        return dp[target];
    }
}
