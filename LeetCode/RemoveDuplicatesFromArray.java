package LeetCode;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicatesFromArray {
//    Given an integer array nums sorted in increasing order, 
//    remove the duplicates inplace while maintaining the order.
//    When done if there were k unique elements, then the first 
//    k elements of nums should contain the unique elements in sorted order. Finally return k

    public static void main(String[] args) {
        int[] inputArray = new int[] { 1, 1, 2 };
        System.out.println(solution(inputArray));
        System.out.println(Arrays.toString(inputArray));
        System.out.println();
        System.out.println(optimizedSolution(inputArray));
        System.out.println(Arrays.toString(inputArray));
    }

    public static int solution(int[] nums) {
        Set<Integer> set = new LinkedHashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int index = 0;
        for (int num : set) {
            nums[index++] = num;
        }

        return set.size();
    }
    
    public static int optimizedSolution(int[] nums) {
        int replacementIndex = 1; //which element we need to replace
        for(int i =1;i<nums.length;i++) {
            if(nums[i-1]!=nums[i]) {
                nums[replacementIndex] = nums[i];
            replacementIndex++;
            }
        }
       return replacementIndex; 
    }
}
