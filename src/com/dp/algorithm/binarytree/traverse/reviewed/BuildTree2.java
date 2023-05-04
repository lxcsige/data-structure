package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_106_从中序和后序遍历构建二叉树_中等
 *
 * @author xucheng.liu
 * @since 2022/5/15
 */
public class BuildTree2 {

    private Map<Integer, Integer> val2IndexMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            val2IndexMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                                int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // 1. 从后序数组中找到根节点，即后序数组中最后一个节点
        TreeNode root = new TreeNode(postorder[postEnd]);
        // 2. 找到根节点在中序数组中的index
        int rootIdx = val2IndexMap.get(root.val);
        int leftSize = rootIdx - inStart;
        // 3. 找左子树的根节点
        root.left = buildTree(inorder, inStart, rootIdx - 1,
                postorder, postStart, postStart + leftSize - 1);
        // 4. 找右子树的根节点
        root.right = buildTree(inorder, inStart + leftSize + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);

        return root;
    }
}
