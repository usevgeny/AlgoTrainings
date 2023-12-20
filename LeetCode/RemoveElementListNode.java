package LeetCode;

import java.util.StringJoiner;

public class RemoveElementListNode {
    public static void main(String[] args) {
        // [1,2,6,3,4,5,6]
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;

        System.out.println(node1);

        System.out.println(removeK(node1, 6));

    }

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode current = this;
            StringJoiner joiner = new StringJoiner("->", "[", "]");
            while (current != null) {
                joiner.add(String.valueOf(current.val));
                current = current.next;
            }
            return joiner.toString();
        }
    }

    public static ListNode removeK(ListNode head, int k) {
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode current = helper;
        while (current.next != null) {
            if (current.next.val == k) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return helper.next;
    }

}
