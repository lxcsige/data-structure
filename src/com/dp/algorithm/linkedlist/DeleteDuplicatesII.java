package com.dp.algorithm.linkedlist;

/**
 * leetcode_82_删除排序链表中的重复元素II_中等
 *
 * reviewed at 2023.04.14
 *
 * @author xucheng.liu
 * @since 2022/5/6
 */
public class DeleteDuplicatesII {

    public static void main(String[] args) {

    }

    /**
     * 在原链表上删除节点，p.next = p.next.next
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy, q;
        // 循环边界：
        // p.next == null，表示到达尾节点
        // p.next.next == null，表示后面只剩下一个节点了，因此不会出现重复节点
        while (p.next != null && p.next.next != null) {
            // 后面2个节点值相同，一直往后找到第一个值不同的节点，其实就是将重复的节点从链表中移除
            // 但此时p.next节点不一定和后续节点不重复，因此不需要移动p指针
            if (p.next.val == p.next.next.val) {
                q = p.next.next;
                int val = q.val;
                while (q != null && q.val == val) {
                    q = q.next;
                }
                p.next = q;
            } else {
                // p.next节点和p.next.next节点值不同，因此链表中只有一个值为p.next.val的节点，移动p指针
                p = p.next;
            }
        }

        return dummy.next;
    }
}
