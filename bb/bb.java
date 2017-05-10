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

15. Next Greater Element I && II && III
Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

I.
用一个单调栈来存取之前的值，遇到比栈顶元素大的当前元素，pop栈顶元素与当前元素存成pair放到map里面。
public int[] nextGreaterElement(int[] findNums, int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();
    for(int i : nums) {
        while(!stack.isEmpty() && stack.peek() < i) {
            map.put(stack.pop(), i);
        }
        stack.push(i);
    }
    for(int i = 0; i < findNums.length; i++) {
        findNums[i] = map.getOrDefault(findNums[i], -1);
    }
    return findNums;
}

II. the array is a circular array
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number;
The second 1 next greater number needs to search circularly, which is also 2.
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index only
        for(int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while(!stack.isEmpty() && nums[stack.peek()] < num) {
                next[stack.pop()] = num;
            }
            if(i < n) stack.push(i);
        }
        return next;
    }
}

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


?????18. Word Break II

19. Two Sum
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

20. BST second largest node

21. Number of islands
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

22. Zigzag print a matrix:

23. Sqrt(x)
public class Solution {
    public int sqrt(int x) {
        if(x < 0) 
            return -1;
        if(x == 0)
            return 0;
        double y = ((double) x) / 2;
        // y * y - x > 0.00001 ==> y * y != x
        while(Math.abs(y * y - x) > 0.00001){
            y = (y + x / y) / 2;
        }
        return (int) y;
    }
}

24. Single Number
public class Solution{
    public int singleNumber(int[] A){
    int num = A[0];
    for(int i = 1; i < A.length; i++){
        num ^= A[i];
    }
    return num;
  }
}

25. 给一个list， 有两个数，一直加数字进去知道没有可能性，要保证：
    a. list所有数均大于零
    b. list中所有书都必须为unique
    c. 新加入的数必须为已存在list中的某两数的差
example: Given [30,5]
Output: [30,5,25,20,15,10]

26. Trie
/**
 * Implement a trie with insert, search, and startsWith methods.
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */

class TrieNode {
    // Initialize your data structure here.
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
    public TrieNode() {
        
    }
    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode t;
            if(children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }
            children = t.children;
            // set leaf node
            if(i == word.length() - 1) t.isLeaf = true;
        }
        
     }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
        if(t != null && t.isLeaf)  return true;
        else return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null) return false;
        else return true;
    }
    
    public TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else return null;
        }
        return t;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");

27. String Compression: aaabbbccccbaddd --> a3b2c4bad3

28. Candy
/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 *      Each child must have at least one candy.
 *     Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
 
public class Solution {
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        for(int i = 0;i < candy.length;i++)
            candy[i] = 1;
        for (int i = 1; i < candy.length; i++)
            if (ratings[i] > ratings[i - 1])
                candy[i] = candy[i - 1] + 1;
        for (int i = candy.length - 2; i >= 0; i--)
            if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1])
                candy[i] = candy[i + 1] + 1;
        int sum = 0;
        for (int i : candy)
            sum += i;
        return sum;
    }
}

29. Populating Next Right Pointers in Each Node
I. 
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    
    Initially, all next pointers are set to NULL.
    
    Note:
    
    You may only use constant extra space.
    You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
    For example,
    Given the following perfect binary tree,
             1
           /  \
          2    3
         / \  / \
        4  5  6  7
    After calling your function, the tree should look like:
             1 -> NULL
           /  \
          2 -> 3 -> NULL
         / \  / \
        4->5->6->7 -> NULL

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
 public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode leftwall = root;
        while(leftwall != null){
            TreeLinkNode across = leftwall;
            while(across != null){
                if(across.left != null)
                    across.left.next = across.right;
                if(across.right != null && across.next != null)
                    across.right.next = across.next.left;
                across = across.next;
            }
            leftwall = leftwall.left;
        }       
    }
 }
II.
 Follow up for problem "Populating Next Right Pointers in Each Node".

    What if the given tree could be any binary tree? Would your previous solution still work?
    
    Note:
    
    You may only use constant extra space.
    For example,
    Given the following binary tree,
             1
           /  \
          2    3
         / \    \
        4   5    7
    After calling your function, the tree should look like:
             1 -> NULL
           /  \
          2 -> 3 -> NULL
         / \    \
        4-> 5 -> 7 -> NULL

