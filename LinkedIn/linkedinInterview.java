5 rounds
1. Given a BST, find the closest k node in the tree. closest means node value.
2. Given number array and k, see if array could be divided to k buckets which all have same sum.  Double sqrt(x).
3. Manager round..talking about experiences
4. Design Hangman
5. Tech talk


Leetcode related questions:
1. Two Sum
public int[] twoSum(int[] nums, int target) {
	int[] res = new int[2];
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	for(int i = 0; i < nums.length; i++) {
		if(map.containsKey(target - nums[i])) {
			res[0] = map.get(target - nums[i]);
			res[1] = i;
		} else {
			map.put(target - nums[i], i);
		}
	}
	return res;
}

8. String to Integer (atoi)
public int myAtoi(String str) {
    int index = 0, sign = 1, total = 0;
    //1. Empty string
    if(str.length() == 0) return 0;

    //2. Remove Spaces
    while(str.charAt(index) == ' ' && index < str.length())
        index ++;

    //3. Handle signs
    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
        sign = str.charAt(index) == '+' ? 1 : -1;
        index ++;
    }
    
    //4. Convert number and avoid overflow
    while(index < str.length()){
        int digit = str.charAt(index) - '0';
        if(digit < 0 || digit > 9) break;

        //check if total will be overflow after 10 times and add digit
        if(Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        total = 10 * total + digit;
        index ++;
    }
    return total * sign;
}

12. Integer to Roman
Range from 1-3999
public String intToRoman(int num) {
	String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    
}
13. Roman to Integer
public int romanToInt(String s) {
    HashMap<Character, Integer> dic = new HashMap<Character, Integer>();
    dic.put('I', 1);
    dic.put('V', 5);
    dic.put('X', 10);
    dic.put('L', 50);
    dic.put('C', 100);
    dic.put('D', 500);
    dic.put('M', 1000);
    //start from the rightmost one. 
    int res = dic.get(s.charAt(s.length() - 1));
    for(int i = s.length() - 2; i >= 0; i--){
        if(dic.get(s.charAt(i + 1)) <= dic.get(s.charAt(i)))
            res += dic.get(s.charAt(i));
        else
            res -= dic.get(s.charAt(i));
    }
    return res;
}
20. Valid Parentheses
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
21. Merge Two Sorted Lists

23. Merge k Sorted Lists

31. Next Permutation

33. Search in Rotated Sorted Array
34. Search for a Range
39. Combination Sum
40. Combination Sum II
46. Permutations
47. Permutations II
50. Pow(x, n)
53. Maximum Subarray
56. Merge Intervals
57. Insert Interval
60. Permutation Sequence
62. Unique Paths
63. Unique Paths II
65. Valid Number
68. Text Justification
70. Climbing Stairs
72. Edit Distance
74. Search a 2D Matrix
75. Sort Colors
76. Minimum Window Substring
98. Validate Binary Search Tree
100. Same Tree
101. Symmetric Tree
102. Binary Tree Level Order Traversal
103. Binary Tree Zigzag Level Order Traversal
104. Maximum Depth of Binary Tree
109. Convert Sorted List to Binary Search Tree
126. Word Ladder II
127. Word Ladder
135. Candy
136. Single Number
137. Single Number II
139. Word Break
140. Word Break II
149. Max Points on a Line
150. Evaluate Reverse Polish Notation
151. Reverse Words in a String
152. Maximum Product Subarray
155. Min Stack
156. Binary Tree Upside Down
160. Intersection of Two Linked Lists
169. Majority Element
170. Two Sum III - Data structure design
173. Binary Search Tree Iterator
186. Reverse Words in a String II
187. Repeated DNA Sequences
189. Rotate Array
198. House Robber
200. Number of Islands
205. Isomorphic Strings
210. Course Schedule II
213. House Robber II
215. Kth Largest Element in an Array
216. Combination Sum III
226. Invert Binary Tree
232. Implement Queue using Stacks
236. Lowest Common Ancestor of a Binary Tree
238. Product of Array Except Self
240. Search a 2D Matrix II
243. Shortest Word Distance
244. Shortest Word Distance II
245. Shortest Word Distance III
254. Factor Combinations
256. Paint House
260. Single Number III
261. Graph Valid Tree
265. Paint House II
277. Find the Celebrity
295. Find Median from Data Stream
297. Serialize and Deserialize Binary Tree
305. Number of Islands II
311. Sparse Matrix Multiplication
319. Bulb Switcher
337. House Robber III
339. Nested List Weight Sum
341. Flatten Nested List Iterator
347. Top K Frequent Elements
349. Intersection of Two Arrays
350. Intersection of Two Arrays II
364. Nested List Weight Sum II
366. Find Leaves of Binary Tree
367. Valid Perfect Square
377. Combination Sum IV
380. Insert Delete GetRandom O(1)
381. Insert Delete GetRandom O(1) - Duplicates allowed
409. Longest Palindrome
438. Find All Anagrams in a String
464. Can I Win

Not leetcode ones:
1. Tournament tree
2. Triangle count
3.BST insert/delete/find
4.Convex hull
5.single valid tree
6.四向鍊錶打平
7. Max stack all O(1), Mid stack
8.N nearest points
9. Max palindrome sequence.