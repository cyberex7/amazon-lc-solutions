/*
* Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour. Find out how many hours does it take to infect all humans?

Example:

Input:
[[0, 1, 1, 0, 1],
 [0, 1, 0, 1, 0],
 [0, 0, 0, 0, 1],
 [0, 1, 0, 0, 0]]

Output: 2

Explanation:
At the end of the 1st hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [0, 1, 0, 1, 1],
 [1, 1, 1, 0, 1]]

At the end of the 2nd hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1]]
int minHours(int rows, int columns, List<List<Integer>> grid) {
	// todo
}
* */
import java.util.*;

public class ZombieInfection {
    // Method to find minimum hours to infect all humans
    public int minHours(int rows, int columns, List<List<Integer>> grid) {
        Queue<int[]> queue = new LinkedList<>();
        int hours = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Add initial zombies to the queue
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                }
            }
        }

        // BFS to simulate the infection spread
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean spread = false;
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] dir : directions) {
                    int newRow = point[0] + dir[0];
                    int newCol = point[1] + dir[1];
                    // Check if adjacent cell is valid and contains a human
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns && grid.get(newRow).get(newCol) == 0) {
                        grid.get(newRow).set(newCol, 1); // Infect the human
                        queue.offer(new int[]{newRow, newCol});
                        spread = true;
                    }
                }
            }
            if (spread) hours++; // Only increment hours if infection spread
        }

        return hours;
    }

    public static void main(String[] args) {
        ZombieInfection solution = new ZombieInfection();

        // Creating the grid
        List<List<Integer>> grid = new ArrayList<List<Integer>>();
        grid.add(Arrays.asList(0, 1, 1, 0, 1));
        grid.add(Arrays.asList(0, 1, 0, 1, 0));
        grid.add(Arrays.asList(0, 0, 0, 0, 1));
        grid.add(Arrays.asList(0, 1, 0, 0, 0));

        // Calculating the minimum hours to infect all humans
        int result = solution.minHours(4, 5, grid);

        // Printing the result
        System.out.println("Minimum hours to infect all humans: " + result);
    }
}

