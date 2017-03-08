public class Solution {
	private int max = 0;
	public int longestConsecutive(TreeNode root) {
		if (root == null) return 0;
		return max;
	}
	public void helper(TreeNode root, int curMax, int target) {
		if(root == null) return;
		if(root.val == target) curMax++;
		else curMax = 0;
		max = Math.max(curMax, max);
		helper(root.left, curMax, root.val + 1);
		helper(root.right, curMax, root.val + 1);
	}
}