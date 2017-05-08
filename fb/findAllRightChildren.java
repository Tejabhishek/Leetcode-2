public void printAllRightChildren(TreeNode root, int level) {
	Queue<TreeNode>
}

public void printRight(TreeNode root) {
	if(root == null || root.right == null) return;
	System.println(root.right);
	printRight(root.right);
}

