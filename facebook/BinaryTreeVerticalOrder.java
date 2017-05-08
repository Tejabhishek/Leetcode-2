public class Solution {
	/** Approach: (from geeksforgeeks.org)
	The idea is to traverse the tree once and get the minimum and maximum horizontal distance with respect to root.
	Once we have maximum and minimum distance from root, we iterate for each vertical line at distance minimum to maximum from root,
	and for each vertical line traverse the tree and print the nodes which lie on that vertical line.
	Algorithm: 
	// min --> Minimum horizontal distance from root
	// max --> Maximum horizontal distance from root
	// hd --> Horizontal distance of current node from root
	findMinMax(tree, min, max, hd) 
		if tree is NULL then return;

		if hd is less than min then
			min = hd;
		else if hd is greater than max then
			max = hd;

		findMinMax(tree->left, min, max, hd - 1);
		findMinMax(tree->right, min, max, hd + 1);
	
	printVerticalLine(tree, line_no, hd)
		if tree is NULL then return;

		if hd is equal to line_no, then
			print(tree->data);
		printVerticalLine(tree->left, line_no, hd - 1);
		printVerticalLine(tree->right, line_no, hd + 1);

	Leetcode solution:
	1. BFS, put node, col into queue at the same time;
	2. Every left child access col - 1 while right chilc col + 1
	3. This maps node into different col buckets
	4. Get col boundary min and max on the fly
	5. Retrieve result from cols
	*/
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        q.add(root);
        cols.add(0);

        int min = 0, max = 0;
        while(!q.isEmpty()) {
        	TreeNode node = q.poll();
        	int col = cols.poll();

        	if(!map.containsKey(col)) {
        		map.put(col, new ArrayList<Integer>());
        	}

        	map.get(col).add(node.val);

        	if(node.left != null) {
        		q.add(node.left);
        		cols.add(col - 1);
        		min = Math.min(min, col - 1);
        	}
        	if(node.right != null) {
        		q.add(node.right);
        		cols.add(col + 1);
        		max = Math.max(max, col + 1);
        	}
        }
        for(int i = min; i <= max; i++) {
        	res.add(map.get(i));
        }

        return res;
    }
}