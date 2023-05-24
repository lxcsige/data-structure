package com.dp.algorithm.prefixsum;

/**
 * leetcode_1248_统计优美数组_中等
 */
public class NumberOfSubArrays {

    public static void main(String[] args) {
        NumberOfSubArrays test = new NumberOfSubArrays();
        test.numberOfSubArrays(new int[]{1,1,2,1,1}, 3);
    }

    /**
     * prefix[i]表示nums[0..i]之间的奇数个数
     * nums[i..j]之间的奇数个数 = prefix[i] - prefix[j-1]
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubArrays(int[] nums, int k) {
        int n = nums.length;
        // 记录前缀和出现的次数，下标表示前缀和，即奇数的个数，元素表示该前缀和出现的次数
        int[] cnt = new int[n + 1];
        // 注意边界，对应pre == k的情况，即以index 0为起点
        cnt[0] = 1;
        // 前缀和
        int pre = 0;
        int res = 0;
        for (int num : nums) {
            // 计算当前前缀和
            pre += num & 1;
            // 找到前缀和pre-k出现的次数，以当前num为后缀的优美子数组一共有cnt[pre-k]种
            res += pre >= k ? cnt[pre - k] : 0;
            // 递增该前缀和出现的次数
            cnt[pre]++;
        }
        return res;
    }

    public int numberOfSubArrays2(int[] nums, int k) {
        int[] odds = new int[nums.length + 2];
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                odds[++cnt] = i;
            }
        }
        // 处理边界
        // 第0个奇数的下标为-1，第1个奇数的下标为i，二者之间有i个偶数，一共有i+1种选择
        odds[0] = -1;
        // 走完这步后cnt等于奇数数量+1，用于处理最后一个奇数到数组结尾的边界
        odds[++cnt] = nums.length;
        int res = 0;
        for (int i = 1; i + k <= cnt; i++) {
            res += (odds[i] - odds[i - 1]) * (odds[i + k] - odds[i + k - 1]);
        }
        return res;
    }
}
