package LevelOne;

import java.util.Arrays;
import java.util.Random;

public class FindNumberMatrix {

    // generate a dataset

    public static int[][] generateData(int n, int m, int x, int k) {
        int[][] matrix = new int[n][m];
        Random random = new Random();
        boolean kInserted = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int numberToFill = random.nextInt(x + 1);
                matrix[i][j] = (numberToFill == k) ? numberToFill + 1 : numberToFill; // Random integer in the range [0,

                // Randomly insert 'k'
                if (!kInserted && random.nextBoolean()) {
                    matrix[i][j] = k;
                    kInserted = true; // Set the flag to true after inserting 'k'
                }
            }
            Arrays.sort(matrix[i]); // Sort each row
        }

        // Sort each column
        for (int j = 0; j < m; j++) {
            int[] column = new int[n];
            for (int i = 0; i < n; i++) {
                column[i] = matrix[i][j];
            }
            Arrays.sort(column);
            for (int i = 0; i < n; i++) {
                matrix[i][j] = column[i];
            }
        }

        // Print the matrix as a table
        printMatrixAsTable(matrix);

        return matrix;
    }

    public static void printMatrixAsTable(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // find if the martix contains k

    public static boolean findNumberForCycle(int[][] matrix, int k) {
        Long start = System.currentTimeMillis();
        for (int[] raw : matrix) {
            for (int i : raw) {
                if (i == k) {
                    Long end = System.currentTimeMillis();
                    System.out.println("Time to find cycle start node: " + (end - start) + "millis");
                    return true;
                }
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("Time to find cycle start node: " + (end - start) + "millis");
        return false;
        // O(m*n)
    }

    public static boolean findNumberBinary(int[][] matrix, int k) {
        Long start = System.currentTimeMillis();
        for (int[] raw : matrix) {
            if (raw.length > 0 && (raw[raw.length - 1] < k)) {
                continue;
            }
            int left = 0;
            int right = raw.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (raw[mid] == k) {
                    Long end = System.currentTimeMillis();
                    System.out.println("Time to find  k using binary search: " + (end - start) + "millis");
                    return true;
                }
                if (raw[mid] < k) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // O(m Log n)

        }

        Long end = System.currentTimeMillis();
        System.out.println("Time to find k using binary search: " + (end - start) + "millis");
        return false;
    }

    public static boolean findNumberDiagSearch(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        Long start = System.currentTimeMillis();
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0, j = n - 1;
        // j to move horizontally from the right (end of the array) to the left (beginning)
        // i we need to move vertically

        while (i < m && j > 0) {
            if (matrix[i][j] == k) {
        Long end = System.currentTimeMillis();
        System.out.println("Time to find K using diagonal search: " + (end - start) + "millis");
                return true;
            }
            if (matrix[i][j] > k) {
                j--;
            } else {
                i++;
            }

        }

        Long end = System.currentTimeMillis();
        System.out.println("Time to find K using diagonal search: " + (end - start) + "millis");
        // O(m+n)
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 4, 7, 11, 15, 16 },
                { 2, 5, 8, 12, 19, 22 },
                { 3, 6, 9, 16, 22, 24 },
                { 10, 13, 14, 17, 24, 27 },
                { 18, 21, 23, 26, 30, 36 },
        };
        int[][] matrix100 = generateData(1000, 1000, 100, 9395);

        int k = 14;
//        printMatrixAsTable(matrix);
        System.out.println();
        System.out.println();
        boolean result = findNumberForCycle(matrix, k);
        System.out.println(result);
        boolean result2 = findNumberBinary(matrix, k);
        System.out.println(result2);
        boolean result3 = findNumberBinary(matrix100, 9395);
        System.out.println(result3);
        boolean result4 = findNumberDiagSearch(matrix100, 9395);
        System.out.println(result4);

        System.out.println("comparing to the old style:");
        boolean result5 = findNumberForCycle(matrix100, 9395);
        System.out.println(result5);

    }
}
