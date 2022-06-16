package com.dp.algorithm.dp.subSequence;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode_337_打家劫舍3_中等
 *
 * @author xucheng.liu
 * @since 2022/5/16
 */
public class Rob3 {

    private Map<TreeNode, Integer> fMap = new HashMap<>();

    private Map<TreeNode, Integer> gMap = new HashMap<>();

    /**
     * 动态规划
     * o-根节点，l-左子节点，r-右子节点
     * f(o)表示选o节点时的金额，g(o)表示不选o节点时的金额
     * f(o) = g(l) + g(r) + o.val，选了o节点则不能选l和r节点
     * g(o) = Math.max(g(l), f(l)) + Math.max(g(r), f(r))，不选o节点，则l和r可选可不选，取最大值
     * 因为需要用到左右子树的结果来求根节点，因此采用后序遍历
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(fMap.getOrDefault(root, 0), gMap.getOrDefault(root, 0));
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        // 选root节点，f(root) = g(root.left) + g(root.right) + root.val
        fMap.put(root, gMap.getOrDefault(root.left, 0)
                + gMap.getOrDefault(root.right, 0) + root.val);
        // 不选root节点，g(root) = Math.max(f(root.left), g(root.left)) + Math.max(f(root.right), g(root.right))
        gMap.put(root, Math.max(fMap.getOrDefault(root.left, 0), gMap.getOrDefault(root.left, 0))
                + Math.max(fMap.getOrDefault(root.right, 0), gMap.getOrDefault(root.right, 0)));
    }

    /**
     * 状态压缩
     *
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        int[] res = dfs2(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * res[0]-选root节点的结果，res[1]-不选root节点的结果
     *
     * @param root
     * @return
     */
    private int[] dfs2(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs2(root.left);
        int[] right = dfs2(root.right);
        int[] res = new int[2];
        res[0] = left[1] + right[1] + root.val;
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}
