package com.jetbone.others.common;

/**
 * Created by Chris on 2019-06-03.
 */
public class Test {

    public static void main(String[] args) {

        Test test = new Test();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = test.addTwoNumbers(l1, l2);
        System.out.println(res);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    private ListNode add(ListNode l1, ListNode l2, int carry) {
        ListNode node = null;
        if (l1 != null || l2 != null) {
            int p = 0;
            int q = 0;
            if (l1 != null) {
                p = l1.val;
            }
            if (l2 != null) {
                q = l2.val;
            }
            int result = p + q + carry;
            if (result >= 10) {
                carry = 1;
            } else {
                carry = 0;
            }
            node = new ListNode(result % 10);
            node.next = add(l1 == null ? null : l1.next, l2 == null ? null : l2.next, carry);
        } else {
            if (carry == 1) {
                node = new ListNode(carry);
            }
        }

        return node;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            String res = String.valueOf(val);
            if (next != null) {
                res = res + " -> " + next.toString();
            }

            return res;
        }
    }
}
