

import java.util.HashMap;

public class Substrings_exactly_K_distinct_chars {
    // Method to calculate the number of substrings with at most K distinct characters
    private static int substringsAtMostKDistinctChars(String s, int K) {
        if (K == 0) return 0;

        int count = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                map.remove(c);
            }
            map.put(c, right);

            while (map.size() > K) {
                char leftChar = s.charAt(left);
                int deleteIndex = map.get(leftChar);
                if (deleteIndex == left) {
                    map.remove(leftChar);
                }
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }

    // Method to calculate the number of substrings with exactly K distinct characters
    public static int substringsExactKDistinctChars(String s, int K) {
        int count1 = substringsAtMostKDistinctChars(s, K);
        int count2 = substringsAtMostKDistinctChars(s, K - 1);
        return count1 - count2;
    }

    public static void main(String[] args) {
        String s1 = "pqpqs";
        int k1 = 2;
        System.out.println("Example 1: " + substringsExactKDistinctChars(s1, k1));

        String s2 = "aabab";
        int k2 = 3;
        System.out.println("Example 2: " + substringsExactKDistinctChars(s2, k2));
    }
}
