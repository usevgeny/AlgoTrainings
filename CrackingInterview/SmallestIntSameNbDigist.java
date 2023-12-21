package CrackingInterview;

public class SmallestIntSameNbDigist {

    public static void main(String[] args) {
        System.out.println(smallestInt(15585));
    }

    public static int smallestInt(int N) {
        int smallestNumber = 0;
        int digitsLengt = numberOfDifits(N);
        if (N <= 1 || digitsLengt == 1) {
            return smallestNumber;
        }

        return (int) Math.pow(10, numberOfDifits(N) - 1);
    }

    public static int numberOfDifits(int input) {
//        return String.valueOf(input).length();
        int count = 0;
        while (input != 0) {
            input /= 10;
            count++;
        }
        return count;
    }
}
