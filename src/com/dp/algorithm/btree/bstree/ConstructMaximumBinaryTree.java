package com.dp.algorithm.btree.bstree;

import com.dp.algorithm.btree.TreeNode;

/**
 * @author xucheng.liu
 * @date 2021/3/24
 */
public class ConstructMaximumBinaryTree {

    public static void main(String[] args) {

    }

    public TreeNode constructMaximumBinaryTree(int[] nums){
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
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
}
