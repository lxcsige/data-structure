package com.dp.algorithm.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode_103_二叉树的锯齿形层序遍历_中等
 *
 * @author xucheng.liu
 * @since 2022/5/23
 */
public class ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftOrder = true;
        TreeNode cur;
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                if (leftOrder) {
                    tmp.add(cur.val);
                } else {
                    tmp.addFirst(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(tmp);
            leftOrder = !leftOrder;
        }

        return res;
    }
}
