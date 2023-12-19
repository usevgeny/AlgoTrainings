package CrackingInterview;

import java.util.StringJoiner;

public class LinkedListTraverseBis {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList<>();

        linkedList.add("s1");
        linkedList.add("s2");
        linkedList.add("s3");
        linkedList.add("s4");
        System.out.println(linkedList);
        linkedList.reverse();
        System.out.println(linkedList);
    }

    private static class LinkedList<T> {
        private Node head;

        public class Node {
            final T data;
            private Node next;

            public Node(T data) {
                this.data = data;
                next = null;
            }
        }

        @Override
        public String toString() {
            Node current = head;
            StringJoiner joiner = new StringJoiner("->", "[", "]");
            while (current != null) {
                joiner.add(current.data.toString());
                current = current.next;
            }
            ;

            return joiner.toString();
        }

        // Before changing next of current, store next node
        // then change next of current, here is where actual reversing happens
        // Move prev and current one step forward
        public void reverse() {
            Node previous = null;
            Node next = null;
            Node current = head;
            while (current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
        }

        public void add(T data) {
            Node node = new Node(data);
            if (head == null) {
                head = node;
            } else {
                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = node;
            }
        }

    }

}
