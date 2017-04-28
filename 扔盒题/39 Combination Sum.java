public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
		if(candidates == null) return res;
		backtrack(res, new ArrayList<>(), candidates, target, 0);

		return res;
	}

	private void backtrack(List<List<Integer>> res, List<Integer> tmpList, int[] candidates, int target, int start){
		if(target < 0) return;
		else if(target == 0) // We have found a solution
			res.add(new ArrayList<>(tmpList));
			
		else{
			for(int i = start; i < candidates.length; i++){
				tmpList.add(candidates[i]);
				backtrack(res, tmpList, candidates, target-candidates[i], i); // Not i+1 because we can reuse the same element
				tmpList.remove(tmpList.size()-1);
			}
		}
		
    }
}