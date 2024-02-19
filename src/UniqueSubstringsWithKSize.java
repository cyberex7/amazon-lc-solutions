


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueSubstringsWithKSize {

    public List<String> count(String S, int k) {
        if (S == null || S.length() == 0 || k <= 0 || k > 26) {
            return new ArrayList<>();
        }

        int distinct = 0, i = 0;
        int[] memo = new int[26]; // Frequency array for characters
        Set<String> set = new HashSet<>(); // To store unique substrings

        // Initialize the first window of size k
        for (i = 0; i < k && i < S.length(); i++) {
            if (memo[S.charAt(i) - 'a'] == 0) {
                distinct += 1; // New distinct character
            }
            memo[S.charAt(i) - 'a'] += 1;
        }

        // Check if the first window has k distinct characters
        if (distinct == k) {
            set.add(S.substring(i - k, i));
        }

        // Slide the window through the string S
        while (i < S.length()) {
            if (memo[S.charAt(i) - 'a'] == 0) {
                distinct += 1; // New distinct character in the current window
            }
            memo[S.charAt(i) - 'a'] += 1;

            // Remove the character going out of the window
            memo[S.charAt(i - k) - 'a'] -= 1;
            if (memo[S.charAt(i - k) - 'a'] == 0) {
                distinct -= 1; // A distinct character went out of the window
            }

            // Add the current window to set if it has k distinct characters
            if (distinct == k) {
                set.add(S.substring(i - k + 1, i + 1));
            }

            i += 1;
        }

        return new ArrayList<>(set); // Convert set to list to return
    }

    public static void main(String[] args) {
        UniqueSubstringsWithKSize solution = new UniqueSubstringsWithKSize();

        // Test the method with examples
        System.out.println(solution.count("abcabc", 3));
        System.out.println(solution.count("abacab", 3));
        System.out.println(solution.count("awaglknagawunagwkwagl", 4));
    }
}
