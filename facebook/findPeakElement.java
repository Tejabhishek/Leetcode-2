public class Solution {
    public int findPeakElement(int[] nums) {
    	if(nums == null) return -1;
    	if(nums.length == 0) return 0;
        int start = 0, mid = 0, end = nums.length - 1;
        while(start < end) {
            mid = start + (end - start) / 2;
            if(nums[mid] < nums[mid + 1]) start = mid + 1;
            else end = mid;
        }
        return start;
    }
}