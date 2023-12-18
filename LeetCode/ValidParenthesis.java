package LeetCode;

import java.util.Stack;

public class ValidParenthesis {

    public static void main(String[] args) {
        String s = "()";
        String s1 = "()[]{}";
        String s2 = "(]";

        System.out.println(isValid(s));
        System.out.println(isParenthesisValid(s));
        System.out.println();
        System.out.println(isValid(s1));
        System.out.println(isParenthesisValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isParenthesisValid(s2));
        System.out.println();
        System.out.println(isValidString(s2));
        System.out.println(isValidString(s1));
        System.out.println(isValidString(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            // if the character is a closing paranthesis -> the stack should contain the top element equal to the
            // closing element of the same type
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    public static boolean isParenthesisValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '(') {
                stack.push(')');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();

    }
    
    public static boolean isValidString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++) {
           char c = s.charAt(i);
           if(c=='(') {stack.push(')');}
           else if(c=='[') {stack.push(']');}
           else if(c=='{') {stack.push('}');}
           else if(!stack.isEmpty() && c != stack.pop()) {
               return false;
           }
        }
        return stack.isEmpty();
    }

}
