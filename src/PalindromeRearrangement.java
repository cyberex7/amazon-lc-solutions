/*
* The algorithm rearranges the characters to have
the following characteristics:
« It is a rearrangement of the original palindromic
password.
It is also a palindrome.
Among all such palindromic rearrangements, it is.
the lexicographically smallest.
Given the original palindromic password that
consists of lowercase English characters only, find
the encrypted password.
A string s is considered to be lexicographically
smaller than the string ¢ of the same length if the
first character in s that differs from that in tis
smaller. For example, "abcd" is lexicographically
smaller than "abdc" but larger than "abad"

Note that the encrypted password might be the
same as the original password if it is already
lexicographically smallest.

Example:

The original password is password = "babab".

This can be rearranged to form abbba, which is
both a rearrangement of the original password and
the lexicographically smallest palindrome.

It satisfies all the requirements so return the string
abbba.

Returns
string: the encrypted password
Constraints
1< [password] < 10^5

password consists of lowercase English letters only.

password is a palindrome.
* */

public class PalindromeRearrangement {
    public static String findEncryptedPassword(String password) {
        int[] charMap = new int[26];
        for (int i = 0; i < password.length(); i++) {
            charMap[password.charAt(i) - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        Character middleChar = null;
        for (int i = 0; i < 26; i++) {
            if (charMap[i] > 0) {
                for (int j = 0; j < charMap[i] / 2; j++) {
                    firstHalf.append((char) (i + 'a'));
                }
                if (charMap[i] % 2 != 0) {
                    middleChar = (char) (i + 'a');
                }
            }
        }

        String secondHalf = firstHalf.reverse().toString();
        firstHalf.reverse(); // Reverse it back to original for final concatenation

        if (middleChar == null) {
            return firstHalf.toString() + secondHalf;
        } else {
            return firstHalf.toString() + middleChar + secondHalf;
        }
    }

    public static void main(String[] args) {
        System.out.println(findEncryptedPassword("baabaab"));
        System.out.println(findEncryptedPassword("aba"));
        System.out.println(findEncryptedPassword("babab"));
        System.out.println(findEncryptedPassword("xzxyxzx"));
    }
}
