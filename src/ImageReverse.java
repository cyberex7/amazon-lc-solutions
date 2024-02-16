/*
* Amazon Web Services stores grayscale images as an array of white and black pixels. The image is stored as a binary string where a white pixel is represented by "1". and a black pixel is represented by "0". The reverse of the image is represented as the reverse of this binary representation . For example, the reverse of "11010" is "01011". They want to store the reverse of each image as a backup. In order to reproduce the reverse from the original, the following operation can be preformed any number of timese: remove any cahracter from th estring and append it to the end of the strin, i.e, choose any pixel and shift it to the end of the string.
Given the binary representation of pixels denoted by image, find the minimum number of operations needed to produce its reverse.

Example
The pixel representation is image = "0100110".
The reverse of the image, i.e, the reverse of the following sequence operations
image
The string cannot be reversed in fewer than 3 operations. Return 3

Function description
Complete the function findMinimumOperations. The function takes the following parameter:
string image: a binary string that represents an image.

Returns
int: the minimum number of operations required to produce a reverse, i.e to reverse the string.

Constraints
1 <= length of image <= 10^5

Example Case 1
Input : "00110101"
Output: 3
Explaination:
The string can be reveresd in minimum of 3 moves using the following sequence:

00110101 -> 00101011
00101011 -> 01010110
01010110 -> 10101100

Example Case 2
Input: 101011
Output: 2
Explaination:
101011 -> 110110
110110 -> 110101
* */

public class ImageReverse {
    // Function to find the minimum number of operations to reverse the binary string
    public static int findMinimumOperations(String s) {
        int n = s.length();
        int r = 0;
        for (int i = n - 1, j = 0; i >= 0 && j < n; ++j) {
            if (s.charAt(i) == s.charAt(j)) {
                --i;
                ++r;
            }
        }
        return n - r;
    }

    // Driver code
    public static void main(String[] args) {
        System.out.println(findMinimumOperations("00110101")); // Output: 3
        System.out.println(findMinimumOperations("101011"));   // Output: 2
    }
}
