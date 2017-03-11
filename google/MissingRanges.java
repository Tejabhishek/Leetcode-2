public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        for(int n : nums) {
        	if(n == Integer.MIN_VALUE) {
        		lower = n + 1;
        		continue;
        	}
        	if(lower == n - 1) {
        		// single one missing
        		res.add("" + lower);
        	} else if(lower < n - 1) {
        		res.add(lower + "->" + (n - 1))
        	}
        	if(n == Integer.MAX_VALUE) return res;
        	lower = n + 1;
        }
        if(lower == upper) res.add("" + lower) // already move to the last one
        else if(lower < upper) res.add(lower + "->" + upper);
        return res;
    }
}