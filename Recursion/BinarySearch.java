package Recursion;

public class BinarySearch {

    public static void main(String[] args) {
        int[] inputArray = new int[] { 1, 3, 5, 9, 1, 6 };
        int k = 5;

        System.out.println(findKRec(inputArray, k));
    }

    public static int rec(int[] arr, int k, int left, int right) {
        int mid = (right - left) / 2;
        if (left > right) {
            return -1;
        } else if (arr[mid] == k) {
            return mid;
        } else if (arr[mid] < k ) {
            return rec(arr, k, mid++, right);
        } else {
            return rec(arr, k, left, mid--);
        }
    }

    public static int findKRec(int[] arr, int k) {
        int left = 0;
        int right = arr.length-1;
        return rec(arr, k, left, right);
    }
}
