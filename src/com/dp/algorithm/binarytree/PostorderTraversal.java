package com.dp.algorithm.binarytree;

import java.util.*;

/**
 * leetcode_145_二叉树的后序遍历_简单
 *
 * @author xucheng.liu
 * @since 2022/5/12
 */
public class PostorderTraversal {

    public static void main(String[] args) {

    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        dfs(root.left, res);
        dfs(root.right, res);
        res.add(root.val);
    }

    /**
     * 迭代-模拟递归+辅助集合
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        // 右子树已经被遍历的节点集合
        Set<TreeNode> visited = new HashSet<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || visited.contains(cur)) {
                stack.pop();
                res.add(cur.val);
                cur = null;
            } else {
                visited.add(cur);
                cur = cur.right;
            }
        }

        return res;
    }

    /**
     * 迭代，模拟递归
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 用prev节点记录是从左子节点回到根节点，还是从右子节点回到根节点
        TreeNode cur = root, prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 从栈中弹出的元素，左子树一定是访问完了的
            cur = stack.pop();
            // 如果没有右子树，或者右子树访问完了，也就是上一个访问的节点是右子节点时，说明可以访问当前节点
            if (cur.right == null || cur.right == prev) {
                res.add(cur.val);
                // 说明cur的左右子树都已经访问完了
                prev = cur;
                // 下一个要push的节点为空，则继续pop栈顶元素
                cur = null;
            } else {
                // 如果右子树存在且没有被访问，那么将当前节点压栈，访问右子树
                stack.push(cur);
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * 迭代-利用栈后进先出的特性
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        // 子节点全部遍历过的节点集合
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            // 没有子节点或子节点全部遍历过，则直接出栈
            if ((cur.left == null && cur.right == null) || visited.contains(root)) {
                stack.pop();
                res.add(cur.val);
                continue;
            }
            // 反向入栈
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            // 子节点全部入栈，将root加入到visited集合中
            visited.add(root);
        }

        return res;
    }

    /**
     * 迭代-前序遍历转换
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal5(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                res.add(cur.val);
                cur = cur.right;
            }
            cur = stack.pop();
            cur = cur.left;
        }

        return res;
    }

    /**
     * 迭代-莫里斯遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal6(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root, prev;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                // 第一次遍历
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次遍历，此时prev.right == cur
                    // 还原
                    prev.right = null;
                    addList(cur.left, res);
                    // 左子树已经遍历完，继续遍历右子树
                    cur = cur.right;
                }
            }
        }
        // 注意，最后需要将root节点的右子节点，右子节点的右子节点...最右节点倒序插入到结果中
        addList(root, res);

        return res;
    }

    private void addList(TreeNode node, List<Integer> res) {
        int count = 0;
        while (node != null) {
            res.add(node.val);
            node = node.right;
            count++;
        }
        int left = res.size() - count;
        int right = res.size() - 1;
        while (left < right) {
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
        }
    }
}
