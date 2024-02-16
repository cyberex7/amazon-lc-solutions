/*
* Given a sorted list of integers, rearrange the list in-place such that all unique elements appear at
the beginning of the list.
Your function should return the new length of the list containing only unique elements. Note that you should not create a new list or use any additional data structures to solve this problem. The original list should be modified in-place.

Constraints:
The input list is sorted in non-decreasing order.
The input list may contain duplicates.
The function should have a time complexity of O(n), where n is the length of the input list.
The function should have a space complexity of O(1), i.e.,
it should not use any additional data structures or create new lists.

nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4];
Output = 5, [0, 1, 2, 3, 4, 2, 2, 3, 3, 4] (first 5 elements are unique)
*
* */

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class UniqueElements {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 2, 2, 3, 3, 4));
        int newLength = rearrangeList(list);
        System.out.println("New Length: " + newLength);

        for (int i = 0; i < newLength; i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public static int rearrangeList(ArrayList<Integer> list) {
        if (list.isEmpty()) return 0;

        int j = 0;
        for (int i = 1; i < list.size(); i++) {
            if (!list.get(i).equals(list.get(j))) {
                list.set(++j, list.get(i));
            }
        }
        return j + 1;
    }
}
