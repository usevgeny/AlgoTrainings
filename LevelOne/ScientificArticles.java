package LevelOne;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ScientificArticles {

    public static void main(String[] args) {

        // array containing an index of citations for each article
        // index -> is an article, value -> number of citations
        // the goal is to find index H de ce sciantifique -- h articles avec h number de citations
        // si H index == 1 cela voudrait dire que l'on a au moins un article avec une citation
        // si H == 2 on doit avoir 2 articles avec 2 citations; 3- avec 3 citations ou plus etc
        // le but est de trover max H

        int[] articlesCitations = new int[] { 3, 0, 6, 1, 5 };
        System.out.println(firstSolution(articlesCitations));
        System.out.println(sortedSolution(articlesCitations));
        Arrays.sort(articlesCitations);
        System.out.println(calculateHIndex(articlesCitations));

    }

    // inseerd cycle o(n²)
    public static Integer firstSolution(int[] articlesArray) {
        int n = articlesArray.length; // Get the number of articles

        // Iterate over potential hIndex values from 1 to n
        for (int hIndex = 1; hIndex <= n; hIndex++) {
            int count = 0;

            // Iterate over the articles to count how many have citations greater than or equal to the current hIndex
            for (int i = 0; i < n; i++) {
                if (articlesArray[i] >= hIndex) {
                    count++;
                }
            }

            // If the count is less than the current hIndex, return hIndex - 1
            if (count < hIndex) {
                return hIndex - 1;
            }
        }

        // If no condition is met, return n
        return n;
    }

    // every step incrementing hIndex we move from the biggest (from the right) to the smallest (beginning)

    // O(n log n)
    public static Integer sortedSolution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length, hIndex = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] < hIndex) {
                return hIndex - 1;
            }
            hIndex++;
        }
        return n;
    }

    public static int hindexCountedSorting(int[] citations) {
        ////// THE FIRST STEP //////

        // first step on count the quantity of each value
        int n = citations.length;
        int[] count = new int[n + 1]; // because we should also include 0;
        for (int citation : citations) {
            if (citation >= n) {
                count[n]++; // if the citation is greater then the total number of articles, we just need to increment
                            // the largest citation value which would be equal to the length of the array (hIndex can
                            // not be larger than the total number of the articles)
            } else {
                count[citation]++; // if citation is less than the total number of articles we increment the counter for
                                   // that citation value.in this case citation represents an index in counter array
            }
        }

        ////// THE SECOND STEP //////

        // the second step would be to sort our original array using our counter array [3,0,1,7,1,6] becomes [0 1 1 3 6
        // 6] (the lest 6 refesrs to 6+ -> 7 in this case, because we dont care about the numbers that a greater than
        // the length of our initial array.

        int pos = 0;
        for (int i = 0; i <= n; i++) { // [3,0,1,7,1,6]
            for (int j = 0; j < count[i]; j++) { // [1,2,0,1,0,0,2] // attention here we compare an element from the
                                                 // counter with the citation value
                citations[pos] = i; // [0, 1,1, 3,6,6 ]
                pos++;
            }
        }

        ////// THE THIRD STEP //////

        int hIndex = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] < hIndex) {
                return hIndex - 1;
            }
            hIndex++;
        }

        return n;
    }

    public static int findHindexOptimal(int[] citations) {
        /////// First Step ///////
        // first step create a counter array to count each element occurencies and to reduce a number of elements to
        // check to the max possible value which is n-> number of articles
        int n = citations.length;
        int[] counter = new int[n + 1];

        for (int citation : citations) {
            if (citation >= n) {
                counter[n]++;
            } else {
                counter[citation]++;
            }
        }
        /////// Second Step ///////
        // reconstruct the initial array using the values from the counter array
        int pos = 0; // to keep track of the current position for the initial array
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= counter[i]; j++) {
                citations[pos] = i;
                pos++;
            }
        }

        /////// Third Step ///////
        // iterate over the newly sorted array from the end to the begining and incrementing hIndex on every step
        int hIndex = 1;
        for (int i = n-1;i>=0;i--) {
            if (hIndex>citations[i]) {
                return hIndex-1;
            }
            hIndex++;
        }
        return n;
    }
    
    
    // in case we already have a sorted array 
    public static int calculateHIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int hIndex = n - mid;

            if (citations[mid] == hIndex) {
                return hIndex;
            } else if (citations[mid] < hIndex) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return n - left;  // If no H-index is found
    }

}
