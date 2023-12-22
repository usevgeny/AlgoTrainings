package CrackingInterview;

public class CheckPrimeNumber {
    public static void main(String[] args) {
        System.out.println(isNumberPrime(73));
        System.out.println(isNumberPrime(4));
    }

    public static boolean isNumberPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        //we only need to check a half, because if n is divisible by any even number, then it must also be divisible by 2. 
        //Therefore, we can skip checking even numbers altogether. This saves us some time and makes the code more efficient.
        for (int div = (n / 2) + 1; div > 1; div--) { 
            if (n % div == 0) {
                return false;
            }
        }
        return true;
    }

}
