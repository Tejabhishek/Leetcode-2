public class Solution {
    public void moveZeroes(int[] nums) {
    	int count = 0;
    	for(int i = 0; i < nums.length; i++) {
    		// if it is not a zero, move to the front
    		if(nums[i] != 0) {
    			nums[count] = nums[i];
    			count++;
    		}
    	}
    	while(count < nums.length) {
    		nums[count] = 0;
    		count++;
    	}
    }
}