public class Solution{
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        TreeLinkNode temp = null;
        if(root == null) 
            return;
        queue.offer(root);
        queue.offer(null); // as a marker to mark the level
        while(!queue.isEmpty()){
            temp = queue.poll();
            if(temp != null){
                // means not the end of a level
                temp.next = queue.peek();
                if(temp.left != null)  
                    queue.offer(temp.left);
                if(temp.right != null)
                    queue.offer(temp.right);
            }else{
                // completion of current level
                if(!queue.isEmpty())
                    queue.offer(null);
            }    
        }
    }
}


30. Copy the linkedlist with random pointer
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        // loop 1. Copy all the nodes
        RandomListNode node = head;
        while(node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while(node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
        }
        return map.get(head);
    }
}
with O(1) space solution:

public RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode iter = head, next;
    // First round: make copy of each node,
    // and link them together side-by-side in a single list
    while(iter != null) {
        next = iter.next;
        RandomListNode copy = new RandomListNode(iter.label);
        iter.next = copy;
        copy.next = next;
        iter = next;
    }
    // Second round: assign random pointers for the copy nodes.
    iter = head;
    while(iter != null) {
        if(iter.random != null) {
            iter.next.random = iter.random.next;
        }
        iter = iter.next.next;
    }
    // Third round: restore the original list, and extract the copy list.
    iter = head;
    RandomListNode dummy = new RandomListNode(0);
    RandomListNode copy, copyIter = dummy;
    while(iter != null) {
        next = iter.next.next;
        // extract the copy
        copy = iter.next;
        copyIter.next = copy;
        copyIter = copy;

        // restore the original list
        iter.next = next;
        iter = next;
    }
    return dummy.next;
}


31. Given a 2D Matrix, m * n size, Given a start point, and K steps. Return Maximum Path Sum
public int MaximumPathSum(int[][] board, int x, int y, int k) {
    int res = 0;
    return res;
}

private void helper(int[][] board, int x, int y, int k, int res, int path) {

}

32. phone combination + word break
public class Solution {
    static Map<Character, String> letterMap = new HashMap<Character, String>();
    static{
        letterMap.put('0', "");
        letterMap.put('1', "");
        letterMap.put('2', "abc");
        letterMap.put('3', "def");
        letterMap.put('4', "ghi");
        letterMap.put('5', "jkl");
        letterMap.put('6', "mno");
        letterMap.put('7', "pqrs");
        letterMap.put('8', "tuv");
        letterMap.put('9', "wxyz");
    }
    
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return res;
        char[] cs = new char[digits.length()];
        appendDigits(digits, 0, cs, res);
        return res;
    }
    
    private void appendDigits(String digits, int i, char[] cs, ArrayList<String> res){
        if(i == digits.length()){
            res.add(new String(cs));
            return;
        }
        String letters = letterMap.get(digits.charAt(i));
        for(int j = 0; j < letters.length(); j++){
            cs[i] = letters.charAt(j);
            appendDigits(digits, i + 1, cs, res);
        }
    }
}

33.trapping water
Use Stack Solution:
public class Solution{
    public int trap(int[] A) { 
        // skip zeros  
        int cur = 0;  
        while (cur < A.length && A[cur] == 0) 
            ++cur;  
       
        // check each one  
        int vol = 0;  
        Stack<Integer> stack = new Stack<Integer>();  
        while (cur < A.length) {  
            while (!stack.isEmpty() && A[cur] >= A[stack.peek()]) {  
               int b = stack.pop();  
               if (stack.isEmpty()) 
                break;  
               vol += ((cur - stack.peek() - 1) * (Math.min(A[cur], A[stack.peek()]) - A[b]));  
            }  
            stack.push(cur);  
            ++cur;  
        }  
       
       return vol;  
    }  
}

Regular Solution:
/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class Solution {
    public int trap(int[] A) {
        int res = 0;
        if(A.length < 2)
            return res;
        int[] leftMost = new int[A.length];
        int[] rightMost = new int[A.length];
        // Initialization;
        leftMost[0] = 0;
        rightMost[A.length - 1] = 0;
        for(int i = 1; i < A.length - 1; i++){
            leftMost[i] = Math.max(leftMost[i - 1], A[i - 1]);
        }
        for(int i = A.length - 2; i >= 0; i--){
            rightMost[i] = Math.max(rightMost[i + 1], A[i + 1]);
        }
        for(int i = 0; i < A.length; i++){
            if(Math.min(leftMost[i], rightMost[i]) - A[i] > 0){
                res += Math.min(leftMost[i], rightMost[i]) - A[i];
            }
        }
        return res;
    }
}

34. Race Condition in Distributed System

