/*
* Amazon Prime Video has movies in category 'comedy' or 'drama'. Determine the earliest time you can finish at least one movie from each category. The release schedule and duration of the movies are provided.
You can start watching a movie at the time it is release or later.
If you begin a movie at time t, it ends at t + duration.
If a movie ends at time t + duration , the second movie can start at that time, t+ duration, or later.
The movies can be watched in any order.
Complete the function minimumTimeSpent which has the following parameters:

int comedyReleaseTime[n]: release times
int comedyDuration[n]: durations
int dramaReleaseTime[m]: release times
int dramaDuration[m]: durations
Example 1:

Input: comedyReleaseTime = [1, 4], comedyDuration = [3, 2], dramaReleaseTime = [5, 2], dramaDuration = [2, 2]
Output: 6
Explanation:

Duration and release time are aligned by index.

Two of the best ways to finish watching one movie from each category at the earliest time are as follows:

Start watching comedy movie1 at time t = 1 and until t = 1 + 3 = 4. Then, watch the drama movie1 from time t = 4 to t = 4 + 2 = 6.

Start watching a comedy movie2 at time t = 2 and until t = 2 + 2 = 4. Then, watch the drama movie1 from time t = 4 to t = 4 + 2 = 6.

The earliest finish time and also answer is 6.

Examples that are suboptimal include:

Start watching a comedy movie2 at time t = 4 and until t = 4 + 2 = 6. Then, watch the drama movie1 from time t = 6 to t = 6 + 2 = 8.

Start watching a comedy movie1 at time t = 1 and until t = 1 + 3 = 4. Then, watch the drama movie1 from time t = 5 to t = 5 + 2 = 7.

Constraints:
1 <= n, m <= 10^5
1 <= comedyReleaseTime[i], comedyDuration[i], dramaReleaseTime[i], dramaDuration[i] <= 10^6

* */


import java.util.Map;
import java.util.TreeMap;

public class MovieSchedule {

    public static int minimumTimeSpent(int[] comedyReleaseTime, int[] comedyDuration, int[] dramaReleaseTime, int[] dramaDuration) {
        Map<Integer, Integer> comedy = new TreeMap<>();
        Map<Integer, Integer> drama = new TreeMap<>();

        for (int i = 0; i < comedyReleaseTime.length; i++) {
            comedy.put(comedyReleaseTime[i], comedyDuration[i]);
        }
        for (int i = 0; i < dramaReleaseTime.length; i++) {
            drama.put(dramaReleaseTime[i], dramaDuration[i]);
        }

        int minFinishTime = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> comedyEntry : comedy.entrySet()) {
            int comedyStart = comedyEntry.getKey();
            if (comedyStart > minFinishTime) break;

            for (Map.Entry<Integer, Integer> dramaEntry : drama.entrySet()) {
                int dramaStart = dramaEntry.getKey();
                if (dramaStart > minFinishTime) break;

                int comedyFinishTime = comedyStart + comedyEntry.getValue();
                int finishTime;

                if (dramaStart <= comedyFinishTime) {
                    finishTime = comedyFinishTime + dramaEntry.getValue();
                } else {
                    finishTime = dramaStart + dramaEntry.getValue();
                }

                minFinishTime = Math.min(minFinishTime, finishTime);
            }
        }

        for (Map.Entry<Integer, Integer> dramaEntry : drama.entrySet()) {
            int dramaStart = dramaEntry.getKey();
            if (dramaStart > minFinishTime) break;

            for (Map.Entry<Integer, Integer> comedyEntry : comedy.entrySet()) {
                int comedyStart = comedyEntry.getKey();
                if (comedyStart > minFinishTime) break;

                int dramaFinishTime = dramaStart + dramaEntry.getValue();
                int finishTime;

                if (comedyStart <= dramaFinishTime) {
                    finishTime = dramaFinishTime + comedyEntry.getValue();
                } else {
                    finishTime = comedyStart + comedyEntry.getValue();
                }

                minFinishTime = Math.min(minFinishTime, finishTime);
            }
        }

        return minFinishTime;
    }

    public static void main(String[] args) {
        int[] comedyReleaseTime = {1, 4};
        int[] comedyDuration = {3, 2};
        int[] dramaReleaseTime = {5, 2};
        int[] dramaDuration = {2, 2};

        int result = minimumTimeSpent(comedyReleaseTime, comedyDuration, dramaReleaseTime, dramaDuration);
        System.out.println("The earliest finish time is: " + result);
    }
}
