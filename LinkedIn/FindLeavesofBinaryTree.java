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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, res);
        return res;
    }
    
    // traverse the tree bottom-up recursively
    private int helper(TreeNode root, List<List<Integer>> list) {
        if(root == null) return -1;
        int left = helper(root.left, list);
        int right = helper(root.right, list);
        int curr = Math.max(left, right) + 1;

        // the first time this code is reached is when curr == 0;
        // since the tree is bottom-up processed.
        if(list.size() <= curr) {
        	list.add(new ArrayList<Integer>);
        }
        list.get(curr).add(root.val);
        return curr;
    }
}