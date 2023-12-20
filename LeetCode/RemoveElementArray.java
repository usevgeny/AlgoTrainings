package LeetCode;

import java.util.Arrays;

public class RemoveElementArray {

    public static void main(String[] args) {
        int[] arr = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int k = 2;
        removeElement(arr, k);
        int[] arr1 = { 1 };
        int k1 = 1;
        removeElement(arr1, k1);
        int[] arr2 = { 3, 3 };
        int k2 = 5;
        removeElement(arr2, k2);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
