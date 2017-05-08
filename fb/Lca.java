public class LCA {
	public TreeNode LCA_Binary_Tree(TreeNode root, TreeNode a, TreeNode b) {
		TreeNode left, right;
		if(root == null) return root;
		if(root == a || root == b) return root;
		left = LCA_Binary_Tree(root.left, a, b);
		right = LCA_Binary_Tree(root.right, a, b);
		if(left != null && right != null) {
			return root;
		} else {
			return(left != null ? left : right);
		}
	}

	public TreeNode LCA_Binary_Search_Tree(TreeNode root, TreeNode a, TreeNode b) {
		if(root == null) return root;
		if(root == a || root == b) return root;
		if(Math.max(a.val, b.val) < root.val) {
			// both on the left sub tree
			return LCA_Binary_Search_Tree(root.left, a, b);
		} else if(Math.min(a.val, b. val) > root.val) {
			return LCA_Binary_Search_Tree(root.right, a, b);
		} else {
			return root;

		}
	}
}