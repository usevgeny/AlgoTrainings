package LevelOne;

import java.util.Arrays;

public class ScientificArticlesRepeat {

    // array containing an index of citations for each article
    // index -> is an article, value -> number of citations
    // the goal is to find index H de ce sciantifique -- h articles avec h number de citations
    // si H index == 1 cela voudrait dire que l'on a au moins un article avec une citation
    // si H == 2 on doit avoir 2 articles avec 2 citations; 3- avec 3 citations ou plus etc
    // le but est de trover max H
    public static void main(String[] args) {

        int[] articlesCitations = new int[] { 3, 0, 6, 1, 5 };
        int[] articlesCitations2 = new int[] { 3, 0, 1, 7, 1, 6 };
        System.out.println(firstSolution(articlesCitations));
        System.out.println(sortedSolution(articlesCitations));
        System.out.println(optimalSolution(articlesCitations));
        System.out.println(optimalSolution(articlesCitations2));
//    Arrays.sort(articlesCitations);
//    System.out.println(calculateHIndex(articlesCitations));

    }

    public static int firstSolution(int[] articles) {
        for (int hIndex = 1; hIndex <= articles.length; hIndex++) {
            int count = 0;
            for (int art : articles) {
                if (hIndex <= art) {
                    count++;
                }
            }
            if (hIndex > count) {
                return hIndex - 1;
            }
        }
        return articles.length;
    };

    public static int sortedSolution(int[] citations) {
        Arrays.sort(citations);
        int hIndex = 1;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] < hIndex) {
                return hIndex - 1;
            }
            hIndex++;
        }
        return citations.length;
    }

    public static int optimalSolution(int[] citations) {
        int hindex = 1;
        int n = citations.length;
        int[] counter = new int[citations.length + 1];
        // 1. sort the citations array the way to keep only numbers of citations between 0 and n; all citaitons n+ will
        // be added to n
        for (int citation : citations) {
            if (citation >= n) {
                counter[n]++;
            } else {
                counter[citation]++;
            }
        }
        System.out.println(Arrays.toString(counter));

        // reconstruct the original citations array taking into account the counter

//        int pos=0; // to keep track of the current posision while iterating
//        for(int i = 0; i<=n;i++) { // equals to n because we need to go through all the values of counter whith lenth is n+1
//            for(int j=0;j<counter[i];j++) {
//                citations[pos]=i;
//                pos++;
//            }
//        }

        int pos = 0;
        for (int i = 0; i <= counter.length - 1; i++) {
            for (int j = 0; j < counter[i]; j++) {
                citations[pos] = i;
                pos++;
            }
        }

        System.out.println(Arrays.toString(citations));

        for (int i = n - 1; i >= 0; i--) {
            if (hindex > citations[i]) {
                return hindex - 1;
            }
            hindex++;
        }

        return hindex;

    }

}
