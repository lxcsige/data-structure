package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.*;

/**
 * leetcode_103_二叉树的锯齿形层序遍历_中等
 *
 * reviewed at 2023.04.20
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

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean leftOrder = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            res.add(tmp);
            // 记录该层节点数量
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (leftOrder) {
                    tmp.addLast(cur.val);
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
            leftOrder = !leftOrder;
        }

        return res;
    }
}
