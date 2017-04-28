public class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start <= end) {
        	int mid = (start + end) / 2;
        	if(nums[mid] == target) return mid;
        	if(nums[start] <= nums[mid]) {
        		// the bottom half is already sorted
        		if(target < nums[mid] && target >= nums[start]) {
        			// start <= target < mid
        			end = mid - 1;
        		} else {
        			start = mid + 1;
        		}
        	}
        	if(nums[mid] <= nums[end]) {
        		// the top half is already sorted
        		if(target > nums[mid] && target <= nums[end]) {
        			// mid < target <= end
        			start = mid + 1;
        		} else {
        			end = mid - 1;
        		}
        	}
        }
        return -1;
    }
}