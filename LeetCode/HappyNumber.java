package LeetCode;

import java.util.HashSet;
import java.util.Set;


//Write an algorithm to determine if a number n is happy.
//
//A happy number is a number defined by the following process:
//
//Starting with any positive integer, replace the number by the sum of the squares of its digits.
//Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
//Those numbers for which this process ends in 1 are happy.
//Return true if n is a happy number, and false if not.
//
// 
//
//Example 1:
//
//Input: n = 19
//Output: true
//Explanation:
//12 + 92 = 82
//82 + 22 = 68
//62 + 82 = 100
//12 + 02 + 02 = 1

public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappyNumber(19));
        System.out.println(isHappy(19));
    }

    // one of the solutions would be to use Turtuoise algorytm
    public static boolean isHappyNumber(int n) {
//        int length = (int) (Math.log10(n) + 1);

        Set<Integer> visited = new HashSet<>();

        // if n != 1 and n in visited -> the loop has been detected
        while (n != 1 && !visited.contains(n)) {
            visited.add(n);

            int sum = 0;
            while (n != 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }

            n = sum;
        }
        return n == 1;

    }

    // Turtoise

    public static boolean isHappy(int n) {

        int slow = n;
        int fast = n;
//while loop is not used here because initially slow and 
//fast pointer will be equal only, so the loop won't run.
        do {
//slow moving one step ahead and fast moving two steps ahead

            slow = square(slow);
            fast = square(square(fast));
        } while (slow != fast);

//if a cycle exists, then the number is not a happy number
//and slow will have a value other than 1

        return slow == 1;
    }

//Finding the square of the digits of a number

    public static int square(int num) {

        int ans = 0;

        while (num > 0) {
            int remainder = num % 10;
            ans += remainder * remainder;
            num /= 10;
        }

        return ans;
    }

}
