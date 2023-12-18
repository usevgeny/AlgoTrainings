package LevelOne;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FindTwoElems {

    public static int[] hundredArray = new int[100];
    public static int[] thousandArray = new int[1000];
    public static int[] millionArray = new int[1_000_000];
    public static int[] checkArray = new int[] {3,2,4};
    public static int checkK = 6;

    public static int[] fillArrayy(int[] arrayToFill, int maxRandomNumber) {
        Random ran = new Random();
        for (int i = 0; i < arrayToFill.length; i++) {
            arrayToFill[i] = ran.nextInt(maxRandomNumber);
        }
        return arrayToFill;
    };

    public static Long checkDuration(Long end, Long start) {
        return end - start;
    }

    // the first and the less efficient method using for cycle
    public static int[] summForCycle(int[] inputArray, int k) {
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray.length; j++) {
                if (inputArray[i] + inputArray[j] == k) {
                    return new int[] { inputArray[i], inputArray[j] };
                }
            }
        }
        return new int[0];
    }

    // the second method: faster but using extra memory
    public static int[] summHashSet(int[] inputArray, int k) {
        Set<Integer> checkedNumbers = new HashSet<>();
        for (int i = 0; i < inputArray.length; i++) {
            int numberToFind = k - inputArray[i];
            if (checkedNumbers.contains(numberToFind)) {
                return new int[] { numberToFind, inputArray[i] };
            } else {
                checkedNumbers.add(inputArray[i]);
            }
        }
        return new int[0];
    }

    // the third method: binary search
    public static int[] summBinarySearch(int[] inputArray, int k) {
        for (int i = 0; i < inputArray.length; i++) {
            int numberToFind = k - inputArray[i];
            int left = i+1;
            int right = inputArray.length - 1;
            while (left <= right) {
            int mid = left + (right - left) / 2;
                if (numberToFind == inputArray[mid]) {
                    return new int[] { inputArray[i], inputArray[mid] };
                }
                if (numberToFind > mid) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return new int[0];
    }
    // the third method: binary search
    public static int[] summJavaBinarySearch(int[] inputArray, int k) {
        for (int i = 0; i < inputArray.length; i++) {
            int numberToFind = k - inputArray[i];
            int index  = Arrays.binarySearch(inputArray, numberToFind);
            if(index >=0) {
                return new int[] {inputArray[i], inputArray[index]};
            }
        }
        return new int[0];
    }
    
    // the fourth and the most performant way to solve the task
    public static int[] summPointers(int[] inputArray, int k) {
        int left = 0;
        int right = inputArray.length-1;
        while (left<=right) {
            int leftValue = inputArray[left];
            int rightValue = inputArray[right];
            int summ = leftValue+rightValue;
            if (summ==k) {
                return new int[] {leftValue, rightValue};
            }
            if (summ<k) {
                left++;
            }else {
                right--;
            }
           }
        return new int[0];
    }

    public static void main(String[] args) {
        Arrays.sort(checkArray);
        Long start = System.currentTimeMillis();
        fillArrayy(hundredArray, 1000);
        Arrays.sort(hundredArray);
        fillArrayy(thousandArray, 1000);
        Arrays.sort(thousandArray);
        fillArrayy(millionArray, 1000);
        Arrays.sort(millionArray);
        int newNumberToFind = 1956;
        Long end = System.currentTimeMillis();
        System.out.println("cekcNumber : "+checkK);
        System.out.println("newNumber"+newNumberToFind);
        System.out.println("Arrays initialization took: " + checkDuration(end, start) + "millis");

        // running first method
//        System.out.println(Arrays.toString(summForCycle(checkArray, checkK)));
//        start = System.currentTimeMillis();
//        System.out.println(Arrays.toString(summForCycle(millionArray, newNumberToFind)));
//        end = System.currentTimeMillis();
//        System.out.println("Search with for cycle took" + checkDuration(end, start) + "millis");

        // running second method
        System.out.println(Arrays.toString(summHashSet(checkArray, checkK)));
        start = System.currentTimeMillis();
        System.out.println(Arrays.toString(summHashSet(millionArray, newNumberToFind)));
        end = System.currentTimeMillis();
        System.out.println("Search with hashSet method took" + checkDuration(end, start) + "millis");

        // running third method
        System.out.println(Arrays.toString(summBinarySearch(checkArray, checkK)));
        start = System.currentTimeMillis();
        System.out.println(Arrays.toString(summBinarySearch(millionArray, newNumberToFind)));
        end = System.currentTimeMillis();
        System.out.println("Search with binnary method took" + checkDuration(end, start) + "millis");
        
        // running  binnary java method
        System.out.println(Arrays.toString(summJavaBinarySearch(checkArray, checkK)));
        start = System.currentTimeMillis();
        System.out.println(Arrays.toString(summJavaBinarySearch(millionArray, newNumberToFind)));
        end = System.currentTimeMillis();
        System.out.println("Search with Java binnary method took" + checkDuration(end, start) + "millis");

        // running second method
        System.out.println(Arrays.toString(summPointers(checkArray, checkK)));
        start = System.currentTimeMillis();
        System.out.println(Arrays.toString(summPointers(millionArray, newNumberToFind)));
        end = System.currentTimeMillis();
        System.out.println("Search with pointers method took" + checkDuration(end, start) + "millis");
    }

}
