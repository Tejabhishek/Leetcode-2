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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if(root == null) return res;
        helper(res, root.val + "", root);
        return res;
    }

    public void helper(List<String> res, String path, TreeNode node) {
    	if(node.left == null && node.right == null) {
    		res.add(path);
    		return;
    	}
    	if(node.left != null) {
    		helper(res, path + "->" + node.left.val, node.left);
    	}
    	if(node.right != null) {
    		helper(res, path + "->" + node.right.val, node.right);
    	}
    }
}