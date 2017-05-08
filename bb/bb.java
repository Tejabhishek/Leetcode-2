1. Add Two Numbers II 
// Example:
// Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 8 -> 0 -> 7
// method 1: 用stack，space O(N)， time O(N); method 2: reverse linkedlist and then add two numbers I
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        
        return list.val == 0 ? list.next : list;
    }
}

2. BST Iterator
public class BSTIterator {
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int res = node.val;
        if(node.right != null) {
            node = node.right;
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return res;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

 3. Topological Sort
// Algorithm: In DFS, we start from a vertex, we first print it and then recursively call DFS for its adjacent vertices. 
// In topological sorting, we use a temporary stack. 
// We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, then push it to a stack. 
// Finally, print contents of stack. 

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
	ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
	HashMap<DirectedGraphNode, Integer> map = new HashMap<>(); // consider as the indegree edges count
	for(DirectedGraphNode node : graph) {
		for(DirectedGraphNode neighbor: node.neighbors) {
			if(map.containsKey(neighbor)) {
				if(map.containsKey(neighbor)) {
					map.put(neighbor, map.get(neighbor) + 1);
				} else {
					map.put(neighbor, 1);
				}
			}
		}
	} 
	Queue<DirectedGraphNode> q = new linkedlist<DirectedGraphNode>();
	for(DirectedGraphNode node : graph) {
		if(!map.containsKey(node)) {
			q.offer(node);
			result.add(node);
		}
 	}
 	while(!q.isEmpty()) {
 		DirectedGraphNode node = q.poll();
 		for(DirectedGraphNode n : node.neighbors) {
 			map.put(n, map.get(n) - 1);
 			if(map.get(n) == 0) {
 				result.add(n);
 				q.offer(n);
 			}
 		}
 	}
	return result;
}

4. Group Anagram
public ArrayList<String> anagrams(String[] strs) {
    ArrayList<String> result = new ArrayList<String>();
	HashMap<String, ArrayList<String>> list = new HashMap<String, ArrayList<String>>();
	if (strs.length == 0)
		return result;
	for (String s : strs) {
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray);
		String key = new String(charArray);
		if (list.containsKey(key)) {
			list.get(key).add(s);
		} else {
			list.put(key, new ArrayList<String>(Arrays.asList(s)));
		}
	}
	for (ArrayList<String> test : list.values()) {
		if (test.size() > 1) {
			result.addAll(test);
		}
	}

	return result;
} 

5. Spiral Matrix
public List<Integer> spiralOrder(int[][] matrix) {
        
    List<Integer> res = new ArrayList<Integer>();
    
    if (matrix.length == 0) {
        return res;
    }
    
    int rowBegin = 0;
    int rowEnd = matrix.length - 1;
    int colBegin = 0;
    int colEnd = matrix[0].length - 1;
    
    while (rowBegin <= rowEnd && colBegin <= colEnd) {
        // Traverse Right
        for (int j = colBegin; j <= colEnd; j ++) {
            res.add(matrix[rowBegin][j]);
        }
        rowBegin++;
        
        // Traverse Down
        for (int j = rowBegin; j <= rowEnd; j ++) {
            res.add(matrix[j][colEnd]);
        }
        colEnd--;
        
        if (rowBegin <= rowEnd) {
            // Traverse Left
            for (int j = colEnd; j >= colBegin; j --) {
                res.add(matrix[rowEnd][j]);
            }
        }
        rowEnd--;
        
        if (colBegin <= colEnd) {
            // Traver Up
            for (int j = rowEnd; j >= rowBegin; j --) {
                res.add(matrix[j][colBegin]);
            }
        }
        colBegin ++;
    }
    
    return res;
}

6. Given Node {int val; Node up; Node down; Node next} 以及一系列的rules
// 	a. 一个Node的Up 如果不是null，那么那个Up Node的down和next都是null
//  b. 一个Node的Down 如果不是null，那么那个Down Node的up和next都是null
//  c. 所有能通过up pointer reachable的Nodes的value < current Node's value
//  d. 所有能通过down pointer reachable的Nodes的value > current Node's value
//  e. 所有能通过next pointer reachable的Nodes的value > current Node's value and those of all Nodes' reachable from down pointer
这道题只有最初的node那一条横线上的node有next 其他的node是没有的所以并不用判断 打个比方
       4     7
       |     |
