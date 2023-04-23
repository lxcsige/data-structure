package com.dp.algorithm.binarytree.path.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_257_二叉树的所有路径_简单
 */
public class BinaryTreePaths {

    /**
     * 递归+回溯
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<String> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    private void dfs(TreeNode root, List<String> path, List<String> res) {
        // 将节点值加到路径中
        path.add(root.val + "");
        // 叶子节点，加入结果集
        if (root.left == null && root.right == null) {
            String pathStr = String.join("->", path);
            res.add(pathStr);
        }
        if (root.left != null) {
            dfs(root.left, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            dfs(root.right, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
    }
}
