package LeetCode;

import java.lang.System.Logger;
import java.util.Arrays;

public class LongestPrefix {
    public static void main(String[] args) {
        String[] strs1 = new String[] { "flower", "flow", "flight" };
        String[] strs2 = new String[] { "dog", "racecar", "car" };
        String[] strs3 = new String[] { "ab", "a" };
        String[] strs4 = new String[] {"a","a","aabc","aa","acc"};
        

        System.out.println(longestCommonPrefix(strs1));
        System.out.println(longestCommonPrefix3(strs4));
    }

    public static String longestCommonPrefix(String[] strs) {

        if (strs.length < 1 || strs.length == 200) {
            return "";
        }
        char[] firstWord = strs[0].toCharArray();
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].matches("[a-z]+")) {
                return "";
            }
            char[] word = strs[i].toCharArray();
            int j = 0;
            if (firstWord.length > word.length) {
                firstWord = cutArray(firstWord, word.length);
            }

            while (j < firstWord.length && j < word.length) {
                if (firstWord[j] != word[j]) {
                    firstWord = cutArray(firstWord, j);
                }
                j++;
            }

        }
        return new String(firstWord);

    }

    public static char[] cutArray(char[] arrayToCut, int index) {
        char[] newArr = new char[index];
        System.arraycopy(arrayToCut, 0, newArr, 0, index);
        return newArr;

    }

    public static String longestCommonPrefix2(String[] v) {
        StringBuilder ans = new StringBuilder();
        Arrays.sort(v);
        System.out.println("Sorted array: " + Arrays.toString(v));
        String first = v[0];
        String last = v[v.length - 1];
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }

    public static String longestCommonPrefix3(String[] strs) {
        String smallestWord = strs[0];
        int left = 0;
        int right = strs.length - 1;
        while (left <= right) {
            if (strs[left].length() > strs[right].length() ) {
                if (smallestWord.length() > strs[right].length()) {
                    smallestWord = strs[right];
                }
            } else {
                if (smallestWord.length() > strs[left].length()) {
                    smallestWord = strs[left];
                }
            }
            left ++;
            right --;
        }
        System.out.println("Smalles word = "+ smallestWord);

        StringBuilder prefix = new StringBuilder();
        boolean isFinished = false;
        for (int i = 0; i < smallestWord.length(); i++) {
            for (String s : strs) {
                if (smallestWord.charAt(i) != s.charAt(i)) {
                    isFinished = true;
                    break;
                }
            }
            if (isFinished)
                break;
            prefix.append(smallestWord.charAt(i));
        }
        return prefix.toString();
    }
}
