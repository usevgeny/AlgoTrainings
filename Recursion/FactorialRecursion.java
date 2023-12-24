package Recursion;

public class FactorialRecursion {
    
    public static void main(String[] args) {
        System.out.println(findFactorial(0));
    }
    
    public static int findFactorial(int n) {
        if(n <= 1 || n ==0 ) {
            return 1;
        }
        return n*findFactorial(n-1);
    }

}
