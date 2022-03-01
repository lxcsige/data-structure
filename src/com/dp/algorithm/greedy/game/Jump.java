package com.dp.algorithm.greedy.game;

/**
 * 45_跳跃游戏2_贪心思路
 *
 * @author liuxucheng
 * @since 2021/6/22
 */
public class Jump {

    public static void main(String[] args) {
        Jump test = new Jump();
        System.out.println(test.jump2(new int[]{1,1,1,1,4}));
    }

    /**
     * 正向贪心
     * 这个写法可以很好地处理边界问题
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        // 步数
        int step = 0;
        // 上一跳之后能到达的最大下标
        int end = 0;
        // 本次跳跃之后能到达的最大下标
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            // 到达end之后需要下一跳，跳到i和end之间j+nums[j]最大的那个下标，并且用最大的j+nums[j]更新end
            if (i == end) {
                // 这里提前进行step++
                ++step;
                end = max;
            }
            if (end >= nums.length - 1) {
                return step;
            }
        }
        return step;
    }

    public int jump3(int[] nums) {
        // 额外处理边界问题
        if (nums.length == 1) {
            return 0;
        }
        int step = 0;
        int end = nums[0];
        int max = 0;
        int index = 1;
        while (end < nums.length - 1) {
            for (; index <= end; index++) {
                max = Math.max(max, index + nums[index]);
            }
            end = max;
            ++step;
        }
        return ++step;
    }

    /**
     * 反向贪心，每次选择下标最小的position
     * 时间复杂度O(n^2)，空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int jump4(int[] nums) {
        int step = 0;
        int position = nums.length - 1;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }
}
