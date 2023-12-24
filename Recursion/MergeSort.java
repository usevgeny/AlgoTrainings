package Recursion;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

        int[] testArray = { 5, 1, 3, 4, 2 };
        System.out.println(Arrays.toString(mergeSort(testArray)));
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);
        left = mergeSort(left);
        right = mergeSort(right);

        System.out.println("Left: " + Arrays.toString(left));
        System.out.println("Right: " + Arrays.toString(right));
        return mergeSortedArr(left, right);

    }

    public static int[] mergeSortedArr(int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;

        int[] merged = new int[leftLength + rightLength];

        int leftPosition, rightPosition, mergedPosition;
        leftPosition = rightPosition = mergedPosition = 0;

        // filling in the merged array
        while (leftPosition < leftLength && rightPosition < rightLength) {
            if (left[leftPosition] < right[rightPosition]) {
                merged[mergedPosition++] = left[leftPosition++];
            } else {
                merged[mergedPosition++] = right[rightPosition++];
            }
        }
// adding the elements which remained if exist
        while (leftPosition < leftLength) {
            merged[mergedPosition++] = left[leftPosition++];
        }
// adding the elements which remained if exist

        while (rightPosition < rightLength) {
            merged[mergedPosition++] = right[rightPosition++];
        }

        return merged;
    }

}
