/*
* First question:

Given a room represented like a grid, you have to escape from the fire. The grid looks like the following where 0 represents empty place you can move through, 1 represents you (a person) and 2 represents fire. You can consider yourself escaped if you reach any of the sides that's not on fire (ie., grid[i][j]!=2 && i = 0 || j == 0 || i == m-1 || j == n-1 where 0<=i<m , 0 <=j<n and m,n being length and height of the grid).

0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0
0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0
Goal is to find the shortest distance from 1 to any of the sides. I was able to solve this with plain BFS.

Followup:

Now for every step you take, the fire grows a step in all four directions. For the example above, for the next step you make, the room would look like this.

0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0
2 2 2 0 0 0 0 0 0 0 2 0 0 0 2 2 2 0
0 2 0 0 0 0 0 0 0 2 2 2 0 0 0 2 0 0
0 0 0 0 0 0 0 1 0 0 2 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 2 2 2 0 0 0
0 2 2 2 0 0 0 0 2 0 0 0 0 2 0 0 0 0
0 0 2 0 0 0 0 2 2 2 0 0 0 0 0 0 0 0
Again the goal is to escape fire


* */


import java.util.LinkedList;
import java.util.*;


public class FireEscape {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean escapeFire(int[][] matrix) {
        Queue<Point> queue = new LinkedList<>();
        Queue<Point> fires = new LinkedList<>();
        initStartCoord(matrix, queue, fires);
        int[][] moves = {{0,1}, {0,-1}, {1,0}, {-1,0}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                if (matrix[p.x][p.y] == 2) continue;
                for (int[] move : moves) {
                    int newRow = p.x + move[0], newCol = p.y + move[1];
                    if (newRow == -1 || newRow == matrix.length || newCol == -1 || newCol == matrix[0].length) {
                        return true; // Reached the edge, escaped
                    }
                    if (matrix[newRow][newCol] == 0) {
                        matrix[newRow][newCol] = 1; // Mark as visited
                        queue.add(new Point(newRow, newCol));
                    }
                }
            }
            moveFire(matrix, fires);
        }
        return false;
    }

    private static void moveFire(int[][] matrix, Queue<Point> fires) {
        int[][] moves = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int size = fires.size();
        for (int i = 0; i < size; i++) {
            Point fire = fires.poll();
            for (int[] move : moves) {
                int newRow = fire.x + move[0], newCol = fire.y + move[1];
                if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length && matrix[newRow][newCol] != 2) {
                    matrix[newRow][newCol] = 2;
                    fires.add(new Point(newRow, newCol));
                }
            }
        }
    }

    private static void initStartCoord(int[][] matrix, Queue<Point> queue, Queue<Point> fires) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    queue.add(new Point(i, j));
                } else if (matrix[i][j] == 2) {
                    fires.add(new Point(i, j));
                }
            }
        }
    }

    public static boolean solve(int n, int m, int[] personLoc, List<int[]> fireLoc) {
        Queue<int[]> q = new LinkedList<>();
        int[][] vis = new int[n][m];
        int[][] del = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Add person location with type 1
        q.offer(new int[]{personLoc[0], personLoc[1], 1});

        // Add fire locations with type 2
        for (int[] loc : fireLoc) {
            q.offer(new int[]{loc[0], loc[1], 2});
        }

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int type = node[2], x = node[0], y = node[1];

            // Mark the cell as visited with the type
            vis[x][y] = type;

            // Check if person has reached the boundary
            if (type == 1 && (x == n-1 || x == 0 || y == 0 || y == m-1)) {
                return true;
            }

            // Visit neighbours
            for (int[] d : del) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if ((type == 1 && vis[nx][ny] == 0) || (type == 2 && vis[nx][ny] != 2)) {
                        q.offer(new int[]{nx, ny, type});
                    }
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {0,0,0,0,0,0},
                {0,2,0,0,0,0},
                {0,0,1,2,0,0},
                {0,0,2,0,0,0},
                {0,0,0,0,0,0}
        };

        System.out.println(escapeFire(matrix) ? "Escaped" : "Trapped");

        int[][] grid = {
                {0,0,0,0,0,0},
                {0,2,0,0,0,0},
                {0,0,1,2,0,0},
                {0,0,2,0,0,0},
                {0,0,0,0,0,0}
        };
        int n = grid.length;
        int m = grid[0].length;
        List<int[]> fireLoc = new ArrayList<>();
        int[] personLoc = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = grid[i][j];
                if (x == 1) {
                    personLoc[0] = i;
                    personLoc[1] = j;
                } else if (x == 2) {
                    fireLoc.add(new int[]{i, j});
                }
            }
        }

        System.out.println(solve(n, m, personLoc, fireLoc) ? "Yes" : "No");
    }
}