1      5     8
|      |     |
2   -> 6  -> 9 ->11        给你的起点是2，那么只有2 6 9 11这几个点会有next， 其他点都只有up或dowm，这个是隐藏规则你得问他他才告诉你是这样的
|                 |
3                10 

class Node {
	Node up; 
	Node down;
	Node next;
	int val;
}

public void printAscendOrder(Node head) {
	Stack<Node> temp = new Stack<>();
	Node p = head;
	while(p != null || p.next != null || p.up != null || p.down != null) {
		
		if(p.next != null && temp.isEmpty()) {
			System.out.print(p.val + " ");
			p = p.next;
			continue;
		}
		while(p.up != null) {
			temp.push(p);
			p = p.up;
			continue;
		}
		while(!temp.isEmpty()) {
			System.out.print(temp.pop().val + " ");
		}
		while(p.down != null) {
			System.out.print(temp.down.val + " ");
		}

	}
}

// 地里看到的，还没有验证过
ListNode traverse(Node head){
	ListNode res=new LinkedList(0);
	ListNode dr=res;

	Node node=head;. from: 1point3acres.com/bbs 
	Stack<Node> stack=new Stack<>();

	  while(node!=null || !stack.isEmpty()){
	  while(node!=null ){
	  stack.push(node);
	  node=node.up;
	  }

	node=stack.pop();
	res.next=node;
	res=res.next;
	//这里不管先check next还是先check down都一样，因为两者最多只有一个不是null
	if(node.down!=null) node=node.down;
	else if(tmp.next!=null) node=node.next;
	else node=null;

	  }
	  return dr.next;
}

7. level order print tree
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    	if (root == null)
			return res;
		// Initialization
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		q.offer(null);
		ArrayList<Integer> curr = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if (tmp != null) {
				curr.add(tmp.val);
				if (tmp.left != null)
					q.offer(tmp.left);
				if (tmp.right != null)
					q.offer(tmp.right);
			} else {
				ArrayList<Integer> c_curr = new ArrayList<Integer>(curr);
				res.add(c_curr);
				curr.clear(); // Java will clear the reference, so have to new an new ArrayList. 
				// completion of a level;
				if (!q.isEmpty())
					q.offer(null);
			}
		}
		return res;
    }
 }
 // 简易版
 void printLevelOrder() {
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);
    while (!queue.isEmpty()) 
    {

        /* poll() removes the present head.
        For more information on poll() visit 
        http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
        Node tempNode = queue.poll();
        System.out.print(tempNode.data + " ");

        /*Enqueue left child */
        if (tempNode.left != null) {
            queue.add(tempNode.left);
        }

        /*Enqueue right child */
        if (tempNode.right != null) {
            queue.add(tempNode.right);
        }
    }
}

8. given many linkedlists return a list that contain all the common element 
// example 
// 1->2->3;
// 2->3
// 2->3->4
// return 
// 2->3;
// HashMap count; 
public ListNode commonElement(List<ListNode> lists) {
	HashMap<ListNode, Integer> map = new HashMap<>();
	for(ListNode node : lists) {
		while(node != null) {
			if(map.containsKey(node)) map.put(node, map.get(node) + 1);
			else map.put(node, 1);
			node = node.next;
		}
	}
	ListNode res = new ListNode(0);
	for (Map.Entry<ListNode, Integer> entry : map.entrySet()) {
		if(((int)entry.getValue()) == lists.size()) {
			res.next = entry.getKey();
			res = res.next;
		}
	}
	return res.next;
}

9. max/min stack 
class MinStack {
    Stack<Integer> s = new Stack<Integer>();
    Stack<Integer> aux_s = new Stack<Integer>();
    
    public void push(int x) {
        s.push(x);
        if(aux_s.isEmpty() || aux_s.peek() >= x) aux_s.push(x);
    }

    public void pop() {
        int minTop = aux_s.peek();
        int sTop = s.peek();
        if(minTop == sTop) aux_s.pop();
        s.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return aux_s.peek();
    }
}

