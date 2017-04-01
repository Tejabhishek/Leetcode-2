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
	private TreeNode leftMost(TreeNode root) {
		while(root.left != null) {
			root = root.left;
		}
		return root;
	}
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right != null) return leftMost(p.right);
        TreeNode succ = null;
        while(root != null) {
        	if(p.val < root.val) {
        		succ = root;
        		root = root.left;
        	} else if(p.val > root.val) {
        		root = root.right;
        	} else {
        		break;
        	}
        }
        return succ;
    }
}