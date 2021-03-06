/*
    Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

    The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

    You may assume the integer does not contain any leading zero, except the number 0 itself.

    Example 1:

    Input: [1,2,3]
    Output: [1,2,4]
    Explanation: The array represents the integer 123.
    Example 2:

    Input: [4,3,2,1]
    Output: [4,3,2,2]
    Explanation: The array represents the integer 4321.
 */

// Solution 1: 
// Time: O(n)
// Space: O(1)

// Version 2:
// 08/03/2018
class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];
            digits[i] = (digit + carry) % 10;
            carry = (digit + carry) / 10;
            if (carry == 0) {
                return digits;
            } 
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }
}

// Version 1:
public class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--) { // start from the right most digit
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // done
            }
            digits[i] = 0; // 9 + 1 = 10, set this digit to 0 and do the carry
        }

        // at this point, we overflow the array, need a larger array here
        int[] newDigits = new int[digits.length+1];
        newDigits[0] = 1; 
        return newDigits;
    }
}