package LevelOne;

import java.util.Arrays;
import java.util.Stack;

public class StackProblemRepeat {
    // reminder Stack is a class that represents a last-in, first-out (LIFO) stack of objects. It extends the Vector
    // class with five operations that allow a vector to be treated as a stack.

    public static void main(String[] args) {

        // the goal is for each day find number of days remaining for a warmer day
        // 0 1 2 3 4 5 6
        int[] temperatures = new int[] { 13, 12, 15, 11, 9, 12, 16 };
        // [ 2, 1, 4, 2, 1, 1, 0]

        System.out.println(Arrays.toString(countTemperatures(temperatures)));
        System.out.println(Arrays.toString(countTemperaturesStack(temperatures)));
//        System.out.println(Arrays.toString(firstSolutionBis(temperatures)));
//        System.out.println(Arrays.toString(stackSolutionBis(temperatures)));

    }
    // for loop (working but resources consuming)
    public static int[] countTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        
        for(int i=0; i<temperatures.length;i++) {
            for(int j=i+1;j<temperatures.length;j++) {
                if(temperatures[j]>temperatures[i]) {
                    result[i]= j-i;
                    break;
                }
            }
        }
        result[temperatures.length-1]=0;
        return result;
    }
    
    public static int[] countTemperaturesStack(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<WarmDay> stack = new Stack<>();
        for(int i = n-1;i>=0;i--) {
            while(!stack.isEmpty()&&stack.peek().getTemp()<temperatures[i]) {
                stack.pop();
            }
        if(!stack.isEmpty()) {
            result[i]=stack.peek().index-i;
        }
        
        stack.push(new WarmDay(temperatures[i], i));
        
        }
        return result;
    }
    
    public static class WarmDay {
        private int temp;
        private int index;
        public int getTemp() {
            return temp;
        }
        public void setTemp(int temp) {
            this.temp = temp;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public WarmDay(int temp, int index) {
            super();
            this.temp = temp;
            this.index = index;
        }
        
    }

}