35. predict the winner

36, given 6-digit number, same sum for left 3 digit and right 3 digit, find all the combinations

37. Given a String, and int k, reverse the first k characters every 2k characters, and the rest remains the same;
    if the rest of characters is smaller than k, reverse them.


38. Path Sum II
/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 *            5
 *           / \
 *          4   8
 *         /   / \
 *        11  13  4
 *       /  \    / \
 *      7    2  5   1
 * return
 * [
 *    [5,4,11,2],
 *     [5,8,4,5]
 *  ]
 */
 
 public class Solution {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root != null){
            ArrayList<Integer> path = new ArrayList<Integer>();
            pathSumHelper(root, sum, res, path);
        }
        return res;
    }
    
    public void pathSumHelper(TreeNode root, int sum, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path){
        if(root == null)
            return;
        if(root.left == null && root.right == null && root.val == sum){
            // path found.
            ArrayList<Integer> apath = new ArrayList<Integer>(path);
            apath.add(root.val);
            res.add(apath);
        }else{
            // walk into next level
            path.add(root.val);
            if(root.left != null)
                pathSumHelper(root.left, sum - root.val, res, path);
            if(root.right != null)
                pathSumHelper(root.right, sum - root.val, res, path);
            // recover the global variable. backtracking. 
            path.remove(path.size() - 1);
        }
    }
}

39. Find Peak or valley element
public static int FindValleyOrPeak(int[] nums) {
    int start = 0, end = nums.length - 1;
    boolean isUp = (nums[0] + 1 == nums[1]);
    while(start + 1 < end) {
        int mid = start + (end - start) / 2;
        if(isUp) {
            if(nums[mid] < nums[mid + 1])  start = mid;
            else end = mid;
        } else {
            if(nums[mid] > nums[mid + 1]) start = mid;
            else end = mid;
        }
    }
    if(isUp) return nums[start] > nums[end] ? start : end;
    else return nums[start] < nums[end] ? start : end;
}

40. Reorder List
public static void reorderList(ListNode head) {
    if (head == null || head.next == null)
        return;
    ListNode fast = head;
    ListNode late = head;
    while (fast.next != null && fast.next.next != null) {
        fast = fast.next.next;
        late = late.next;
    }
    ListNode ret = new ListNode(0);
    ListNode cur = ret;
    ListNode leftHalf = head;
    ListNode rightHalf;
    if (fast.next != null) {
        rightHalf = reverseList(late.next);
        late.next = null;
    } else {
        rightHalf = reverseList(late);
        ListNode tmp = head;
        while (tmp.next != late) {
            tmp = tmp.next;
        }
        tmp.next = null;
    }
    leftHalf = head;
    while (leftHalf != null && rightHalf != null) {
        cur.next = leftHalf;
        leftHalf = leftHalf.next;
        cur = cur.next;
        cur.next = rightHalf;
        rightHalf = rightHalf.next;
        cur = cur.next;
    }
    if (leftHalf != null) {
        cur.next = leftHalf;
    } else if (rightHalf != null) {
        cur.next = rightHalf;
    }
    head = ret.next;
}

private static ListNode reverseLinkedList(ListNode head) {
    ListNode curr = null, next = null;
    while (head != null) {
        next = head.next;
        head.next = curr;
        curr = head;
        head = next;
    }
    return curr;
}  

41. Josephus problem

42. LCA 
public class LCA {
    public TreeNode BT_LCA(TreeNode root, TreeNode a, TreeNode b) {
        TreeNode left, right;
        if (root == null)
            return root;
        if (root == a || root == b)
            return root;
        
        // divide
        left = BT_LCA(root.left, a, b);
        right = BT_LCA(root.right, a, b);
        
        // conquer
        if (left != null && right != null)
            return root;// nodes are each on a separate branch
        else
            return (left != null ? left : right);
        // either one node is on one branch,
        // or none was found in any of the branches
        
    }