10. reverse singly linked list
public class ReverseSinglyList {
	public static ListNode reverseSinglyList(ListNode head) {
	    ListNode curr = head, prev = null, next = null;
	    while(curr != null) {
	        next = curr.next;
	        // swap process
	        curr.next = prev;
	        // the previous one become the new current
	        prev = curr;
	        // the new curr become the next
	        curr = next;
	    }
	    return prev;
	}
}

11. Island Perimeter
// Algorithm:
// 1. Loop over the matrix and count the number of islands;
// 2. If the current dot is an island, count if has any right neighbor or down neighbor
// 3. The result is islands * 4 - neighbors * 2
public class Solution {
	public int islandPerimeter(int[][] grid) {
		int islands = 0, neighbors = 0;

		for(int i = 0; i < grid.length; i++ {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 1) {
					islands++;
					if(i < grid.length - 1 && grid[i + 1][j] == 1) neighbors++; // count down neighbors
					if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
				}
			}
		}
		return islands * 4 - neighbours * 2;
	}
}


12. Serialize and deserialize Strings
public class GEncode {

	String serialize(List strings) throws UnsupportedEncodingException {
		String serialized = "";
		for (String s : strings) {
			serialized += URLEncoder.encode(s, "UTF-8") + "&";
		}
		return serialized;
	}

	@SuppressWarnings("deprecation")
	List deserialize(String serialized) {
		String[] strings = serialized.split("&");
		for (int i = 0; i < strings.length; i++) {
			strings[i] = URLDecoder.decode(strings[i]);
		}
		List list = Arrays.asList(strings);
		return list;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		List strings = new LinkedList();
		strings.add("Old & New");
		strings.add("Once upon a time!");
		strings.add("Yes, there is a monster in the closet");
		strings.add("Another string? Why, I don't mind if I do!");
		strings.add("She said \"Yes\"");

		for (String s : strings) {
			System.out.println (s);
		}

		GEncode g = new GEncode();
		String s = g.serialize(strings);

		System.out.println ("\nSerialized:\n" + s + "\n\nDeserialized:");

		List newStrings = g.deserialize(s);

		int i = 1;
		for (String ns : newStrings) {
			System.out.println (i + ": " + ns);
			i++;
		}
	}
}
// Output:

// Old & New
// Once upon a time!
// Yes, there is a monster in the closet
// Another string? Why, I don't mind if I do!
// She said "Yes"

// Serialized:
// Old+%26+New&Once+upon+a+time%21&Yes%2C+there+is+a+monster+in+the+closet&Another+string%3F+Why%2C+I+don%27t+mind+if+I+do%21&She+said+%22Yes%22&

// Deserialized:
// 1: Old & New
// 2: Once upon a time!
// 3: Yes, there is a monster in the closet
// 4: Another string? Why, I don't mind if I do!
// 5: She said "Yes"

Leetcode 271 encode and decode string
/**
 * Design an algorithm to encode a list of strings to a string. 
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 * Machine 1 (sender) has the function:
 *  string encode(vector<string> strs) {
 *     // ... your code
 *     return encoded_string;
 *   }
 * Machine 2 (receiver) has the function:
 *  vector<string> decode(string s) {
 *     //... your code
 *    return strs;
 *  }
 * So Machine 1 does:
 *  string encoded_string = encode(strs);
 * and Machine 2 does:
 *  vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * Implement the encode and decode methods.
 * Note:
 *  The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
 *  Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 *  Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */

public class Codec {
    // encode mechanism: "aaa", "/bbb/" --> 3/aaa5//bbb/
    // when decoding, the number will tell you how long your would like to go for the string length
    

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs) {
            sb.append(s.length()).append('/').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        while(i < s.length()) {
            // indexof returns the index within this string of the first occurrence of the specified charater or -1, if the charater does not occur
            int slash = s.indexOf('/', i);
            int size = Integer.valueOf(s.substring(i, slash));
            ret.add(s.substring(slash + 1, slash + size + 1));
            i = slash + size + 1;
        }
        return ret;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));


