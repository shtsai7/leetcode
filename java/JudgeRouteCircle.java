/*
	Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

	The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

	Example 1:
	Input: "UD"
	Output: true
	Example 2:
	Input: "LL"
	Output: false
 */

// Solution 1:
// Calculate the index after each move, and
// check if the final index is (0,0)
// Time: O(n)
// Space: O(1)
// 10/27/2017

class Solution {
    public boolean judgeCircle(String moves) {
        int i = 0, j = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                j--;
            } else if (c == 'R') {
                j++;
            } else if (c == 'U') {
                i--;
            } else if (c == 'D') {
                i++;
            }
        }
        return i == 0 && j == 0;
    }
}