package com.zl.geekdata.cyc2018.doublepoint;

/**
 * 141.142两题
 * Create by zhanglong on 2020/4/16
 */
public class LinkedListCycle_141 {

    public static void main(String[] args) {
        LinkedListCycle_141 t = new LinkedListCycle_141();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next.next;
        t.detectCycle(head);
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        // 此时快指针走了2n,慢指针走了n
        // fast: s + nc + p, slow: s + mc + p
        // (n - m) c = s + mc + p -> s + p = kc
        // 所以当前在p点的指针再走s步就是kc，刚好成环,再次相交
        // 所以一个指针从头再走s到交界点，另一个在交界点再走s又回到交界点
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
