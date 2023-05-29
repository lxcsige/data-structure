package com.dp.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_229_多数元素II_中等
 *
 * @author liuxucheng
 * @since 2023/5/30
 */
public class MajorityElementII {

    public static void main(String[] args) {
        MajorityElementII test = new MajorityElementII();
        test.majorityElement(new int[]{2,1,1,3,1,4,5,6});
    }

    /**
     * 最多只有2个元素的出现次数超过n/3
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int major1 = 0, vote1 = 0;
        int major2 = 0, vote2 = 0;
        // 对抗阶段
        for (int num : nums) {
            if (vote1 > 0 && major1 == num) {
                vote1++;
            } else if (vote2 > 0 && major2 == num) {
                vote2++;
            } else if (vote1 == 0) {
                major1 = num;
                vote1++;
            } else if (vote2 == 0) {
                major2 = num;
                vote2++;
            } else {
                // 互不相同，所有候选元素抵消一次
                vote1--;
                vote2--;
            }
        }
        // 计数阶段
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == major1) {
                cnt1++;
            } else if (vote2 > 0 && num == major2) {
                cnt2++;
            }
        }
        List<Integer> res = new ArrayList<>();
        if (cnt1 > nums.length / 3) {
            res.add(major1);
        }
        if (cnt2 > nums.length / 3) {
            res.add(major2);
        }
        return res;
    }
}
