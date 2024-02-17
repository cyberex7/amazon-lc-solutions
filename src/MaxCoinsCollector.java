/*
* The first traversal starts from the top-left corner of the matrix and ends in the bottom-left corner, and the second traversal starts from the top-right corner and ends in the bottom-right corner. From any cell (i, j) in the matrix, we are allowed to move to cell (i+1, j+1) or (i+1, j-1) or (i+1, j). If both traversals pass through the same cell, only one can collect coins from that cell.

Input:  The given matrix is

[ 0 2 4 1 ]
[ 4 8 3 7 ]
[ 2 3 6 2 ]
[ 9 7 8 3 ]
[ 1 5 9 4 ]

Output: The maximum coins collected is 47.
* */

public class MaxCoinsCollector {

    // Function to check whether (i, x) and (i, y) are valid matrix coordinates
    private static boolean isValid(int i, int x, int y, int M, int N) {
        return i < M && x >= 0 && x < N && y >= 0 && y < N;
    }

    // Collect maximum coins from cell (i, x) to cell (M-1, 0) and from
    // cell (i, y) to cell (M-1, N-1)
    private static int getMaxCoins(int[][] mat, int i, int x, int y) {
        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;

        // Return if either (i, x) or (i, y) is invalid
        if (!isValid(i, x, y, M, N)) {
            return Integer.MIN_VALUE;
        }

        // Current row is the last row
        if (i == M - 1) {
            // Destination reached
            if (x == 0 && y == N - 1) {
                return (x == y) ? mat[i][x] : mat[i][x] + mat[i][y];
            }

            // Destination not reached
            return Integer.MIN_VALUE;
        }

        // Stores the max number of coins
        int coins = Integer.MIN_VALUE;

        // Recur for all possible ways:
        coins = Math.max(coins, getMaxCoins(mat, i + 1, x - 1, y - 1));
        coins = Math.max(coins, getMaxCoins(mat, i + 1, x - 1, y));
        coins = Math.max(coins, getMaxCoins(mat, i + 1, x - 1, y + 1));

        coins = Math.max(coins, getMaxCoins(mat, i + 1, x, y - 1));
        coins = Math.max(coins, getMaxCoins(mat, i + 1, x, y));
        coins = Math.max(coins, getMaxCoins(mat, i + 1, x, y + 1));

        coins = Math.max(coins, getMaxCoins(mat, i + 1, x + 1, y - 1));
        coins = Math.max(coins, getMaxCoins(mat, i + 1, x + 1, y));
        coins = Math.max(coins, getMaxCoins(mat, i + 1, x + 1, y + 1));

        // Update max number of coins with current cell coins before returning
        if (x == y) {
            return mat[i][x] + coins;
        } else {
            return mat[i][x] + mat[i][y] + coins;
        }
    }

    public static int getMaxCoins(int[][] mat) {
        // Base case
        if (mat == null || mat.length == 0) {
            return 0;
        }

        // `M × N` matrix
        int N = mat[0].length;

        // Start with cell (0, 0) and (0, N-1)
        return getMaxCoins(mat, 0, 0, N - 1);
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 2, 4, 1},
                {4, 8, 3, 7},
                {2, 3, 6, 2},
                {9, 7, 8, 3},
                {1, 5, 9, 4}
        };

        System.out.println("The maximum coins collected is " + getMaxCoins(mat));
    }
}

