package com.dp.algorithm.binarytree.traverse.reviewed;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.*;

/**
 * leetcode_145_二叉树的后序遍历_简单
 *
 * reviewed at 2023.04.19
 *
 * @author xucheng.liu
 * @since 2022/5/12
 */
public class PostorderTraversal {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t5.left = t6;
        t5.right = t7;
        PostorderTraversal test = new PostorderTraversal();
        test.postorderTraversal3(t1);
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
     * 辅助集合：保存右子树已经遍历过的节点
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 用于暂存中节点
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 右子树已经被遍历的节点集合
        Set<TreeNode> visited = new HashSet<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            // 没有右子节点或右子节点已经遍历过，说明所有子节点都已经遍历完，可以处理根节点了
            if (cur.right == null || visited.contains(cur)) {
                stack.pop();
                res.add(cur.val);
                cur = null;
            } else {
                // 存在右子节点，且没有遍历过
                visited.add(cur);
                cur = cur.right;
            }
        }

        return res;
    }

    /**
     * 迭代-模拟递归+prev指针
     * 推荐，节省空间
     * 统一
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
                System.out.println(prev.val);
                System.out.println(res);
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
     * 不常见
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

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                res.push(cur.val);
                cur = cur.right;
            }
            cur = stack.pop().left;
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
