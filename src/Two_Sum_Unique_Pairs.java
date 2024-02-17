/*
* Given an int array nums and an int target, find how many unique pairs in the array such that their sum is equal to target. Return the number of pairs.

Example 1:

Input: nums = [1, 1, 2, 45, 46, 46], target = 47
Output: 2
Explanation:
1 + 46 = 47
2 + 45 = 47
Example 2:

Input: nums = [1, 1], target = 2
Output: 1
Explanation:
1 + 1 = 2
Example 3:

Input: nums = [1, 5, 1, 5], target = 6
Output: 1
Explanation:
[1, 5] and [5, 1] are considered the same.
*
* */


import java.util.*;

public class Two_Sum_Unique_Pairs {
    public static int getUniquePairsOpti(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                int key = map.get(num) * 10 + num;
                if (!seen.contains(key)) {
                    ans++;
                    seen.add(key);
                }
            } else {
                map.put(target - num, num);
            }
        }
        return ans;
    }

    public static int getUniquePairs(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array first
        int i = 0;
        int j = nums.length - 1;
        int ans = 0;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                ans++; // Found a unique pair
                i++;
                j--;
                // Skip duplicates for i
                while (i < j && nums[i] == nums[i - 1]) {
                    i++;
                }
                // Skip duplicates for j
                while (i < j && nums[j] == nums[j + 1]) {
                    j--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test example 1
        int[] nums1 = {1, 1, 2, 45, 46, 46};
        int target1 = 47;
        System.out.println("Example 1:");
        System.out.println("Using sorting: " + Two_Sum_Unique_Pairs.getUniquePairs(nums1, target1));
        System.out.println("Using hashing: " + Two_Sum_Unique_Pairs.getUniquePairsOpti(nums1, target1));

        // Test example 2
        int[] nums2 = {1, 1};
        int target2 = 2;
        System.out.println("\nExample 2:");
        System.out.println("Using sorting: " + Two_Sum_Unique_Pairs.getUniquePairs(nums2, target2));
        System.out.println("Using hashing: " + Two_Sum_Unique_Pairs.getUniquePairsOpti(nums2, target2));

        // Test example 3
        int[] nums3 = {1, 5, 1, 5};
        int target3 = 6;
        System.out.println("\nExample 3:");
        System.out.println("Using sorting: " + Two_Sum_Unique_Pairs.getUniquePairs(nums3, target3));
        System.out.println("Using hashing: " + Two_Sum_Unique_Pairs.getUniquePairsOpti(nums3, target3));
    }
}
