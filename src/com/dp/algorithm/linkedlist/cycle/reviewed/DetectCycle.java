package com.dp.algorithm.linkedlist.cycle.reviewed;

import com.dp.algorithm.linkedlist.ListNode;

/**
 * leetcode_142_环形链表II_中等
 *
 * @author xucheng.liu
 * @date 2021/3/1
 */
public class DetectCycle {

    public static void main(String[] args) {
        ListNode n1 =  new ListNode(3);
        ListNode n2 =  new ListNode(2);
        ListNode n3 =  new ListNode(0);
        ListNode n4 =  new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
        DetectCycle main = new DetectCycle();
        main.detectCycle(n1);
    }

    /**
     * 快慢指针
     * 假设head到入口节点要走a步，环的长度为b步
     * 如果slow和fast相遇，slow指针走了s步，fast指针走了f步，那么f = 2s，因为slow走一步，fast会走2步
     * 同时fast指针比slow多走了n个环的长度，即f = s + nb，那么s + nb = 2s，s = nb，f = 2nb
     * 如果想要走到入口节点，那么该指针一定走了a + kb步，而此时slow指针刚好走了nb步
     * 此时，可以让一个指针从头开始走，当它和slow指针相遇时，相遇节点一定就是环入口节点，slow指针一共走了a + nb步
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 相遇，存在环
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
