package LevelOne;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindSubsForK {

    public static void main(String[] args) {

        int[] testArray = new int[] {4,2,2,1,2,-3,5,-8};
        int[] testArray2 = new int[] {4,2,2,1,2,-3,5,-8, 10,15,3, 9,-14,5};
        int k = 5;
        int k2= 2;
        System.out.println(subarraySum(testArray, k));
        System.out.println(subArraySummFirstBis(testArray, k));
        System.out.println(subarrayHashMapBis(testArray, k));
        System.out.println(subarraySumBis(testArray, k));
        System.out.println(subarraySumOptimal(testArray, k));
        System.out.println(findNumberSubsums(testArray, k));
        System.out.println(findNumberSubsums(testArray2, k2));
        System.out.println(subarraySumOptimal(testArray2, k2));
    }

    // first method O(n³)
    // find number of subarrays where their sum gives k
    public static int subarraySum(int[] arr, int k) {
        int answer = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                int subArraySumm = 0;
                for (int i = start; i <= end; i++) {
                    subArraySumm += arr[i];
                }
                if (subArraySumm == k) {
                    answer++;
                }
            }
        }
        return answer;
    }

    // second méthod

    public static int subarraySumBis(int[] arr, int k) {
        int answer = 0;
        for (int start = 0; start < arr.length; start++) {
            int subArraySumm = 0;
            for (int i = start; i < arr.length; i++) {
                subArraySumm += arr[i];
                if (subArraySumm == k) {
                    answer++;
                }
            }
        }

        return answer;
    }

    // third méthod O(n)

    public static int subarraySumOptimal(int[] arr, int k) {
        int answer = 0;
        int subArraySumm = 0;
        Map<Integer, Integer> prefixSummCount = new HashMap<>() {
            {
                put(0, 1);
            }
        };

        for (int i = 0; i < arr.length; i++) {
            subArraySumm += arr[i];
            int toRemove = subArraySumm - k;
            answer += prefixSummCount.get(toRemove) != null ? prefixSummCount.get(toRemove) : 0;
            int previous = prefixSummCount.get(subArraySumm) != null ? prefixSummCount.get(subArraySumm) : 0;
            prefixSummCount.put(subArraySumm, previous + 1);
        }
        return answer;

    }

    public static int subArraySummFirstBis(int[] array, int k) {
        int count = 0;
        for (int start = 0; start < array.length; start++) {
            for (int end = start; end < array.length; end++) {
                int subSumm =0;
                for (int i = start; i <= end; i++) {
                    subSumm = subSumm + array[i];
                }
                if (subSumm == k) {
                    count++;
                }

            }
        }
        return count;
    }

    public static int subarrayHashMapBis(int[] array, int k) {
        int count =0;
        int subarraySumm =0;
        Map<Integer, Integer> subSums = new HashMap<>();
        subSums.put(0, 1);
        for(int i=0;i<array.length;i++) {
            subarraySumm += array[i];
            int toDelete = subarraySumm-k;
            count += (subSums.get(toDelete) != null? subSums.get(toDelete):0);
            int previousCount= (subSums.get(subarraySumm) != null ?subSums.get(subarraySumm):0);
            subSums.put(subarraySumm, previousCount+1);
            
        }
        return count;

    }
    
    public static int findNumberSubsums(int[] input, int k) {
        int result = 0;
        int subsumm = 0;
        Map<Integer, Integer> foundSumms = new HashMap<>();
        foundSumms.put(0,1);
        for (int i = 0; i< input.length; i++) {
            subsumm += input[i];
            int toDeletoFromSubSumm = subsumm -k;
//            int alreadyPresent = Optional.ofNullable(foundSumms.get(toDeletoFromSubSumm)).orElse(0);
            int alreadyPresent = foundSumms.getOrDefault(toDeletoFromSubSumm,0);
            result+=alreadyPresent;
            //adding a new value to the hashMap of subsumms
            int previousSummNumber = Optional.ofNullable(foundSumms.get(subsumm)).orElse(0);
            foundSumms.put(subsumm, previousSummNumber+1);
        }

        return result;
    }

}