13. Intersection of Two Arrays I & II
I.
// 1. Use two hash sets
// Time complexity: O(n)
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }
}
// 2. Sort both arrays, use two pointers
// Time complexity: O(nlogn)
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
}
// 3. Binary search
// Time complexity: O(nlogn)
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }
    
    public boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}

II. Contains Duplicate and return with duplicate, too
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < nums1.length; i++)
        {
            if(map.containsKey(nums1[i])) map.put(nums1[i], map.get(nums1[i])+1);
            else map.put(nums1[i], 1);
        }
    
        for(int i = 0; i < nums2.length; i++)
        {
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0)
            {
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i])-1);
            }
        }
    
       int[] r = new int[result.size()];
       for(int i = 0; i < result.size(); i++)
       {
           r[i] = result.get(i);
       }
    
       return r;
    }
}

14. Best Time  to Sell and Buy Stocks I, II, III
/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
 
public class Solution{
    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices.length == 1) return 0;
        int max_profit = 0;
        int buy_date = 0;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] < prices[buy_date]) buy_date = i;
            int tmp_profit = prices[i] - prices[buy_date];
            if(tmp_profit > max_profit) max_profit = tmp_profit;   
        }
        return max_profit;
    }
}

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. 
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

public class Solution{
    public int maxProfit(int[] prices) {
        if(prices.length < 2) 
            return 0;
        int max = 0;
        for(int i = 1; i < prices.length; i++){
            int tmp_profit = prices[i] - prices[i-1];
            if(tmp_profit > 0)
                max += tmp_profit;
        }
        return max;
    }
}

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
 
public class Solution{
    public int maxProfitSolutionI(int[] prices) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] forward = new int[prices.length];
        int[] backward = new int[prices.length];
        for(int i = 0; i < prices.length; i++){
            // the first transaction profit
            if(prices[i] > min)
                forward[i] = Math.max(prices[i] - min, forward[i - 1]);
            else
                if(i > 0)
                    forward[i] = forward[i - 1];
            min = Math.min(prices[i], min);
            // the second transaction
            if(prices[prices.length - 1 - i] < max)
                backward[prices.length - 1 - i] = Math.max(max - prices[prices.length - 1 - i], backward[prices.length - i]);
            else
                if(i > 0)
                    backward[prices.length - 1 - i] = backward[prices.length - i];
            max = Math.max(prices[prices.length - 1 - i], max);
        }
        // find out the max profit combined 2 transactions
        int res = 0;
        for(int i = 0; i < prices.length; i++)
            res = Math.max(forward[i] + backward[i], res);
        return res;
    }
    
    public int maxProfitSolutionII(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far. 
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }
}

?????15. Next Greater Element I && II && III

16. LRU
/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */

public class LRUCache {
    private HashMap<Integer, LRUCache.DoubleLinkedListNode> map = new HashMap<Integer, LRUCache.DoubleLinkedListNode>();
    private DoubleLinkedListNode head;
    private DoubleLinkedListNode end;
    private int capacity;
    private int len;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.len = 0;
    }
    
    public int get(int key) {
        if(this.map.containsKey(key)){
            // move this referred frame to the front;
            removeNode(this.map.get(key));
            setHead(this.map.get(key));
            return this.map.get(key).val;
        }else{
            return -1;
        }
    }
    
    public void set(int key, int value) {
        if(this.map.containsKey(key)){
            // if the map contains this frame, set the value, move it to the front. 
            DoubleLinkedListNode curr = this.map.get(key);
            curr.val = value;
            removeNode(curr);
            setHead(curr);
        }else{
            // if the map doesn't contain this frame, insert it.
            DoubleLinkedListNode curr = new DoubleLinkedListNode(key, value);
            if(this.len < this.capacity){
                setHead(curr);
                this.map.put(key, curr);
                this.len++;
            }else{
                this.map.remove(this.end.key);
                this.end = this.end.pre;
                if(this.end != null){
                    this.end.post = null;
                }
                setHead(curr);
                this.map.put(key, curr);
            }
        }
    }
    
    private class DoubleLinkedListNode {
        public int val;
        public int key;
        public DoubleLinkedListNode pre;
        public DoubleLinkedListNode post;
        // constuctor
        public DoubleLinkedListNode(int key, int value){
            this.val = value;
            this.key = key;
        }
    }
    
    private void setHead(DoubleLinkedListNode node){
        node.post = this.head;
        node.pre = null;
        if(this.head != null){
            this.head.pre = node;
        }
        this.head = node;
        if(this.end == null){
            this.end = node;
        }
    }
    
    private void removeNode(DoubleLinkedListNode node){
        DoubleLinkedListNode curr = node;
        DoubleLinkedListNode pre = curr.pre;
        DoubleLinkedListNode post = curr.post;
        if(pre != null){
            pre.post = post;
        }else{
            this.head = post;
        }
        if(post != null){
            post.pre = pre;
        }else{
            this.end = pre;
        }
    }
}

