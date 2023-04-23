package com.dp.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode_654_最大二叉树_中等
 *
 * reviewed at 2023.04.20
 *
 * @author xucheng.liu
 * @date 2021/3/24
 */
public class ConstructMaximumBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 前序DFS
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums){
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int maxIndex = findMaxIndex(nums, start, end);
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructMaximumBinaryTree(nums, start, maxIndex - 1);
        node.right = constructMaximumBinaryTree(nums, maxIndex + 1, end);
        return node;
    }

    private int findMaxIndex(int[] nums, int start, int end) {
        int max = nums[start];
        int res = start;
        while (++start <= end) {
            if (max < nums[start]) {
                max = nums[start];
                res = start;
            }
        }
        return res;
    }

    /**
     * 单调栈
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int num : nums) {
            TreeNode cur = new TreeNode(num);
            while (!stack.isEmpty() && stack.peek().val < cur.val) {
                cur.right = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().left = cur;
            }
            stack.push(cur);
        }
        return stack.peekLast();
    }
}
