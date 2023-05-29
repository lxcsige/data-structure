package com.dp.algorithm.slidingwindow;

/**
 * leetcode_567_字符串的排列_中等
 *
 * @author xucheng.liu
 * @since 2022/5/2
 */
public class CheckInclusion {

    public static void main(String[] args) {
        System.out.println(checkInclusion2("abcdxabcde", "abcdeabcdx"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m > n) {
            return false;
        }
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (int i = 0; i < m; i++) {
            arr1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0, j = 0; j < n; j++) {
            char ch = s2.charAt(j);
            arr2[ch - 'a']++;
            // 收缩窗口
            while (arr2[ch - 'a'] > arr1[ch - 'a']) {
                char left = s2.charAt(i++);
                arr2[left - 'a']--;
            }
            if (j - i + 1 == m) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            cnt1[s1.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0, j = 0; j < m; j++) {
            char ch = s2.charAt(j);
            cnt2[ch - 'a']++;
            if (cnt2[ch - 'a'] <= cnt1[ch - 'a']) {
                count++;
            }
            // 收缩
            while (j - i + 1 >= n) {
                if (count == n) {
                    return true;
                }
                char left = s2.charAt(i++);
                if (cnt2[left] <= cnt1[left]) {
                    count--;
                }
                cnt2[left]--;
            }
        }
        return false;
    }
}