    public TreeNode BST_LCA(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null)
            return root;
        if (root == a || root == b)
            return root;
        if (Math.max(a.val, b.val) < root.val) // a.val < root.val && b.val < root.val, on the left subtree
            return BST_LCA(root.left, a, b);
        else if (Math.min(a.val, b.val) > root.val) // a.val > root.val && b.val > root.val, on the right subtree
            return BST_LCA(root.right, a, b);
        else
            return root;
    }
    
    // first common ancestor with parent pointer. What if the parent pointer is not available
    
    public TreeNode getLCAWithParent(TreeNode a, TreeNode b) {
        int la = 0, lb = 0;
        TreeNode curr = a; 
        while(curr != null) {
            curr = curr.parent;
            la++;
        }
        curr = b;
        while(curr != null) {
            curr = curr.parent;
            lb++;
        }
        if(la < lb) {
            int delta = lb - la;
            for(int i = 0; i < delta; i++) {
                a = a.parent;
            }
        } else if(lb < la) {
            int delta = la - lb;
            for(int i = 0; i < delta; i++) {
                b = b.parent;
            }
        }
        while(a != null && b != null) {
            if(a == b) return a;
            a = a.parent;
            b = b.parent;
        }
        return null;
    }
}

43. Unique Paths 
/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Note: m and n will be at most 100.
 */
 
public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        // Initialization
        for(int i = 0; i < m; i++){
            paths[i][0] = 1;
        }
        for(int i = 0; i < n; i++){
            paths[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m - 1][n - 1];
    }
}

44. Evaluate Reverse Polish Notation
/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 *  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
 
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        for(String token : tokens){
            if(token.equals("+")){
                int op1 = s.pop();
                int op2 = s.pop();
                int res = op1+op2;
                s.push(res);
            }else if(token.equals("-")){
                int op1 = s.pop();
                int op2 = s.pop();
                int res = op2-op1;
                s.push(res);
            }else if(token.equals("*")){
                int op1 = s.pop();
                int op2 = s.pop();
                int res = op1 * op2;
                s.push(res);
            }else if(token.equals("/")){
                int op1 = s.pop();
                int op2 = s.pop();
                int res = op2 / op1;
                s.push(res);
            }else{
                s.push(Integer.parseInt(token));
            }
        }
        return s.pop();
    }
}

45. Leetcode-295 Find Median from Data Stream
Find Median from a very large set of integer

46. Valid parentheses
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<Character>();
    for (char c : s.toCharArray()) {
        if (c == '(')
            stack.push(')');
        else if (c == '{')
            stack.push('}');
        else if (c == '[')
            stack.push(']');
        else if (stack.isEmpty() || stack.pop() != c)
            return false;
    }
    return stack.isEmpty();
}

47.Multiply two integers without using multiplication operators
int multiply(int multiplicand, int factor)
{
    if (factor == 0) return 0;

    int product = multiplicand;
    for (int ii = 1; ii < abs(factor); ++ii) {
        product += multiplicand;
    }

    return factor >= 0 ? product : -product;
}

Think about how you multiply in decimal using pencil and paper:

  12
x 26
----
  72
 24
----
 312
What does multiplication look like in binary?

   0111
x  0101
-------
   0111
  0000
 0111
-------
 100011

 // A method to multiply two numbers using Russian Peasant method
public int russianPeasant(int a, int b)
{
    int res = 0;  // initialize result
 
    // While second number doesn't become 1
    while (b > 0)
    {
         // If second number becomes odd, add the first number to result
         if (b & 1)
             res = res + a;
 
         // Double the first number and halve the second number
         a = a << 1;
         b = b >> 1;
     }
     return res;
}

48. Jump Game
/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
 I.
public class Solution {
    public boolean canJump(int[] A) {
        int maxCover = 0;
        for(int start = 0; start <= maxCover && start < A.length; start++){
            maxCover = Math.max(A[start] + start, maxCover);
            if(maxCover >= A.length - 1)
                return true;
        }
        return false;
    }
    
    public boolean canJumpDP(int[] A) {
      boolean[] can = new boolean[A.length];
      // Initialization
      can[0] = true;
        
      for (int i = 1; i < A.length; i++) {
          for (int j = 0; j < i; j++) {
              if (can[j] && j + A[j] >= i) {
                  can[i] = true;
                  break;
              }
          }
      }
        
      return can[A.length - 1];
    }
}
II.
/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
// version 1: Dynamic Programming
public class Solution {
    public int jump(int[] A) {
        int[] steps = new int[A.length];
        
        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }
        
        return steps[A.length - 1];
    }
}


// version 2: Greedy
public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0, end = 0, jumps = 0;
        while (end < A.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }
}

49. Symmetric Tree
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null) 
            return true;
        if(left == null || right == null) 
            return false;
        if(left.val != right.val) 
            return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
   }
}

50.LFU
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Solution: 
Two HashMaps are used, one to store <key, value> pair, another store the <key, node>.
I use double linked list to keep the frequent of each key. In each double linked list node, keys with the same count are saved using java built in LinkedHashSet. This can keep the order.
Every time, one key is referenced, first find the current node corresponding to the key, If the following node exist and the frequent is larger by one, add key to the keys of the following node, else create a new node and add it following the current node.
All operations are guaranteed to be O(1).

