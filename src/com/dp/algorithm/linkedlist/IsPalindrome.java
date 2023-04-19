package com.dp.algorithm.linkedlist;

/**
 * leetcode_234_回文链表_简单
 *
 * @author liuxucheng
 * @since 2021/3/9
 */
public class IsPalindrome {

    private ListNode front;

    public static void main(String[] args) {

    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode firstHalfEnd = this.getFirstHalfEnd(head);
        ListNode secondHalfHead = this.reverseList(firstHalfEnd.next);

        ListNode p = head, q = secondHalfHead;
        while (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
            p = p.next;
            q = q.next;
        }

        firstHalfEnd.next = this.reverseList(secondHalfHead);

        return true;
    }

    private ListNode getFirstHalfEnd(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 倒序打印
     *
     * @param head
     */
    public void reversePrint(ListNode head) {
        // base case
        if (head == null) {
            return;
        }
        // 先打印后面的节点
        reversePrint(head.next);
        // 最后打印head节点
        System.out.println(head.val);
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        front = head;
        return check(head);
    }

    private boolean check(ListNode head) {
        if (head == null) {
            return true;
        }
        if (!check(head.next)) {
            return false;
        }
        if (head.val != front.val) {
            return false;
        }
        // 递归返回后front才会向后移动
        // 对于最后一个节点，递归返回后，front等于第一个节点，然后继续向后移动
        front = front.next;
        return true;
    }
}
