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

第一轮：两个国人小哥，非常nice，全程跟我着我思路一起做题的节奏，我方向错了也及时把我拉回来，感激涕零。题目是，给两个正数，p和q，求a*p + b*q的最小的k个数字，a和b为非负数。这题我秒联想到了leetcode 264，第k大的ugly number了；还好这两救命恩人把我拉回来，帮我分析，最后用最小堆完成；代码非常简洁，六七行把。就这一题。
后面聊天，他们组有用c＋＋的，有java的，有python的，总之语言不受限制。
第二轮：美国小哥，宾州人lol。非常nice。
第一题，给一个字符串，和list of intervals of index；求没被这些覆盖的子序。很简单的题，先用naive方法写了，写的时候跟他说了，这个方法并不一定好，但是简单。后面问我怎么优化，我说简单，先把intervals合并，这样每个index只会被检查一次。小哥表示恩可以，并没有让写出来。
第二题，给一个head node；链表；假设有一个matrix，head在左上；每个node可能有down和right；这题跟面经那题不一样；这题是给了一个规则，对任何一个down node，如果想向下，能到的
格子右边必须是全空的；就是说，如果右边有别的节点，down node需要再多往下放一个位置。最后求最小的matrix面积，可以容下所有节点，并符合规则。用stack搞定了。注意更新长和宽就可以。小哥说写伪代码即可，我还是把所有代码写出来了。全程我一边思考一边说出自己想法，小哥全程跟着我的思路，还给我列了几个例子帮我理解。太nice了。

hr：跟纽约办公室的一个hr video聊天，随便说说了以前的经历，问了现在的情况等等。
第四轮manager：呆了13年的manager。上来寒暄完，问我知道double precision不，逼人不才，第一次听说precision这个词；问我用过float不，我说没有。。。。尴尬癌。。毕竟cs科班出身尴尬，当时manager脸色不好看，估计在怀疑人生，这样的人怎么前两轮没挂。好吧出题，求一个double value的平方根；这题我说用binary search做；写完coding后，今天面试里最有趣的一幕来了，他问我，你怎么想到用binary search的，我说数学知识。。他问你想得到别的办法不？
这里我想暂停两行，希望各位战友想想这个问题。integer 求平方根，leetcode有原题，O(logn)时间复杂度我说可以，满快的；double求平方根一个道理。但是有没有更快的办法呢？经过了3秒我大脑转了1亿次没想到；
不过
马上我想到了，机缘巧合之下，我这学期修了一门数学课，里面有一个知识点，bisection 和 newton method。就是说，一个连续曲线，给定的两个x，其y值一正一负，那么这两点之间必有一点x，使y为0。bisection就是二分搜索；newton‘s method是求切线，然后找到下一个参考点。newton’s method比二分搜索更快的得到结果。我跟manager描述了这个理论，和这个方法，他开心的笑。

9. backpack problem
