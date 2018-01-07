/*
 *  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 *
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */

// Solution 2: Bottom-up DP
// Put s1 and s2 as the vertical and horizontal axis.
// At each state DP[i][j], there are three possibilities
//      1. s1[i] == s3[i+j], result depends on dp[i-1][j]
//      2. s2[j] == s3[i+j], result depends on dp[i][j-1]
//      3. Otherwise, false
//
// reference: https://discuss.leetcode.com/topic/7728/dp-solution-in-java
// Time: O(mn)
// Space: O(mn) - dp arrays
// 01/07/2018

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        // dp[i][j] == true when substring s1[0:i] and s2[0:j] interleave s3[0:i+j-1]
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;    // base case
        for (int i = 1; i < s1.length()+1; i++) {   // initialize the first row and first col
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }
        for (int j = 1; j < s2.length()+1; j++) {
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }
        for (int i = 1; i < s1.length()+1; i++) {
            for (int j = 1; j < s2.length()+1; j++) {
                // dp[i-1][j] == true --> first i-1 of s1 and j of s2 matches s3[0, i+j-2]
                // we need to see if the next char in s1 matches next char in s3
                // Same applies for the second check
                dp[i][j] = (dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1)) ||
                    (dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1));
            }
        }
        return dp[s1.length()][s2.length()];
    }
}


// Solution 1: Brute force
// Generate all permutations and see if there is a match with s3
// Time: O(2^(m+n)) - time limit exceeded
// Space: O(m+n) - n level call stack
// 01/06/2018
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return search(s1, s2, 0, 0, "", s3);
    }
    
    public boolean search(String s1, String s2, int index1, int index2, String cur, String s3) {
        if (cur.equals(s3) && index1 == s1.length() && index2 == s2.length()) return true;     
        if (index1 >= s1.length() || index2 >= s2.length()) return false;

        if (search(s1, s2, i+1, index2, cur + s1.charAt(i), s3)) {
            return true;
        }

        if (search(s1, s2, index1, i+1, cur + s2.charAt(i), s3)) {
            return true;
        }
        return false;
    }
}