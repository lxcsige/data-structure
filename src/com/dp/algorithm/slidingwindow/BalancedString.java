package com.dp.algorithm.slidingwindow;

/**
 * leetcode_1234_替换子串得到平衡字符串_中等
 */
public class BalancedString {

    public int balancedString(String s) {
        int[] cntArr = new int['W' - 'E' + 1];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cntArr[s.charAt(i) - 'E']++;
        }
        int m = n / 4;
        if (cntArr['Q' - 'E'] == m && cntArr['W' - 'E'] == m && cntArr['E' - 'E'] == m && cntArr['R' - 'E'] == m) {
            return 0;
        }
        int res = n;
        for (int l = 0, r = 0; r < n; r++) {
            cntArr[s.charAt(r) - 'E']--;
            while (cntArr['Q' - 'E'] <= m && cntArr['W' - 'E'] <= m && cntArr['E' - 'E'] <= m && cntArr['R' - 'E'] <= m) {
                res = Math.min(res, r - l + 1);
                cntArr[s.charAt(l++) - 'E']++;
            }
        }
        return res;
    }
}
