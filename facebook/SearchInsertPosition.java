public class Solution {
	public int SearchInsertPosition(int[] nums, int target) {
		if(nums == null) return -1;
		if(nums.length() == 0) return 0;
		int start = 0, end = nums.length - 1;
		int mid;
		while(start + 1 < end) {
			mid = start + (end - start) / 2;
			if(nums[mid] == target) return mid; // no duplicates, if not end == target
			else if(nums[mid] < target) start = mid;
			else end = mid;
		}
		if(nums[start] >= target) return start;
		else if(nums[end] >= target) return end; // in most cases
		else return end + 1; //nums[end] < target;
	}
}