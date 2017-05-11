1. Palindrome Partition
public class Solution {
    List<List<String>> resultLst;
	ArrayList<String> currLst;
	public List<List<String>> partition(String s) {
		resultLst = new ArrayList<List<String>>();
        currLst = new ArrayList<String>();
        backTrack(s,0);
        return resultLst;
	}

	private void backtrack(String s, int l) {
		if(currLst.size() > 0 //the initial str could be palindrome
            && l >= s.length()){
                List<String> r = (ArrayList<String>) currLst.clone();
                resultLst.add(r);
        }
        for(int i = l; l < s.length(); i++) {
        	if(isPalindrome(s, l, i)) {
        		if(l == i) currLst.add(Character.toString(s.charAt(i)));
        		else currLst.add(s.substring(l, i + 1));
        		backtrack(s, i + 1);
        		currLst.remove(currLst.size() - 1);
        	}
        }
	}

	private boolean isPalindrome(Stirng s, int l, int r) {
		if(l==r) return true;
        while(l < r){
            if(str.charAt(l) != str.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
	}
}

2. strStr
public int strStr(int s, int t) {
	if(s == null || t == null) return -1;
	for(int i = 0; i < s.length() - t.length() + 1; i++) {
		int j = 0;
		while(j < t.length()) {
			if(s.charAt(i + j) != t.charAt(j)) break;
		}
		if(j == t.length()) return i;
	}
	return -1;
}

3. Find Median from Data Stream
I keep two heaps (or priority queues):

Max-heap small has the smaller half of the numbers.
Min-heap large has the larger half of the numbers.
This gives me direct access to the one or two middle values (they are the tops of the heaps), so getting the median takes O(1) time. And adding a number takes O(log n) time.

class MedianFinder {
	private Queue<Long> small = new PriorityQueue(),
						large = new PriorityQueue();

	public void addNum(int num) {
		large.add((long) num);
		small.add(-large.poll());
		if(large.size() < small.size()) large.add(-small.poll());
	}

	public double findMedian() {
		return large.size() > small.size() ? large.peek() : (large.peek() - small.peek()) / 2.0;
	}
}

class MedianFinder {
    // max queue is always larger or equal to min queue
    PriorityQueue<Integer> min = new PriorityQueue();
    PriorityQueue<Integer> max = new PriorityQueue(1000, Collections.reverseOrder());
    // Adds a number into the data structure.
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()){
            max.offer(min.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (max.size() == min.size()) return (max.peek() + min.peek()) /  2.0;
        else return max.peek();
    }
};

4. Sliding Window Median
For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

a. Use two Heaps to store numbers. maxHeap for numbers smaller than current median, minHeap for numbers bigger than and equal to current median. 
	A small trick I used is always make size of minHeap equal (when there are even numbers) or 1 element more (when there are odd numbers) than the size of maxHeap. 
	Then it will become very easy to calculate current median.
b. Keep adding number from the right side of the sliding window and remove number from left side of the sliding window. 
   And keep adding current median to the result.

public class Solution {
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
        new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        }
    );
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
		if (n <= 0) return new double[0];
	    double[] result = new double[n];
	        
        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
        	result[i - k] = getMedian();
        	remove(nums[i - k]);
            }
            if (i < nums.length) {
        	add(nums[i]);
            }
        }
        
        return result;
	}
	    
	private void add(int num) {
		if (num < getMedian()) {
		    maxHeap.add(num);
		}
		else {
		    minHeap.add(num);
		}
		if (maxHeap.size() > minHeap.size()) {
	        minHeap.add(maxHeap.poll());
		}
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
	}
		
	private void remove(int num) {
		if (num < getMedian()) {
		    maxHeap.remove(num);
		}
		else {
		    minHeap.remove(num);
		}
		if (maxHeap.size() > minHeap.size()) {
	        minHeap.add(maxHeap.poll());
		}
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
    }
	
    private double getMedian() {
		if (maxHeap.isEmpty() && minHeap.isEmpty()) return 0;
		    
		if (maxHeap.size() == minHeap.size()) {
		    return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
		}
		else {
            return (double)minHeap.peek();
		}
    }
}

5. Word Search
/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 *    ["ABCE"],
 *    ["SFCS"],
 *    ["ADEE"]
 *  ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null) 
            return false;
        if(word == null || word.length() == 0) 
            return true;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(DFS(board, i, j, word, 0, visited))
                    return true;
            }
        }
        return false;
    }
    private boolean DFS(char[][] board, int i, int j, String word, int len, boolean[][] visited) {
        if(visited[i][j] || board[i][j] != word.charAt(len)) 
            return false;
        if(len == word.length() - 1) 
            return true;
        visited[i][j] = true;
        // walk left;
        if(i != 0 && DFS(board, i - 1, j, word, len + 1, visited))
            return true;
        // walk right;
        if(i != board.length - 1 && DFS(board, i + 1, j, word, len + 1, visited))
            return true;
        // walk up
        if(j != 0 && DFS(board, i, j - 1, word, len + 1, visited))
            return true;
        // walk down
        if(j != board[0].length - 1 && DFS(board, i, j + 1, word, len + 1, visited))
            return true;
        // backtracking
        visited[i][j] = false;
        return false;
    }
}

6. Binary Tree Right Side View
/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 *     1            
 *   /   \
 *  2     3         
 *   \     \
 *    5     4       
 * You should return [1, 3, 4].
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
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
            return result;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(queue.size() > 0) {
            // get size here
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode top = queue.remove();
                // the first element in the queue (right-most of the tree)
                if(i == 0) {
                    result.add(top.val);
                }
                if(top.right != null)
                    queue.add(top.right);
                if(top.left != null)
                    queue.add(top.left);
            }
        }
        return result;
    }
}

7. tic tac toe 

8. Find two sum in BST