public class LFUCache {
    private Node head = null;
    private int cap = 0;
    private HashMap<Integer, Integer> valueHash = null;
    private HashMap<Integer, Node> nodeHash = null;
    
    public LFUCache(int capacity) {
        this.cap = capacity;
        valueHash = new HashMap<Integer, Integer>();
        nodeHash = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        if (valueHash.containsKey(key)) {
            increaseCount(key);
            return valueHash.get(key);
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if ( cap == 0 ) return;
        if (valueHash.containsKey(key)) {
            valueHash.put(key, value);
        } else {
            if (valueHash.size() < cap) {
                valueHash.put(key, value);
            } else {
                removeOld();
                valueHash.put(key, value);
            }
            addToHead(key);
        }
        increaseCount(key);
    }
    
    private void addToHead(int key) {
        if (head == null) {
            head = new Node(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            Node node = new Node(0);
            node.keys.add(key);
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            head.keys.add(key);
        }
        nodeHash.put(key, head);      
    }
    
    private void increaseCount(int key) {
        Node node = nodeHash.get(key);
        node.keys.remove(key);
        
        if (node.next == null) {
            node.next = new Node(node.count+1);
            node.next.prev = node;
            node.next.keys.add(key);
        } else if (node.next.count == node.count+1) {
            node.next.keys.add(key);
        } else {
            Node tmp = new Node(node.count+1);
            tmp.keys.add(key);
            tmp.prev = node;
            tmp.next = node.next;
            node.next.prev = tmp;
            node.next = tmp;
        }

        nodeHash.put(key, node.next);
        if (node.keys.size() == 0) remove(node);
    }
    
    private void removeOld() {
        if (head == null) return;
        int old = 0;
        for (int n: head.keys) {
            old = n;
            break;
        }
        head.keys.remove(old);
        if (head.keys.size() == 0) remove(head);
        nodeHash.remove(old);
        valueHash.remove(old);
    }
    
    private void remove(Node node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        } 
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }
    
    class Node {
        public int count = 0;
        public LinkedHashSet<Integer> keys = null;
        public Node prev = null, next = null;
        
        public Node(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }
}

51. Find First Missing Positive
/**
 * Given an unsorted integer array, find the first missing positive integer.
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 */
 
public class Solution {
    public int firstMissingPositive(int[] A) {
        if(A.length == 0)
            return 1;
        for(int i = 0; i < A.length; i++){
            if(A[i] > 0 && A[i] - 1 < A.length && A[i] - 1 != i && A[i] != A[A[i] - 1]) {
                int t = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = t;
                i--;
            }
        }
        for(int j = 0; j < A.length; j++){
            if(A[j] - 1 != j)
                return j + 1;
        }
        return A.length + 1;
    }
    
}

52. Given n people and a relationship among them. like <a,b,c,d>, <c,a,b>,<e,f>,<g>... return how many groups are there?

53. leetcode-287 find duplicate numbers
/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
 * Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * 1. You must not modify the array (assume the array is read only).
 * 2. You must use only constant, O(1) extra space.
 * 3. Your runtime complexity should be less than O(n^2).
 * 4. There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class Solution {
    /** Solution: 
     * These numbers constitute a linked list and the value of the node (a array cell) is the index of the next node, and there must be a cycle. 
     * Therefore, we use the classical "fast and slow pointers". 
     * http://keithschwarz.com/interesting/code/?dir=find-duplicate
     */
    public int findDuplicate(int[] nums) {
        int fast, slow; 
        fast = slow = nums[0];
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);
        slow = nums[0];
        while(fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }
}

Binary Search Solution:
public int findDuplicate(int[] nums) {
    if( nums == null || nums.length <2){ //No duplicate if length is 1
        return 0;
    }

    int low=1, mid, high = nums.length - 1;  //values range from [1..n-1]
    int countLessThanMid = 0;
    while (low < high) {
        //Recalculate the mid based on half range reduced [lower', high']
        mid = low + (high - low)/2;

        //Count how many numbers less than mid
        for(int x: nums) {
            //Valid data checking
            if(x >= nums.length || x < 1) {
                return x;
            }

            if(x <=mid) countLessThanMid++;
        }

        if(countLessThanMid > mid) {
            //duplicate occurs in [lower, mid]
            high=mid;
        } else {
            //dupicate occurs in [mid+1, high]
            low=mid+1;
        }
        countLessThanMid = 0;
    }

    return low;
}

54. rand(x)
Solution 1: randomNum = minimum + (int)(Math.random() * maximum); 

from rand(5) to rand(7):
int rand7() {
    int value = rand5()
              + rand5() * 2
              + rand5() * 3
              + rand5() * 4
              + rand5() * 5
              + rand5() * 6;
    return value%7;
}

55. Combination Sums
/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 *      All numbers (including target) will be positive integers.
 *      Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 *      The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7, 
 * A solution set is: 
 * [7] 
 * [2, 2, 3] 
 */
 
public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(candidates);
        combinationSumHelper(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }
    
    public void combinationSumHelper(int[] candidates, int start, int target, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res){
        if(target < 0 || start < 0 || start >= candidates.length)
            return;
        if(target == 0) {
            ArrayList<Integer> result = new ArrayList<Integer>(path);
            res.add(result);
        }else {
            for(int i = start; i < candidates.length && candidates[i] <= target; i++){
                path.add(candidates[i]);
                combinationSumHelper(candidates, i, target - candidates[i], path, res);
                path.remove(path.size() - 1); //reset the variable.
            }
        }
    }
}

56. isBST
public class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isBSTUtil(TreeNode root, int min, int max) {
        if (root == null)
            return true;
        return (root.val > min && root.val < max && isBSTUtil(root.left, min, root.val) && isBSTUtil(root.right, root.val, max));
    }
}

