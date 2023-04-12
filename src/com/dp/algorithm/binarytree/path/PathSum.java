package com.dp.algorithm.binarytree.path;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_113_路径总和2_中等
 *
 * @author xucheng.liu
 * @since 2022/5/26
 */
public class PathSum {

    /**
     * DFS递归-每个分支新建一个list保存当前path，防止分支之间的污染
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), res);
        return res;
    }

    private void dfs(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        List<Integer> tmpPath = new ArrayList<>(path);
        tmpPath.add(root.val);
        if (root.left == null && root.right == null && root.val == targetSum) {
            res.add(tmpPath);
            return;
        }
        dfs(root.left, targetSum - root.val, tmpPath, res);
        dfs(root.right, targetSum - root.val, tmpPath, res);
    }

    /**
     * 递归DFS+回溯
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs2(root, targetSum, path, res);
        return res;
    }

    private void dfs2(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        // 将root节点加入到path中
        path.add(root.val);
        targetSum -= root.val;
        // root节点为叶节点，同时targetSum == root.val，此时path就是一条符合题意的路径，需要加入到路径列表中
        if (root.left == null && root.right == null && targetSum == 0) {
            res.add(new ArrayList<>(path));
            // 回溯
            path.remove(path.size() - 1);
            return;
        }
        dfs2(root.left, targetSum, path, res);
        dfs2(root.right, targetSum, path, res);
        // 回溯
        path.remove(path.size() - 1);
    }
}
