public class Solution {
	//time: O(N), space(N);
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
        	if(!map.containsKey(nums[i])) {
        		map.put(target - nums[i], i);
        	} else {
        		res[0] = i;
        		res[1] = map.get(nums[i]);
        	}
        }
        return res;
    }
}