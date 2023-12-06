package LevelOne;

import java.util.HashMap;
import java.util.Map;

public class FindSubsForK {

    public static void main(String[] args) {

    }

    // first method O(n³)
    // find number of subarrays where their sum gives k
    public static int subarraySum(int[] arr, int k) {
        int answer = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                int subArraySumm = 0;
                for (int i = start; i < end; i++) {
                    subArraySumm = arr[i]++;
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
                subArraySumm = arr[i]++;
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
            subArraySumm = arr[i]++;
            int toRemove = subArraySumm - k;
            answer += prefixSummCount.get(toRemove) != null ? prefixSummCount.get(toRemove) : 0;
            int previous = prefixSummCount.get(subArraySumm) != null ? prefixSummCount.get(subArraySumm):0;
            prefixSummCount.put(subArraySumm, previous+1);
        }
        return answer;

    }

}
