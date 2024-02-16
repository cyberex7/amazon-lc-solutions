/*
* Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value. Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible. Return a list of ids of selected elements. If no pair is possible, return an empty list.

Example 1:

Input:
a = [[1, 2], [2, 4], [3, 6]]
b = [[1, 2]]
target = 7

Output: [[2, 1]]

Explanation:
There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
Example 2:

Input:
a = [[1, 3], [2, 5], [3, 7], [4, 10]]
b = [[1, 2], [2, 3], [3, 4], [4, 5]]
target = 10

Output: [[2, 4], [3, 2]]

Explanation:
There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
Example 3:

Input:
a = [[1, 8], [2, 7], [3, 14]]
b = [[1, 5], [2, 10], [3, 14]]
target = 20

Output: [[3, 1]]
Example 4:

Input:
a = [[1, 8], [2, 15], [3, 9]]
b = [[1, 8], [2, 11], [3, 12]]
target = 20

Output: [[1, 3], [3, 2]]
*
*
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class OptimalIdUtilization {
    public List<int[]> optimizeUtilization(int[][] a, int[][] b, int target) {
        TreeMap<Integer, List<Integer>> mapA = new TreeMap<>();
        TreeMap<Integer, List<Integer>> mapB = new TreeMap<>();

        // Fill mapA with tasks from list a
        for (int[] task : a) {
            int value = task[1];
            if (!mapA.containsKey(value)) {
                mapA.put(value, new ArrayList<Integer>());
            }
            mapA.get(value).add(task[0]);
        }

        // Fill mapB with tasks from list b
        for (int[] task : b) {
            int value = task[1];
            if (!mapB.containsKey(value)) {
                mapB.put(value, new ArrayList<Integer>());
            }
            mapB.get(value).add(task[0]);
        }

        List<int[]> result = new ArrayList<int[]>();
        int maxSum = 0;

        for (Integer aValue : mapA.keySet()) {
            for (Integer bValue : mapB.keySet()) {
                int sum = aValue + bValue;
                if (sum <= target && sum >= maxSum) {
                    if (sum > maxSum) {
                        maxSum = sum;
                        result.clear(); // Clear previous results if a new maxSum is found
                    }
                    for (Integer aId : mapA.get(aValue)) {
                        for (Integer bId : mapB.get(bValue)) {
                            result.add(new int[]{aId, bId});
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        OptimalIdUtilization optimizer = new OptimalIdUtilization();
        int[][] a1 = {{1, 2}, {2, 4}, {3, 6}};
        int[][] b1 = {{1, 2}};
        int target1 = 7;

        int[][] a2 = {{1, 3}, {2, 5}, {3, 7},{4, 10}};
        int[][] b2 = {{1, 2},{2, 3},{3, 4},{4, 5}};
        int target2 = 10;

        int[][] a3 = {{1, 8}, {2, 7}, {3, 14}};
        int[][] b3 = {{1, 5},{2, 10},{3, 14}};
        int target3 = 20;

        int[][] a4 = {{1, 8}, {2, 15}, {3, 9}};
        int[][] b4 = {{1, 8},{2, 11},{3, 12}};
        int target4 = 20;

        List<int[]> result1 = optimizer.optimizeUtilization(a1, b1, target1);
        for (int[] pair : result1) {
            System.out.println(Arrays.toString(pair));
            System.out.println("\n");
        }

        List<int[]> result2 = optimizer.optimizeUtilization(a2, b2, target2);
        for (int[] pair : result2) {
            System.out.println(Arrays.toString(pair));
        }
        System.out.println("\n");

        List<int[]> result3 = optimizer.optimizeUtilization(a3, b3, target3);
        for (int[] pair : result3) {
            System.out.println(Arrays.toString(pair));
        }
        System.out.println("\n");

        List<int[]> result4 = optimizer.optimizeUtilization(a4, b4, target4);
        for (int[] pair : result4) {
            System.out.println(Arrays.toString(pair));
        }
        System.out.println("\n");
    }


}

