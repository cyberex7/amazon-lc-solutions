/*
* Write a function to crush candy in one dimensional board. In candy crushing games, groups of like items are removed from the board. In this problem, any sequence of 3 or more like items should be removed and any items adjacent to that sequence should now be considered adjacent to each other. This process should be repeated as many time as possible. You should greedily remove characters from left to right.

Example 1:

Input: "aaabbbc"
Output: "c"
Explanation:
1. Remove 3 'a': "aaabbbbc" => "bbbbc"
2. Remove 4 'b': "bbbbc" => "c"
Example 2:

Input: "aabbbacd"
Output: "cd"
Explanation:
1. Remove 3 'b': "aabbbacd" => "aaacd"
2. Remove 3 'a': "aaacd" => "cd"
Example 3:

Input: "aabbccddeeedcba"
Output: ""
Explanation:
1. Remove 3 'e': "aabbccddeeedcba" => "aabbccdddcba"
2. Remove 3 'd': "aabbccdddcba" => "aabbcccba"
3. Remove 3 'c': "aabbcccba" => "aabbba"
4. Remove 3 'b': "aabbba" => "aaa"
5. Remove 3 'a': "aaa" => ""
Example 4:

Input: "aaabbbacd"
Output: "acd"
Explanation:
1. Remove 3 'a': "aaabbbacd" => "bbbacd"
2. Remove 3 'b': "bbbacd" => "acd"
I solved it with recursion and also discussed the stack based approach.

Follow-up:
What if you need to find the shortest string after removal?

Example:

Input: "aaabbbacd"
Output: "cd"
Explanation:
1. Remove 3 'b': "aaabbbacd" => "aaaacd"
2. Remove 4 'a': "aaaacd" => "cd"
*
* */

import java.util.*;
public class CandyCrush_InterviewBB {
    public String solution(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> occurrence = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (stack.isEmpty() || c != stack.peek()) {
                if (!stack.isEmpty() && occurrence.peek() >= 3) {
                    stack.pop();
                    occurrence.pop();
                } else {
                    stack.push(c);
                    occurrence.push(1);
                    i++;
                }
            } else {
                int count = occurrence.pop();
                occurrence.push(count + 1);
                i++;
            }
        }

        if (occurrence.peek() >= 3) {
            stack.pop();
            occurrence.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            int count = occurrence.pop();
            while (count > 0) {
                sb.append(c);
                count--;
            }
        }

        return sb.reverse().toString();
    }

    public String solution_follow_up(String s) {
        boolean crushed = true;
        while (crushed) {
            Stack<Character> stack = new Stack<>();
            Stack<Integer> occurrence = new Stack<>();
            crushed = false;

            for (int i = 0; i < s.length(); ) {
                char c = s.charAt(i);
                if (stack.isEmpty() || c != stack.peek()) {
                    if (!stack.isEmpty() && occurrence.peek() >= 3) {
                        stack.pop();
                        occurrence.pop();
                        crushed = true;
                    } else {
                        stack.push(c);
                        occurrence.push(1);
                        i++;
                    }
                } else {
                    int count = occurrence.pop();
                    occurrence.push(count + 1);
                    i++;
                }
            }

            if (!occurrence.isEmpty() && occurrence.peek() >= 3) {
                stack.pop();
                occurrence.pop();
                crushed = true;
            }

            StringBuilder sb = new StringBuilder();
            Stack<Character> tempStack = new Stack<>();
            Stack<Integer> tempOccurrence = new Stack<>();
            while (!stack.isEmpty()) {
                char c = stack.pop();
                int count = occurrence.pop();
                tempStack.push(c);
                tempOccurrence.push(count);
            }
            while (!tempStack.isEmpty()) {
                char c = tempStack.pop();
                int count = tempOccurrence.pop();
                while (count > 0) {
                    sb.append(c);
                    count--;
                }
            }

            s = sb.toString();
        }
        return s;
    }

    public static void main(String[] args) {
        CandyCrush_InterviewBB solution = new CandyCrush_InterviewBB();
        System.out.println(solution.solution("aaabbbc"));
        System.out.println(solution.solution("aabbbacd"));
        System.out.println(solution.solution("aabbccddeeedcba"));
        System.out.println(solution.solution("aaabbbacd"));
        System.out.println(solution.solution_follow_up("aaabbbacd"));

    }
}