package com.dp.algorithm.binarytree.bst.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

/**
 * leetcode_450_删除二叉搜索树中的节点_中等
 *
 * reviewed at 2023.04.20
 *
 * @author xucheng.liu
 * @date 2021/3/24
 */
public class DeleteNode {

    public static void main(String[] args) {

    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            // 1. 左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
            // 2. 左孩子为空，右孩子不为空，删除节点，右孩子补位 ，返回右孩子为根节点
            if (root.left == null) {
                return root.right;
            }
            // 3. 右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
            else if (root.right == null) {
                return root.left;
            }
            // 4. 左右孩子都不为空
            else {
                // 4.1 右子节点的左节点为空，右子节点补位
                if (root.right.left == null) {
                    root.right.left = root.left;
                    root = root.right;
                }
                // 4.2 右子节点的左子节点不为空，一直向下找到最左节点，并补位至根结点位置
                else {
                    root.val = deleteMin(root.right);
                }
                return root;
            }
        }
    }

    private int deleteMin(TreeNode node) {
        TreeNode left = node.left;

        while (left.left != null) {
            node = left;
            left = left.left;
        }
        node.left = left.right;

        return left.val;
    }
}
