/*
    Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

    Each element is either an integer, or a list -- whose elements may also be integers or other lists.

    Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

    Example 1:
    Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

    Example 2:
    Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */


// Solution 2: 
// Insert of multiplying sum by depth, repeatedly add previous results.
// Time: O(n) - one pass
// Space: O(n) - next level list
// 01/15/2018
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int res = 0, prev = 0;
        while (!nestedList.isEmpty()) {
            int current = 0;
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    current += ni.getInteger();
                } else {
                    nextLevel.addAll(ni.getList());
                }
            }
            prev += current;
            res += prev;
            nestedList = nextLevel;
        }
        return res;
    }
}

// Solution 1: Get depth, then get sum
// Time: O(n) - two passes
// Space: O(n) - call stack (worst case)
// 01/15/2018
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int depth = findDepth(nestedList);
        return depthSum(nestedList, depth);
    }
    
    public int depthSum(List<NestedInteger> nestedList, int depth) {
        int res = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                res += depth * ni.getInteger();
            } else {
                res += depthSum(ni.getList(), depth - 1);
            }
        }
        return res;
    }
    
    public int findDepth(List<NestedInteger> nestedList) {
        int depth = 1;
        for (NestedInteger ni : nestedList) {
            if (!ni.isInteger()) {
                depth = Math.max(depth, 1 + findDepth(ni.getList()));
            }
        }
        return depth;
    }
}