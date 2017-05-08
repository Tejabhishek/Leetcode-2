public class Solution {
	public int FindValleyOrPeak(int[] nums) {
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
}