17. Move Zeroes
/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * 1. You must do this in-place without making a copy of the array.
 * 2. Minimize the total number of operations.
 */

public class Solution {
    public void moveZeroes(int[] nums) {
       int count = 0;
       for(int i = 0; i < nums.length; i++) {
           if(nums[i] != 0) {
               nums[count++] = nums[i];
           }
       }
       while(count < nums.length) nums[count++] = 0;
    }
}

?????18. Leetcode 544: Output Contest Matches

?????19. Word Break II

20. 2 Sum
I.
public class Solution{
    public int[] twoSum(int[] numbers, int target) {
        if(numbers.length < 2) return null;
        int[] res = new int[2];
        // HashMap<value, index>;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i++){
            if(map.containsKey(numbers[i])){
                res[0] = map.get(numbers[i]) + 1;
                res[1] = i + 1;           
            }else{
                map.put(target - numbers[i], i);
            }
        }
        return res;
    }
}

II. Array is sorted
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int L = 0, R = numbers.length - 1;
        int[] res = new int[2];
        while(L < R) {
            int sum = numbers[L] + numbers[R];
            if(sum == target) {
                res[0] = L + 1;
                res[1] = R + 1;
                break;
            } else if(sum < target) {
                L++;
            } else {
                R--;
            }
        }
        return res;
    }
}

III. Design
public class TwoSum {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    // Add the number to an internal data structure.
	public void add(int number) {
	    if(map.containsKey(number)) {
	        int count = map.get(number);
	        count++;
	        map.put(number, count);
	    } else {
	        map.put(number, 1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    Set<Integer> keys = map.keySet();
	    for(int key : keys) {
	        if(keys.contains(value - key)) {
	            if(value == key * 2 && map.get(key) < 2) continue;
	            return true;
	        }
	    }
	    return false;
	}
}

21. BST second largest node

22. Number of islands
public class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    merge(grid, i, j);
                }
            }
        }
        return count;
    }
    
    public void merge(char[][] grid, int i, int j) {
        // validity checking
        if(i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1)
            return;
        // if already visited or water
        if(grid[i][j] != '1') return;
        // set as visited
        grid[i][j] = '0';
        
        // merge the adjacent area
        merge(grid, i + 1, j);
        merge(grid, i - 1, j);
        merge(grid, i, j + 1);
        merge(grid, i, j - 1);
    }
}
BFS Solution:
class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int n = grid.length;
        int m = grid[0].length;
        int islands = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    markByBFS(grid, i, j);
                    islands++;
                }
            }
        }
        
        return islands;
    }
    
    private void markByBFS(boolean[][] grid, int x, int y) {
        // magic numbers!
        int[] directionX = {0, 1, -1, 0};
        int[] directionY = {1, 0, 0, -1};
        
        Queue<Coordinate> queue = new LinkedList<>();
        
        queue.offer(new Coordinate(x, y));
        grid[x][y] = false;
        
        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate adj = new Coordinate(
                    coor.x + directionX[i],
                    coor.y + directionY[i]
                );
                if (!inBound(adj, grid)) {
                    continue;
                }
                if (grid[adj.x][adj.y]) {
                    grid[adj.x][adj.y] = false;
                    queue.offer(adj);
                }
            }
        }
    }
    
    private boolean inBound(Coordinate coor, boolean[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        return coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m;
    }
}

?????23. Zigzag print a matrix:





















