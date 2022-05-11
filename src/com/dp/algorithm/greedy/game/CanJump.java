package com.dp.algorithm.greedy.game;

/**
 * 55_跳跃游戏_贪心思路
 *
 * @author liuxucheng
 * @since 2021/6/22
 */
public class CanJump {

    public static void main(String[] args) {

    }

    /**
     * 贪心
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) {
                return true;
            }
            if (max == i) {
                return false;
            }
        }

        return false;
    }
}
