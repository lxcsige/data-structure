package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 求二叉树根结点到指定节点的路径
 *
 * @author liuxucheng
 * @since 2023/5/24
 */
public class GetPathToTarget {

    public List<TreeNode> getPathToTarget(TreeNode root, TreeNode target) {
        LinkedList<TreeNode> path = new LinkedList<>();
        if (root == null || target == null) {
            return path;
        }
        backtrack(root, target, path);
        return path;
    }

    private boolean backtrack(TreeNode root, TreeNode target, LinkedList<TreeNode> path) {
        if (root == null) {
            return false;
        }
        path.offerLast(root);
        if (root == target) {
            return true;
        }
        if (backtrack(root.left, target, path)) {
            return true;
        }
        if (backtrack(root.right, target, path)) {
            return true;
        }
        path.pollLast();
        return false;
    }
}
