/*
 * Given a collection of distinct numbers, return all possible permutations.
 */

// Solution 3:
// iterative approach
// add one integer at a time, and add it to all possible insert position
// reference: https://leetcode.com/problems/permutations/discuss/18237/
// Time: O(n!): n! permutations
// Space O(n * n!): n! permutations, each has length of n
// 01/15/2018
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> newRes = new ArrayList<>();
            for (List<Integer> l : res) {
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> newL = new ArrayList<>(l);
                    newL.add(j, nums[i]);
                    newRes.add(newL);
                }
            }
            res = newRes;
        }
        return res;
    }
}

// Solution 2:
// first convert int[] to ArrayList so that we can add and remove easily
// better performance than solution 1, yet use extra memory space
// Time: O(n! * n) - n for removing and adding at each level
// Space: O(n) - call stack
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        ArrayList<Integer> number = new ArrayList<>();      // convert int[] to arraylist so that we can add and remove
        for (int n : nums) {
            number.add(n);
        }
        helper(res, number, nums.length, new ArrayList<Integer>());
        return res;
    }

    public void helper(List<List<Integer>> res, ArrayList<Integer> nums, int len, ArrayList<Integer> list) {
        if (list.size() == len) {
            res.add(new ArrayList<Integer>(list));   // reach end
        }

        for (int i = 0; i < nums.size(); i++) {     // try number at different position
            int n = nums.get(i);
            list.add(n);
            nums.remove(i);  // remove this number so that we won't use it again later
            helper(res, nums, len, list);   // recursively solve subproblems
            nums.add(i, n);  // add it back so that we can use it in the next iteration
            list.remove(list.size()-1);     // remove the last int we add to the list
        }
    }
}

// Solution 1:
// Time: O(n! * n) - n! permutation * O(n) contains() in each call
// Space: O(n) - stack

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<Integer>(), nums);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));   // need this
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {   // skip numbers that we already used
                continue;                   // need to check for every integer we are going to add
            }                               // influence performance O(len(list.length)) time
            list.add(nums[i]);
            helper(result, list, nums);
            list.remove(list.size()-1);
        }
    }
}
