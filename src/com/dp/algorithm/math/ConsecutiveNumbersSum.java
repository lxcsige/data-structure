package com.dp.algorithm.math;

/**
 * leetcode_829_连续整数求和_困难
 */
public class ConsecutiveNumbersSum {

    /**
     * 假设连续正整数起始值为a，共k个，那么(a+a+k-1)*k/2=n
     * 2a+k-1=2n/k
     * 2a=2n/k-k+1，k必然是2n的约数
     * 由于a是正整数，那么2n/k-k+1>=2，2n/k>=k+1，2n/k>k，k*k<2n
     *
     * @param n
     * @return
     */
    public int consecutiveNumbersSum(int n) {
        n *= 2;
        int res = 0;
        for (int k = 1; k * k < n; k++) {
            if (n % k != 0) {
                continue;
            }
            // 此时可以得到一个起始值
            if (((n / k - k + 1) & 1) == 0) {
                res++;
            }
        }
        return res;
    }
}
