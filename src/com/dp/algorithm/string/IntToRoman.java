package com.dp.algorithm.string;

/**
 * leetcode_12_整数转罗马数字_中等
 *
 * @author liuxucheng
 * @since 2023/5/29
 */
public class IntToRoman {

    /**
     * 贪心，优先选数值较大的字符
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int[] nums = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for (int i = 0; i < nums.length; i++) {
            while (num >= nums[i]) {
                res.append(romans[i]);
                num -= nums[i];
            }
            if (num == 0) {
                break;
            }
        }
        return res.toString();
    }
}
