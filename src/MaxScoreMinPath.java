/*
* Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1]. The score of a path is the minimum value in that path. For example, the score of the path 8 → 4 → 5 → 9 is 4.

Don't include the first or final entry. You can only move either down or right at any point in time.

Example 1:

Input:
[[5, 1],
 [4, 5]]

Output: 4
Explanation:
Possible paths:
5 → 1 → 5 => min value is 1
5 → 4 → 5 => min value is 4
Return the max value among minimum values => max(4, 1) = 4.
Example 2:

Input:
[[1, 2, 3]
 [4, 5, 1]]

Output: 4
Explanation:
Possible paths:
1-> 2 -> 3 -> 1
1-> 2 -> 5 -> 1
1-> 4 -> 5 -> 1
So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
Return the max of that, so 4.
*
* */

public class MaxScoreMinPath {
    public int findMaximumScore(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0) {
            return 0; // Return 0 or appropriate error value
        }

        int N = nums.length;
        int M = nums[0].length;

        // Set the starting and ending points to a high value
        nums[0][0] = Integer.MAX_VALUE;
        nums[N - 1][M - 1] = Integer.MAX_VALUE;

        int[][] dp = new int[N][M];
        // Initialize the dp array with high values
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // Initialize the first row and column of dp table
        for (int j = 1; j < M; j++) {
            dp[0][j] = Math.min(dp[0][j - 1], nums[0][j]);
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], nums[i][0]);
        }

        // Fill the rest of the dp table
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int cur = Math.max(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = Math.min(cur, nums[i][j]);
            }
        }

        // The answer is stored in the bottom-right cell
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        MaxScoreMinPath solution = new MaxScoreMinPath();

        // Test with the first example
        int[][] nums1 = {{5, 1}, {4, 5}};
        System.out.println("Maximum score of the path (Example 1): " + solution.findMaximumScore(nums1));

        // Test with the second example
        int[][] nums2 = {{1, 2, 3}, {4, 5, 1}};
        System.out.println("Maximum score of the path (Example 2): " + solution.findMaximumScore(nums2));
    }
}
