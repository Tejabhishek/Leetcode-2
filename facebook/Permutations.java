public class Solution {
    public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> res = new ArrayList<List<Integer>>();
       if(nums == null || nums.length == 0) return res;
	   helper(res, new ArrayList<Integer>(), nums);
       return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> path, int[] nums){
    	if(path.size() == nums.length) {
    		res.add(new ArrayList<Integer>(path));
    		return;
    	}
    	for(int i = 0; i < nums.length; i++) {
    		if(path.contains(nums[i])) continue;
    		path.add(nums[i]);
    		helper(res, path, nums);
    		path.remove(path.size() - 1);
    	}
    }
}