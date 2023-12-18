package LeetCode;


public class MatrixRotation {

    public static void main(String[] args) {

        int[][] matrix = { { 1, 4, 7, 11, 15 },
                { 2, 5, 8, 12, 19 },
                { 3, 6, 9, 16, 22 },
                { 10, 13, 14, 17, 27 },
                { 18, 21, 23, 26, 36 },
        };

        printMatrixAsTable(matrix);
//        rotateClockwise(matrix);
        rotate(matrix);
        System.out.println();
        printMatrixAsTable(matrix);
    }

    public static void rotateClockwise(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1)
            return;

        /* layers */
        for (int i = 0; i < n / 2; i++) {
            /* elements */
            for (int j = i; j < n - i - 1; j++) {
                // Swap elements in clockwise direction
                // temp = top-left
                int temp = matrix[i][j];
                // top-left <- bottom-left
                matrix[i][j] = matrix[n - 1 - j][i];
                // bottom-left <- bottom-right
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                // bottom-right <- top-right
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                // top-right <- top-left
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public static int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] result = new int[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;

    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n<=1) {
            return;
        }
        for (int i =0; i<n/2;i++) {
            for (int j=0;j<n-i-1;j++) {
                int temp = matrix[i][j];
                // top-left <- bottom-left
                matrix[i][j] = matrix[n - 1 - j][i];
                // bottom-left <- bottom-right
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                // bottom-right <- top-right
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                // top-right <- top-left
                matrix[j][n - 1 - i] = temp;
            }
        }

    }

    public static void printMatrixAsTable(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

}