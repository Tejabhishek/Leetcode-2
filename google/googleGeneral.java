public void shiftArray(int[] nums, int target) {
	int count_non_target = 0;
	for(int i = 0; i < nums.length; i++) {
		if(nums[i] != target) nums[count_non_target++] = nums[i];
	}
	while(count_non_target < nums.length) nums[count_non_target++] = target;
}