package Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StairsSteps {

    public static void main(String[] args) {
        int numerOfStes = 10;
        int[] possibleSteps = { 2, 4, 5, 8 };

        System.out.println(ways(numerOfStes, possibleSteps));
        System.out.println(ways(numerOfStes, possibleSteps, new HashMap<Integer, Integer>()));
        Tree recursionTree = new Tree();
        System.out.println(ways(numerOfStes, possibleSteps, new HashMap<Integer, Integer>(), recursionTree));
        printTree(recursionTree, null);

    }

    public static int ways(int size, int[] steps) {
        if (size == 0) {
            return 1;
        }
        int counter = 0;
        for (int step : steps) {
            if (size > 0) {
                counter += ways(size - step, steps);
            }
        }
        return counter;
    }

    public static int ways(int size, int[] steps, Map<Integer, Integer> readyValues) {
        if (readyValues.containsKey(size)) {
            return readyValues.get(size);
        } else if (size == 0) {
            return 1;
        }
        int counter = 0;
        for (int step : steps) {
            if (size > 0) {
                counter += ways(size - step, steps, readyValues);
            }
        }
        return counter;
    }

    public static int ways(int size, int[] steps, Map<Integer, Integer> readyValues, Tree tree) {
        tree.setCall("ways(" + size + ")");
        if (readyValues.containsKey(size)) {
            return readyValues.get(size);
        } else if (size == 0) {
            return 1;
        }
        int counter = 0;
        for (int step : steps) {
            if (size > 0) {
                Tree child = new Tree();
                tree.getChildren().add(child);
                counter += ways(size - step, steps, readyValues, child);
            }
        }
        tree.setReturned(String.valueOf(counter));
        return counter;
    }

    public static class Tree {
        @Override
        public String toString() {
            return "Tree []" + call + "returned " + returned;
        }

        public static String call = "";
        public static String returned = "";
        public static List<Tree> children = new ArrayList<>();

        public static String getCall() {
            return call;
        }

        public static void setCall(String call) {
            Tree.call = call;
        }

        public static String getReturned() {
            return returned;
        }

        public static void setReturned(String returned) {
            Tree.returned = returned;
        }

        public static List<Tree> getChildren() {
            return children;
        }

        public static void setChildren(List<Tree> children) {
            Tree.children = children;
        }

        public Tree() {
        }

    }

    public static void printTree(Tree tree, String indent) {
        int indentSize = 4;
        if (tree == null || tree.getChildren().size() == 0) {
            System.out.println(tree.getCall() + "returned" + tree.getReturned());
        } else {
            System.out.println(tree.getCall() + "returned" + tree.getReturned());
            for (int i = 0; i < tree.getChildren().size() - 1; i++) {
                System.out.print(indent + "|" + (repeatString("-", indentSize)));
                printTree(tree.getChildren().get(i), indent + "|" + repeatString(" ", indentSize));
            }
            Tree child = tree.getChildren().get(tree.getChildren().size() - 1);
            System.out.print(indent + "\\" + (repeatString("-", indentSize)));
            printTree(child, indent + repeatString(" ", indentSize));
        }
    }

    public static String repeatString(String toBeRepeated, int times) {

        StringBuilder repeatedString = new StringBuilder();
        for (int i = 0; i < times; i++) {
            repeatedString.append(toBeRepeated);
        }
        return repeatedString.toString();
    }

}
