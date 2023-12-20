package LeetCode;

import java.util.StringJoiner;

public class DeleteNodeWithoutHeadAccess {
    public static void main(String[] args) {
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

        deleteNode(node2);

        System.out.println(node1);
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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
}
