public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int[] res = new int[2];
        // base case
        if(nums == null || nums.length == 0) return new int[] {-1, 1};
        // left side 
        while(start < end) {
        	int mid = start + (end - start) / 2;
        	if(target > nums[mid]) start = mid + 1;
        	else end = mid; 
        }
        if(target == nums[start]) res[0] = start;
        else res[0] = -1;
        // right side
        end = nums.length - 1;
        while(start < end) {
        	int mid = start + (end - start) / 2 + 1;
        	if(target < nums[mid]) end = mid - 1;
        	else start = mid;
        }
        if(target == nums[end]) res[1] = end;
        else res[1] = -1;
        return res;
    }
}