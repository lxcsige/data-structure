package com.dp.algorithm.binarytree.bsttree;

/**
 * leetcode_96_不同的二叉搜索树_中等
 *
 * @author xucheng.liu
 * @since 2022/5/14
 */
public class NumTrees {

    /**
     * 动态规划
     * g(n)表示n个节点可以组成的二叉搜索树个数，注意，g(n)和节点序列的内容无关，仅和序列长度有关
     * f(i,n)表示以第i个节点为根节点的二叉搜索树个数
     * g(n) = f(1,n) + f(2,n) + f(3,n) + ... + f(n,n)
     * f(i,n) = g(i-1) * g(n-i),  1 =< i <= n
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        // 初始化边界
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}
