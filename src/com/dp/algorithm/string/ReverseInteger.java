package com.dp.algorithm.string;

/**
 * leetcode_7_整数反转_中等
 */
public class ReverseInteger {

    /**
     * 当x的位数和Integer.MAX_VALUE/MIN_VALUE相同时，反转之后可能出现溢出
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        int res = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            // res * 10 + digit <= Integer.MAX_VALUE（2147483647）
            // 对于Integer.MAX_VALUE，其最高位digit最大为2，无论如何都小于7
            // 因此只需要res <= 214748364即可
            if (res > max || res < min) {
                return 0;
            }
            res = res * 10 + digit;
        }
        return res;
    }

    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            int temp = res * 10 + digit;
            // 说明溢出
            if (temp / 10 != res) {
                return 0;
            }
            res = temp;
        }
        return res;
    }
}
