/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        // min and max are null initially
        return isValid(root, null, null);
    }
    
    public boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        
        // return false if value is less than min
        if (min != null && root.val <= min) {
            return false;
        }
        
        // return false if value is greater than max
        if (max != null && root.val >= max) {
            return false;
        }
        
        // recursively test left and right subtree, with the respective range
        // min   <---- left subtree ----->  root.val  <---- right subtree ----->  max
        return isValid(root.left, min, root.val) && isValid(root.right, root.val,max);
    }
}