57. Maximal Square 
DP Solution:

58. Pow(x, n)
/**
 * Implement pow(x, n).
 */
public class Solution {
    public double pow(double x, int n) {
        if(n == 0)
            return 1.0;
        double half = pow(x, n / 2);
        if(n % 2 == 0)
            return half * half;
        else if(n > 0)
            return half * half * x;
        else return half * half / x;
    }
}

59. Leetcode-328: Odd Even LinkedList
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
        
            ListNode odd = head, even = head.next, evenHead = even; 
        
            while (even != null && even.next != null) {
                odd.next = odd.next.next; 
                even.next = even.next.next; 
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead; 
        }
        return head;
    }
}

60. First Unique Character in a String
public class Solution {
    public int firstUniqChar(String s) {
        int index = 0;
        int[] counts = new int[26];
        for(char c: s.toCharArray()) {
            counts[c - 'a']++;
        }
        for(int i = 0; i < s.length(); i++) {
            if(counts[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}

61. Course Schedule II
public int[] findOrder(int numCourses, int[][] prerequisites) {
    if(numCourses == 0) return 0;
    // Covert graph presentation from edges from indegree of adjacent list
    // indegree means how many edges are pointed to the vertex
    // outdegree means how many edges current vertex are pointing out
    int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
    for(int i = 0; i < prerequisites.length; i++) {
        //Indegree - how many prerequisites are needed.
        indegree[presentation[i][0]]++;
    }
    Queue<Integer> queue = new LinkedList<Integer>();
    for(int i = 0; i < numCourses; i++) {
        if(indegree[i] == 0) {
            // Add the course to the order because it has no prerequisites
            order[index++] = i;
            queue.offer(i);
        }
    }
    // How many courses don't need prerequisites
    while(!queue.isEmpty()) {
        int prerequisite = queue.poll(); // Already finished this prerequisite course
        for(int i = 0; i < prerequisites.length; i++) {
            if(prerequisites[i][1] == prerequisite) {
                indegree[prerequisites[i][0]]--;
                if(indegree[prerequisites[i][0]] == 0) {
                    // If indegree is zero, then add the course to the order
                    order[index++] = prerequisites[i][0];
                    queue.offer(prerequisites[i][0]);
                }
            }
        }
    }
    return (index == numCourses) ? order: new int[0];
}

62. Implement Queue with Stacks
/**
 * Implement the following operations of a queue using stacks.
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Notes:
 * 1. You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * 2. Depending on your language, stack may not be supported natively. 
 *    You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 * 3. You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */

class MyQueue {
    Stack<Integer> stack = new Stack<Integer>();
    // Push element x to the back of queue.
    public void push(int x) {
        Stack<Integer> rev = new Stack<Integer>();
        while(!stack.empty()) {
            rev.push(stack.pop());
        }
        rev.push(x);
        while(!rev.empty()) {
            stack.push(rev.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        stack.pop();
    }

    // Get the front element.
    public int peek() {
        return stack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.empty();
    }
}









