package LevelOne;

import java.util.Arrays;
import java.util.Stack;

public class StackProblem {

    // reminder Stack is a class that represents a last-in, first-out (LIFO) stack of objects. It extends the Vector
    // class with five operations that allow a vector to be treated as a stack.

    public static void main(String[] args) {

        // the goal is for each day find number of days remaining for a warmer day
        // 0 1 2 3 4 5 6
        int[] temperatures = new int[] { 13, 12, 15, 11, 9, 12, 16 };
        // [ 2, 1, 4, 2, 1, 1, 0]

        System.out.println(Arrays.toString(countTemperatures(temperatures)));
        System.out.println(Arrays.toString(countTemperaturesStack(temperatures)));
        System.out.println(Arrays.toString(firstSolutionBis(temperatures)));
        System.out.println(Arrays.toString(stackSolutionBis(temperatures)));

    }

    // first solution a for cycle O(n²) the advantage is that we only create 2 vars i and j
    // if we need to considerate memory the complexity is O(n) | O(1)

    public static int[] countTemperatures(int[] t) {
        int[] answer = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            for (int j = i + 1; j < t.length; j++) {
                if (t[j] > t[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        // here we can notice that only values from the right part is took into account
        // it would be advantegeous to run over the array from the right to the left
        // to fill in the answers array and to collect the information about the completed path
        // this information about the passed path we would store it in a stack
        return answer;

    }

    // memory O(n) // time : everytime we look inside the stack we also delete an element if it does not satisfy the conditions the
    // time of algorythm is O(n) as well
    public static int[] countTemperaturesStack(int[] t) {
        Stack<C> stack = new Stack<>();
        int[] answer = new int[t.length];

        for (int i = t.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().value <= t[i]) { // peek permet de regarder la value de l'element
                                                                     // sans le retirer du stack
                stack.pop(); // and we suppress the last element from the stack
            }
            if (!stack.isEmpty()) {
                answer[i] = stack.peek().index - i;
            }
            stack.push(new C(t[i], i));
        }
        return answer;
    }
    
    
    public static int[] firstSolutionBis(int[] t) {
        int[] result = new int[t.length];
        result[t.length-1]=0;
        
        for(int i = 0; i<t.length;i++) {
            for (int j=i+1;j<t.length;j++) {
                if(t[j]>t[i]) {
                    result[i]=j-i;
                    break;
                }
            }
        }
        return result;
    }
    
    public static int[] stackSolutionBis(int[] t) {
        int[] result = new int[t.length];
        Stack<C> stack = new Stack<>();
        for(int i=t.length-1;i>=0;i--) {

            while(!stack.isEmpty()&&stack.peek().value<t[i]) {
                stack.pop();
            }
            if(!stack.isEmpty()) {
            result[i]=stack.peek().index - i;
            }
            stack.push(new C(t[i], i));
        }
        return result;
    }

}

class C {
    int value;
    int index;

    C(int value, int index) {
        this.value = value;
        this.index = index;
    }
}
