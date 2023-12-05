package LevelOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinkedListIterations {

    // first approach check every node and save it in a list of checked nodes
    // then verify if every next node is already present i a list of checked nodes -> then it is a beginning of the
    // cycle

    public static ListNode detectCycleFirstMethod(ListNode head) {
        Long start = System.currentTimeMillis();
        List<ListNode> visitedNodes = new ArrayList<>();
        ListNode current = head;
        while (null != current.getNext()) {
            visitedNodes.add(current);
            current = current.getNext();
            if (visitedNodes.contains(current)) {
                Long end = System.currentTimeMillis();
                System.out.println("Time to find cycle start node: " + (end - start) + "millis");
                return current;
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("Time to find cycle start node: " + (end - start) + "millis");
        return null;
    }

    public static ListNode repeatTortoireAlgo(ListNode head) {
        Long start = System.currentTimeMillis();
        ListNode slow = head, fast = head;
        // firstStep detect cycle
        while (null != fast || null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (null == fast || null == fast.next) {
            Long end = System.currentTimeMillis();
            System.out.println("Time to find cycle start node: " + (end - start) + "millis");
            return null;
        }

        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        Long end = System.currentTimeMillis();
        System.out.println("Time to find cycle start node: " + (end - start) + "millis");

        return head;
    }

    public static ListNode detectCycleTortoiseAlgorythm(ListNode head) {
        Long start = System.currentTimeMillis();
        // first step
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null || fast.getNext() != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break; // stop while block when cycle is detected
            }
        }
        if (fast == null || fast.getNext() == null) {
            Long end = System.currentTimeMillis();
            System.out.println("no cycle found. Time since begining: " + (end - start) + "millis");
            return null;
        }

        // second step
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }

        Long end = System.currentTimeMillis();
        System.out.println("Time to find cycle start node: " + (end - start) + "millis");
        return head;
    }

    public static int[] fillArrayy(int[] arrayToFill, int maxRandomNumber) {
        Random ran = new Random();
        for (int i = 0; i < arrayToFill.length; i++) {
            arrayToFill[i] = i; // ran.nextInt(maxRandomNumber);
        }
        return arrayToFill;
    };

    // Generating dataset. Method to create a linked list with a cycle
    public static ListNode createLinkedListWithCycle(int[] values, Integer cycleIndex) {
        if (values == null || values.length == 0
                || (null != cycleIndex && (cycleIndex < 0 || cycleIndex >= values.length))) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleStart = null;

        for (int i = 1; i < values.length; i++) {
            current.setNext(new ListNode(values[i]));
            current = current.getNext();

            if (null != cycleIndex && i == cycleIndex) {
                cycleStart = current; // Save the node where the cycle starts
            }
        }

        // Connect the last node to the cycle start to create a cycle
        if (null != cycleIndex && cycleIndex == 0) {
            current.setNext(head);
        } else {
            current.setNext(cycleStart);
        }
        return head;
    }

    // second approach would be to use a 'slow' and 'fast' pointers
    public static void main(String[] args) {
        // working with linear data -> no cycle present
        // linear data; manual dataset
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);
        ListNode seventh = new ListNode(7);
        ListNode eight = new ListNode(8);
        head.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(fifth);
        fifth.setNext(sixth);
        sixth.setNext(seventh);
        seventh.setNext(eight);
        // check if there is a cycle
        ListNode cycleStartNode = detectCycleFirstMethod(head);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());

        // working with mixed linear and cycled data
        head.setNext(fourth);
        fourth.setNext(second);
        second.setNext(third);
        third.setNext(fifth);
        fifth.setNext(sixth);
        sixth.setNext(second);
        // check if there is a cycle
        cycleStartNode = detectCycleFirstMethod(head);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());

        // working with cycled data
        head.setNext(third);
        third.setNext(fourth);
        fourth.setNext(second);
        second.setNext(head);
        // check if there is a cycle
        cycleStartNode = detectCycleFirstMethod(head);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        // refactoring: generate dataset via a dedicatd method, use Floyd's Tortoise and Hare algorithm to detect cycle

        System.out.println("REFACTORED PART");
        ListNode head2 = createLinkedListWithCycle(new int[] { 1, 4, 2, 3, 5, 6 }, null);
        cycleStartNode = detectCycleFirstMethod(head2);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        head2 = createLinkedListWithCycle(new int[] { 1, 4, 2, 3, 5, 6 }, 2);
        cycleStartNode = detectCycleFirstMethod(head2);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        ListNode head3 = createLinkedListWithCycle(new int[] { 1, 3, 4, 2 }, 0);
        cycleStartNode = detectCycleFirstMethod(head3);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());

        ListNode head4 = createLinkedListWithCycle(fillArrayy(new int[100_000], 100_000), 99999);
        cycleStartNode = detectCycleFirstMethod(head4);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
//
        System.out.println("TORTOISE AND HARE ALGORITHM");
        cycleStartNode = detectCycleTortoiseAlgorythm(head);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        cycleStartNode = detectCycleTortoiseAlgorythm(head2);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        cycleStartNode = detectCycleTortoiseAlgorythm(head3);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        System.out.println();
        cycleStartNode = detectCycleTortoiseAlgorythm(head4);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        System.out.println("test");
        cycleStartNode = repeatTortoireAlgo(head4);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
        System.out.println("COMPPARING LARGE AMOUNT OF DATA PROCESSING WITHT THE OLD METHOD");
        cycleStartNode = detectCycleFirstMethod(head4);
        System.out.println(cycleStartNode == null ? "null" : cycleStartNode.toString());
//
    }

};

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "ListNode [val=" + val + "]";
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
};