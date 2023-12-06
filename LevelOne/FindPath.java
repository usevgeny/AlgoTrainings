package LevelOne;

public class FindPath {

    public static void main(String[] args) {

    }

    // solution would be to use a recursive function
    public static int paths(int x, int y) {
        if (x < 1 || y < 1) {
            return 0;
        }
        if (x == 1 && y == 1) {
            return 1;
        }
        return paths(x - 1, y) + paths(x, y - 1);
    }

    // the problem is complexity: O(2^(n+m)) some visited values will be colulated several times
    // in order to optimize this silution we could stock all the values that has already been seen
    // in an array
    
    public static int optimizedPaths(int x, int y) {
        return helper(x, y, new int[x+1][y+1]);
    }
    
    public static int helper(int x, int y, int[][] arr) {
        // we create the recursion base here
        if (x < 1 || y < 1) {
            return 0;
        }
        if (x == 1 && y == 1) {
            return 1;
        }
        if (arr[x][y] > 0) {
            return arr[x][y];
        }
        arr[x][y] = helper(x - 1, y, arr) + helper(x, y - 1, arr);
        return arr[x][y];
    }

}
