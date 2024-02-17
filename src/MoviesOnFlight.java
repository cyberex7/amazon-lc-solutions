/*
* Question:
You are on a flight and want to watch two movies during this flight.
You are given List<Integer> movieDurations which includes all the movie durations.
You are also given the duration of the flight which is d in minutes.
Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).

Find the pair of movies with the longest total duration and return they indexes. If multiple found, return the pair with the longest movie.

Example 1:

Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
Output: [0, 6]
Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215 is the maximum number within 220 (250min - 30min)
*
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MoviesOnFlight {
    public static int[] moviesOnFlight(int[] movieDurations, int d) {
        d -= 30; // Adjust flight duration to account for the 30-minute pre-flight period
        int[][] movies = new int[movieDurations.length][2];

        // Store original indices and durations
        for (int i = 0; i < movieDurations.length; i++) {
            movies[i][0] = movieDurations[i];
            movies[i][1] = i;
        }

        // Sort movies by duration
        Arrays.sort(movies, (a, b) -> a[0] - b[0]);

        int left = 0, right = movies.length - 1;
        int maxDuration = 0;
        int[] result = new int[]{-1, -1};

        while (left < right) {
            int currentSum = movies[left][0] + movies[right][0];
            if (currentSum > d) {
                // If sum exceeds limit, move right pointer to the left
                right--;
            } else {
                // If current pair has the longest duration so far, update result
                if (currentSum > maxDuration) {
                    maxDuration = currentSum;
                    result[0] = movies[left][1];
                    result[1] = movies[right][1];
                }
                // Move left pointer to the right to try finding a longer duration pair
                left++;
            }
        }

        // Sort the result by index if needed
        if (result[0] > result[1]) {
            int temp = result[0];
            result[0] = result[1];
            result[1] = temp;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] movieDurations = {90, 85, 75, 60, 120, 150, 125};
        int d = 250;
        int[] result = moviesOnFlight(movieDurations, d);
        System.out.println("[" + result[0] + ", " + result[1] + "]");

        int[] result2 =moviesOnFlight(new int[]{90, 85, 75, 60, 120, 150, 125}, 50);

        int[] result3 =moviesOnFlight(new int[]{90, 85, 75, 60, 120, 150, 125}, 220);

        int[] result4 =moviesOnFlight(new int[]{10, 50, 60}, 120);

        int[] result5 =moviesOnFlight(new int[]{90, 85, 75, 60, 120, 110, 110, 150, 125}, 250);

        System.out.println("[" + result2[0] + ", " + result2[1] + "]");
        System.out.println("[" + result3[0] + ", " + result3[1] + "]");
        System.out.println("[" + result4[0] + ", " + result4[1] + "]");
        System.out.println("[" + result5[0] + ", " + result5[1] + "]");
    }
}
