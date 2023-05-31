package com.dp.algorithm.string;

public class CompareVersion {

    /**
     * 思路：双指针
     * 1. 将每段子串转成整数，可以忽略前导0的存在
     * 2. 某一段不存在时，默认为0
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        int m = version1.length(), n = version2.length();
        int i = 0, j = 0;
        while (i < m || j < n) {
            int num1 = 0;
            while (i < m && version1.charAt(i) != '.') {
                num1 = num1 * 10 + version1.charAt(i++) - '0';
            }
            i++;
            int num2 = 0;
            while (j < n && version2.charAt(j) != '.') {
                num2 = num2 * 10 + version2.charAt(j++) - '0';
            }
            j++;
            if (num1 != num2) {
                return num1 > num2 ? 1 : -1;
            }
        }
        return 0;
    }
}
