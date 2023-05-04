package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_501_二叉搜索树中的众数_简单
 */
public class FindMode {

    private List<Integer> res = new ArrayList<>();

    private int maxCnt = 1;

    private int cnt;

    private Integer pre;

    public int[] findMode(TreeNode root) {
        inOrder(root);
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        int cur = root.val;
        if (pre != null && cur == pre) {
            cnt++;
        } else {
            // 重置
            cnt = 1;
            pre = cur;
        }
        // 更新maxCnt以及结果
        if (cnt == maxCnt) {
            res.add(pre);
        } else if (cnt > maxCnt) {
            res.clear();
            res.add(pre);
            maxCnt = cnt;
        }
        inOrder(root.right);
    }
}
